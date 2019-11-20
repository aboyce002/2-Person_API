package com.aboyce002.Person_API.controller;

import com.aboyce002.Person_API.domains.Account;
import com.aboyce002.Person_API.domains.response.ResponseStateReturn;
import com.aboyce002.Person_API.services.AccountService;
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
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts")
    public ResponseEntity<?> getAllAccts(){
        ResponseStateReturn rep = new ResponseStateReturn();
        List<Account> accounts = accountService.getAllAccounts();

        if(!accounts.isEmpty())
        {
            rep.setCode(HttpStatus.OK.value());
            rep.setMessage("Success");
            rep.setData(accounts);
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }
        else {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error fetching accounts");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{customerId} ")
    public ResponseEntity<?> getByCustomerId(@PathVariable Long customerId){
        ResponseStateReturn rep = new ResponseStateReturn();
        Optional<Account> account = accountService.getByCustomerId(customerId);

        if(account.isPresent())
        {
            rep.setCode(HttpStatus.OK.value());
            rep.setMessage("Success");
            rep.setData(account);
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }
        else {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error fetching account");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{customerId}/accounts")
    public ResponseEntity<?> getAllAccountsForCustomer(@PathVariable Long customerId){
        ResponseStateReturn rep = new ResponseStateReturn();
        List<Account> account = accountService.getAllAccountsForCustomer(customerId);

        if(!account.isEmpty())
        {
            rep.setCode(HttpStatus.OK.value());
            rep.setMessage("Success");
            rep.setData(account);
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }
        else {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error fetching customer accounts");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers/{id}/accounts")
    public ResponseEntity<?> addAccount(@RequestBody Account account, @PathVariable Long id) {
        ResponseStateReturn rep = new ResponseStateReturn();
        Account a = accountService.addAccount(account);

        if(a != null)
        {
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newPollUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(a.getId())
                    .toUri();
            responseHeaders.setLocation(newPollUri);

            rep.setCode(HttpStatus.CREATED.value());
            rep.setMessage("Account created");
            rep.setData(a);

            return new ResponseEntity<>(rep, responseHeaders, HttpStatus.CREATED);
        }
        else {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error creating customer account");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        ResponseStateReturn rep = new ResponseStateReturn();
        Account a = accountService.updateAccount(account);

        if(a != null)
        {
            rep.setCode(HttpStatus.ACCEPTED.value());
            rep.setMessage("Accepted deposit modification");
            return new ResponseEntity<>(rep, HttpStatus.ACCEPTED);
        }
        else {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error fetching customer accounts");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/accounts/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        ResponseStateReturn rep = new ResponseStateReturn();

        if(accountService.deleteAccount(id)){
            rep.setCode(HttpStatus.ACCEPTED.value());
            rep.setMessage("Account successfully deleted");
            return new ResponseEntity<>(rep, HttpStatus.ACCEPTED);
        }
        else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Account does not exist");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }
}
