package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        customerMapper = CustomerMapper.INSTANCE;
    }

    @Test
    void convertCustomerToCustomerDto() {
        Customer customer = Customer.builder()
                .id(1L)
                .firstname("jackie")
                .lastname("chan")
                .customerUrl("/customers/1/")
                .build();

        CustomerDto customerDto = customerMapper.convertCustomerToCustomerDto(customer);
        assertEquals(customer.getId(), customerDto.getId());
        assertEquals(customer.getFirstname(), customerDto.getFirstname());
        assertEquals(customer.getLastname(), customerDto.getLastname());
        assertEquals(customer.getCustomerUrl(), customerDto.getCustomerUrl());
    }
    @Test
    void convertCustomerDtoToCustomer() {
        CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .firstname("jackie")
                .lastname("chan")
                .customerUrl("/customers/1/")
                .build();

        Customer customer = customerMapper.convertCustomerDtoToCustomer(customerDto);
        assertEquals(customer.getId(), customerDto.getId());
        assertEquals(customer.getFirstname(), customerDto.getFirstname());
        assertEquals(customer.getLastname(), customerDto.getLastname());
        assertEquals(customer.getCustomerUrl(), customerDto.getCustomerUrl());
    }
}