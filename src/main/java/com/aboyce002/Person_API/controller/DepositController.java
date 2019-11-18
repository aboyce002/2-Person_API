package com.aboyce002.Person_API.controller;

import com.aboyce002.Person_API.domains.Deposit;
import com.aboyce002.Person_API.domains.response.ResponseStateReturn;
import com.aboyce002.Person_API.services.DepositService;
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
public class DepositController {

    @Autowired
    private DepositService depositService;

    @RequestMapping(method= RequestMethod.GET, value="/accounts/{accountId}/deposits")
    public ResponseEntity<?> getAllDepositsForAccount(@PathVariable Long accountId){
        ResponseStateReturn rep = new ResponseStateReturn();
        List<Deposit> allDeposits = depositService.getAllDepositsForAccount(accountId);

        if(!allDeposits.isEmpty())
        {
            rep.setCode(HttpStatus.OK.value());
            rep.setMessage("Success");
            rep.setData(allDeposits);
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }
        else {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Account not found");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/deposits/{depositId}")
    public ResponseEntity<?> getDeposit(@PathVariable Long depositId){
        ResponseStateReturn rep = new ResponseStateReturn();
        Optional<Deposit> deposit = depositService.getDepositById(depositId);

        if(deposit.isPresent())
        {
            rep.setCode(HttpStatus.OK.value());
            rep.setMessage("Success");
            rep.setData(deposit);
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }
        else {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error fetching deposit with id");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.POST, value="/accounts/{accountId}/deposits")
    public ResponseEntity<?> addDeposit(@RequestBody Deposit deposit, @PathVariable Long accountId){
        ResponseStateReturn rep = new ResponseStateReturn();
        Deposit d = depositService.addDeposit(deposit, accountId);

        if(d != null){
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newPollUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(d.getId())
                    .toUri();
            responseHeaders.setLocation(newPollUri);

            return new ResponseEntity<>(d, responseHeaders, HttpStatus.CREATED);
        }
        else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error creating deposit: Account not found”");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.PUT, value="/deposits/{depositId}")
    public ResponseEntity<?> updateDeposit(@RequestBody Deposit deposit, @PathVariable Long depositId){
        ResponseStateReturn rep = new ResponseStateReturn();
        Deposit uDeposit = depositService.updateDeposit(deposit, depositId);

        if(uDeposit != null){
            rep.setCode(HttpStatus.ACCEPTED.value());
            rep.setMessage("Accepted deposit modification");
            return new ResponseEntity<>(rep, HttpStatus.ACCEPTED);
        }
        else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Deposit ID does not exist");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/deposits/{depositId}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositId){
        ResponseStateReturn rep = new ResponseStateReturn();

        if(depositService.getDepositById(depositId).isPresent()){
            depositService.deleteDeposit(depositId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("This id does not exist in deposits");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }
}
