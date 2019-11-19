package com.aboyce002.Person_API.controller;

import com.aboyce002.Person_API.domains.Account;
import com.aboyce002.Person_API.domains.Address;
import com.aboyce002.Person_API.domains.Bill;
import com.aboyce002.Person_API.domains.Customer;
import com.aboyce002.Person_API.domains.response.ResponseStateReturn;
import com.aboyce002.Person_API.services.AccountService;
import com.aboyce002.Person_API.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/accounts/{accountId}/customer")
    public ResponseEntity<?> getAccountForCustomer(@PathVariable Long accountId) {
        ResponseStateReturn rep = new ResponseStateReturn();
        List<Customer> customers = customerService.getAccountForCustomer(accountId);

        if(!customers.isEmpty())
        {
            rep.setCode(HttpStatus.OK.value());
            rep.setMessage("Success");
            rep.setData(customers);
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }
        else {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error fetching accounts");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        ResponseStateReturn rep = new ResponseStateReturn();
        List<Customer> customers = customerService.getAllCustomers();

        if(!customers.isEmpty())
        {
            rep.setCode(HttpStatus.OK.value());
            rep.setMessage("Success");
            rep.setData(customers);
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }
        else {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error fetching customers");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("customerId") Long id) {
        ResponseStateReturn rep = new ResponseStateReturn();
        Optional<Customer> customer = customerService.getCustomerById(id);

        if(customer.isPresent())
        {
            rep.setCode(HttpStatus.OK.value());
            rep.setMessage("Success");
            rep.setData(customer);
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }
        else {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error fetching customer");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer, @PathVariable("accountId") Long id) {
        ResponseStateReturn rep = new ResponseStateReturn();
        Customer c = customerService.createCustomer(customer);

        if(c != null){
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newPollUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(c.getId())
                    .toUri();
            responseHeaders.setLocation(newPollUri);

            rep.setCode(HttpStatus.CREATED.value());
            rep.setMessage("Customer account created");
            rep.setData(c);

            return new ResponseEntity<>(rep, responseHeaders, HttpStatus.CREATED);
        }
        else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error creating customer");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("customerId") Long id, @RequestBody Customer customer) {
        ResponseStateReturn rep = new ResponseStateReturn();
        Customer uCustomer = customerService.updateCustomer(id, customer);

        if(uCustomer != null){
            rep.setCode(HttpStatus.ACCEPTED.value());
            rep.setMessage("Customer account updated");
            return new ResponseEntity<>(rep, HttpStatus.ACCEPTED);
        }
        else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error updating customer");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }
}


