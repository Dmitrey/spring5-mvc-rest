package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.api.v1.model.CustomerListDto;
import guru.springfamework.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "get list of all customers", description = "some description")
    @GetMapping
    public ResponseEntity<CustomerListDto> getAllCategories() {
        return new ResponseEntity<>(
                new CustomerListDto(customerService.findAllCustomers()),
                HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CustomerDto> getCategoryByName(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(
                customerService.findCustomerByName(name),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(
                customerService.findCustomerById(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(
                customerService.saveCustomer(customerDto),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(
                customerService.updateCustomer(customerDto),
                HttpStatus.OK
        );
    }

    @PatchMapping
    public ResponseEntity<CustomerDto> patchCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(
                customerService.patchCustomer(customerDto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDto> deleteCustomerById(@PathVariable(name = "id") Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
