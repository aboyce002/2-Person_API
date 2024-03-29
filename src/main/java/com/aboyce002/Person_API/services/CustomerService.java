package com.aboyce002.Person_API.services;

import com.aboyce002.Person_API.domains.Account;
import com.aboyce002.Person_API.domains.Customer;
import com.aboyce002.Person_API.repository.AccountRepository;
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
    private AccountRepository accountRepository;

    public List<Customer> getAccountForCustomer(Long accountId) {
        List<Customer> listOfCustomers = new ArrayList<>();
        Optional<Account> acc = accountRepository.findById(accountId);
        if (acc.isPresent()){
            customerRepository.findAll().forEach(listOfCustomers::add);
            List<Account> accList = accountRepository.findAccountById(accountId);
            Account a = accList.get(0);

            List<Customer> validCustomers = new ArrayList<>();
            for(Customer c : listOfCustomers){
                if(c.getId() == a.getCustomerId())
                    validCustomers.add(c);
            }
                return validCustomers;
            }
        return listOfCustomers;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> listOfCustomers = new ArrayList<>();
        customerRepository.findAll().forEach(listOfCustomers::add);
        return listOfCustomers;
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, Customer customer) {
        customer.setId(customerId);
        return customerRepository.save(customer);
    }
}
