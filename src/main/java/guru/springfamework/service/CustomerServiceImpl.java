package guru.springfamework.service;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        customerMapper = CustomerMapper.INSTANCE;
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        Customer customerById = customerRepository.findCustomerById(id);
        return customerMapper.convertCustomerToCustomerDto(customerById);
    }

    @Override
    public CustomerDto findCustomerByName(String name) {
        Customer customerByName = customerRepository.findCustomerByFirstname(name);
        return customerMapper.convertCustomerToCustomerDto(customerByName);
    }

    @Override
    public List<CustomerDto> findAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::convertCustomerToCustomerDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return customerMapper.convertCustomerToCustomerDto(customerRepository.save(customerMapper.convertCustomerDtoToCustomer(customerDto)));
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        if (customerDto.getId() == null)
            throw new RuntimeException("CustomerServiceImpl::updateCustomer:: id customer не может быть null при редактировании");
        Customer customer = customerMapper.convertCustomerDtoToCustomer(customerDto);
        customer = customerRepository.save(customer);
        return customerMapper.convertCustomerToCustomerDto(customer);
    }

    @Override
    public CustomerDto patchCustomer(CustomerDto customerDto) {
        Customer customerById = customerRepository.findCustomerById(customerDto.getId());

        if (customerDto.getFirstname() != null) {
            customerById.setFirstname(customerDto.getFirstname());
        }
        if (customerDto.getLastname() != null) {
            customerById.setLastname(customerDto.getLastname());
        }
        if (customerDto.getCustomerUrl() != null) {
            customerById.setCustomerUrl(customerDto.getCustomerUrl());
        }

        customerRepository.save(customerById);
        return customerMapper.convertCustomerToCustomerDto(customerById);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
