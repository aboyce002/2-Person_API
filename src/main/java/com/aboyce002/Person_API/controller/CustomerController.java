package com.aboyce002.Person_API.controller;

import com.aboyce002.Person_API.domains.Address;
import com.aboyce002.Person_API.domains.Bill;
import com.aboyce002.Person_API.domains.Customer;
import com.aboyce002.Person_API.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/accounts/{accountId}/customer")
    public List<Customer> getAccountForCus(@PathVariable Long id) {
        return customerService.getAccountForCus(id);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(@PathVariable Long id, @RequestBody Address address) {
        return customerService.getAllCustomers(id);
    }

    @GetMapping("/customers/{id}")
    public Optional<Customer> getCustomerById(@PathVariable("customerId") Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customers")
    public void createCustomer(@RequestBody Customer customer, @PathVariable("accountId") Long id) {
        customerService.createCustomer(customer);
    }

    @PutMapping("/customers/{id}")
    public void updateCustomer(@PathVariable("customerId") Long id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
    }
}


