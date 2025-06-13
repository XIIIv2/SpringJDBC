package icu.xiii.app.controller;

import icu.xiii.app.dto.customer.*;
import icu.xiii.app.entity.Customer;
import icu.xiii.app.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDtoCreateResponse> createCustomer(@RequestBody CustomerDtoRequest request) {
        Customer customer = customerService.create(request);

        return (customer != null)
                ? ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoCreateResponse.of(true, customer))
                : ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerDtoCreateResponse.of(false, null));
    }

    @GetMapping
    public ResponseEntity<CustomerDtoListResponse> fetchAllCustomers() {
        List<Customer> customers = customerService.fetchAll();
        if (customers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoListResponse.of(true, Collections.emptyList()));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomerDtoListResponse.of(false, customers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDtoGetByIdResponse> fetchCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.fetchById(id);
        if (customer != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoGetByIdResponse.of(id, true, customer));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomerDtoGetByIdResponse.of(id, false, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDtoUpdateResponse> updateCustomerById(@PathVariable("id") Long id, @RequestBody CustomerDtoRequest request) {
        if (customerService.fetchById(id) != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomerDtoUpdateResponse.of(id, true, customerService.updateById(id, request)));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomerDtoUpdateResponse.of(id, false, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDtoDeleteResponse> deleteCustomerById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomerDtoDeleteResponse.of(id, customerService.deleteById(id)));
    }

    @GetMapping("/last-entity")
    public ResponseEntity<CustomerDtoGetLastEntityResponse> getLastEntity() {
        Customer customer = customerService.getLastEntity();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomerDtoGetLastEntityResponse.of(customer != null, customer));
    }
}
