package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.domain.Customer;
import guru.springfamework.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
//        categoryController = new CategoryController(categoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        customerMapper = CustomerMapper.INSTANCE;
    }

    @Test
    void getAllCategories() throws Exception {
        CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .firstname("jackie")
                .lastname("chan")
                .customerUrl("/customers/1/")
                .build();
        CustomerDto customerDto1 = CustomerDto.builder()
                .id(2L)
                .firstname("van")
                .lastname("darkholm")
                .customerUrl("/customers/2/")
                .build();

        when(customerService.findAllCustomers()).thenReturn(new ArrayList<>(Arrays.asList(customerDto, customerDto1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    void getCategoryByName() throws Exception {
        String jackie = "jackie";
        CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .firstname(jackie)
                .lastname("chan")
                .customerUrl("/customers/jackie/")
                .build();
        when(customerService.findCustomerByName(anyString())).thenReturn(customerDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/name/" + jackie + "/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(jackie)));
    }

    @Test
    void getCategoryById() throws Exception {
        String jackie = "jackie";
        long id = 1L;
        CustomerDto customerDto = CustomerDto.builder()
                .id(id)
                .firstname(jackie)
                .lastname("chan")
                .customerUrl("/customers/jackie/")
                .build();
        when(customerService.findCustomerById(anyLong())).thenReturn(customerDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/" + id + "/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(jackie)));
    }
}