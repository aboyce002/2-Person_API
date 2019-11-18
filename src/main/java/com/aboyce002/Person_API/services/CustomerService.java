package com.aboyce002.Person_API.services;

import com.aboyce002.Person_API.domains.Customer;
import com.aboyce002.Person_API.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

public List<Customer> getAccountForCus(Long customerId) {
    List<Customer> listOfAccForCus = new ArrayList<>();
    customerRepository.findAll().forEach(listOfAccForCus::add);
    return listOfAccForCus;
}

public List<Customer> getAllCustomers(Long id) {
    List<Customer> listOfCustomers = new ArrayList<>();
    customerRepository.findAll().forEach(listOfCustomers::add);
    return listOfCustomers;
}

public Optional<Customer> getCustomerById(Long id) {
    return customerRepository.findById(id);
}

public void createCustomer(Customer customer) {
    customerRepository.save(customer);
}

public Customer updateCustomer(Long customerId, Customer customer) {
    customer.setId(customerId);
    return customerRepository.save(customer);
}



}
