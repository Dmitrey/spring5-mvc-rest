package guru.springfamework.service;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerMapper = CustomerMapper.INSTANCE;
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void findCustomerById() {
        Customer customer = Customer.builder()
                .id(1L)
                .firstname("jackie")
                .lastname("chan")
                .customerUrl("/customers/1/")
                .build();

        when(customerRepository.findCustomerById(anyLong())).thenReturn(customer);

        CustomerDto customerById = customerService.findCustomerById(1L);

        verify(customerRepository, times(1)).findCustomerById(anyLong());

        assertEquals(customer.getId(), customerById.getId());
        assertEquals(customer.getFirstname(), customerById.getFirstname());
        assertEquals(customer.getLastname(), customerById.getLastname());
        assertEquals(customer.getCustomerUrl(), customerById.getCustomerUrl());
    }

    @Test
    void findCustomerByName() {
        Customer customer = Customer.builder()
                .id(1L)
                .firstname("jackie")
                .lastname("chan")
                .customerUrl("/customers/1/")
                .build();

        when(customerRepository.findCustomerByFirstname(anyString())).thenReturn(customer);

        CustomerDto customerById = customerService.findCustomerByName("jackie");

        verify(customerRepository, times(1)).findCustomerByFirstname(anyString());

        assertEquals(customer.getId(), customerById.getId());
        assertEquals(customer.getFirstname(), customerById.getFirstname());
        assertEquals(customer.getLastname(), customerById.getLastname());
        assertEquals(customer.getCustomerUrl(), customerById.getCustomerUrl());
    }

    @Test
    void findAllCustomers() {
        Customer customer = Customer.builder()
                .id(1L)
                .firstname("jackie")
                .lastname("chan")
                .customerUrl("/customers/1/")
                .build();
        Customer customer1 = Customer.builder()
                .id(2L)
                .firstname("van")
                .lastname("darkholm")
                .customerUrl("/customers/2/")
                .build();
        List<Customer> customerDtoList = new ArrayList<>(Arrays.asList(customer1, customer));

        when(customerRepository.findAll()).thenReturn(customerDtoList);

        List<CustomerDto> allCustomers = customerService.findAllCustomers();

        verify(customerRepository, times(1)).findAll();
        assertEquals(2, allCustomers.size());
    }
}