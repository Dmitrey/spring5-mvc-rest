package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(SpringVersion.getVersion());
        Category category = Category.builder().name("test cat 1").build();
        Category category1 = Category.builder().name("test cat 2").build();
        Category category2 = Category.builder().name("test cat 3").build();
        Category category3 = Category.builder().name("test cat 4").build();
        categoryRepository.saveAll(Arrays.asList(category,category1,category2,category3));

        Customer customer = Customer.builder()
                .firstname("jackie")
                .lastname("chan")
                .customerUrl("/customers/1/")
                .build();
        Customer customer1 = Customer.builder()
                .firstname("van")
                .lastname("darkholm")
                .customerUrl("/customers/2/")
                .build();
        Customer customer2 = Customer.builder()
                .firstname("van")
                .lastname("darkholm")
                .customerUrl("/customers/3/")
                .build();
        List<Customer> customerDtoList = new ArrayList<>(Arrays.asList(customer1, customer,customer2));
        customerRepository.saveAll(customerDtoList);
    }
}
