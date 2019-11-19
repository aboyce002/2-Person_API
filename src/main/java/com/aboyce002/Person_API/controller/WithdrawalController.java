package com.aboyce002.Person_API.controller;

import com.aboyce002.Person_API.domains.Withdrawal;
import com.aboyce002.Person_API.domains.response.ResponseStateReturn;
import com.aboyce002.Person_API.services.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

public class WithdrawalController {
    @Autowired
    private WithdrawalService withdrawalService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawals(@PathVariable Long accountId){
        ResponseStateReturn rep = new ResponseStateReturn();
        List<Withdrawal> allWithdrawals = withdrawalService.getAllWithdrawalsForAccount(accountId);

        if(!allWithdrawals.isEmpty())
        {
            rep.setCode(HttpStatus.OK.value());
            rep.setMessage("Success");
            rep.setData(allWithdrawals);
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }
        else {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Account not found");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId){
        ResponseStateReturn rep = new ResponseStateReturn();
        Optional<Withdrawal> withdrawal = withdrawalService.getWithdrawalById(withdrawalId);;

        if(withdrawal.isPresent())
        {
            rep.setCode(HttpStatus.OK.value());
            rep.setMessage("Success");
            rep.setData(withdrawal);
            return new ResponseEntity<>(rep, HttpStatus.OK);
        }
        else {
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error fetching withdrawal with id");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> addWithdrawal(@Valid @RequestBody Withdrawal withdrawal, @PathVariable Long id){
        ResponseStateReturn rep = new ResponseStateReturn();
        Withdrawal w = withdrawalService.addWithdrawal(withdrawal);;

        if(w != null){
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newPollUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(w.getId())
                    .toUri();
            responseHeaders.setLocation(newPollUri);

            rep.setCode(HttpStatus.CREATED.value());
            rep.setMessage("Created withdrawal and deducted it from the account");
            rep.setData(w);

            return new ResponseEntity<>(rep, responseHeaders, HttpStatus.CREATED);
        }
        else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Error creating withdrawal: Account not found");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@PathVariable long id, @RequestBody Withdrawal withdrawal){
        ResponseStateReturn rep = new ResponseStateReturn();
        Withdrawal uWithdrawal = withdrawalService.updateWithdrawal(id, withdrawal);

        if(uWithdrawal != null){
            rep.setCode(HttpStatus.ACCEPTED.value());
            rep.setMessage("Accepted withdrawal modification");
            return new ResponseEntity<>(rep, HttpStatus.ACCEPTED);
        }
        else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("Withdrawal ID does not exist");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/withdrawals/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable long withdrawalId) {
        ResponseStateReturn rep = new ResponseStateReturn();

        if(withdrawalService.getWithdrawalById(withdrawalId).isPresent()){
            withdrawalService.deleteWithdrawal(withdrawalId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            rep.setCode(HttpStatus.NOT_FOUND.value());
            rep.setMessage("This id does not exist in withdrawals");
            return new ResponseEntity<>(rep, HttpStatus.NOT_FOUND);
        }
    }
}
