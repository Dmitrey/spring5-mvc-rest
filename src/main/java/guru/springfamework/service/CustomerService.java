package guru.springfamework.service;

import guru.springfamework.api.v1.model.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto findCustomerById(Long id);
    CustomerDto findCustomerByName(String name);
    List<CustomerDto> findAllCustomers();
    CustomerDto saveCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(CustomerDto customerDto);
    CustomerDto patchCustomer(CustomerDto customerDto);
    void deleteCustomerById(Long id);
}
