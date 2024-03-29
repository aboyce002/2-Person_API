package com.aboyce002.Person_API.services;

import com.aboyce002.Person_API.domains.Account;
import com.aboyce002.Person_API.repository.AccountRepository;
import com.aboyce002.Person_API.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        accountRepository.findAll().forEach(accountList::add);
        return accountList;
    }

    public Optional<Account> getByCustomerId(Long customerId){
        if (customerRepository.findById(customerId).isPresent())
            return accountRepository.findById((long) 1);
        return null;
    }

    public List<Account> getAllAccountsForCustomer(Long customerId){
        List<Account> accountList = new ArrayList<>();
        if (customerRepository.findById(customerId).isPresent()){
            accountRepository.findAll().forEach(accountList::add);

            List<Account> validDeposits = new ArrayList<>();
            for(Account a : accountList){
                if(a.getCustomerId() == customerId)
                    validDeposits.add(a);
            }
            return validDeposits;
        }
        return null;
    }

    public Account addAccount(Account account){
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account){
        return accountRepository.save(account);
    }

    public boolean deleteAccount(Long id){
        if(accountRepository.findById(id).isPresent())
        {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
