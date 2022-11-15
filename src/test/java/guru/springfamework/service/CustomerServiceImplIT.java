package guru.springfamework.service;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.bootstrap.Bootstrap;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    CustomerMapper customerMapper;

    @Before
    public void setUp() throws Exception {
        new Bootstrap(categoryRepository, customerRepository).run();
        customerService = new CustomerServiceImpl(customerRepository);
        customerMapper = CustomerMapper.INSTANCE;
    }

    @Test
    public void testSaveCustomer() {
        long startSize = customerRepository.count();
        CustomerDto savedCustomer = customerService.saveCustomer(CustomerDto.builder().firstname("fname").lastname("lname").build());
        long newSize = customerRepository.count();
        assertEquals(startSize + 1, newSize);
    }

    @Test
    public void patchFirstName(){
        Customer originalCustomer = customerRepository.findAll().get(0);

        String originalFirstname = originalCustomer.getFirstname();
        String originalLastname = originalCustomer.getLastname();
        String originalUrl = originalCustomer.getCustomerUrl();

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(originalCustomer.getId());
        customerDto.setFirstname(originalCustomer.getFirstname()+" patched");
        customerService.patchCustomer(customerDto);
        Customer updatedCustomer = customerRepository.findCustomerById(customerDto.getId());

        assertEquals(updatedCustomer.getLastname(), originalLastname);
        assertEquals(updatedCustomer.getCustomerUrl(), originalUrl);
        assertThat(originalFirstname, not(equalTo(updatedCustomer.getFirstname())));
    }

}
