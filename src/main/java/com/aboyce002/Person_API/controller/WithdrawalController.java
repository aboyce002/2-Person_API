package com.aboyce002.Person_API.controller;

import com.aboyce002.Person_API.domains.Withdrawal;
import com.aboyce002.Person_API.domains.response.ResponseStateReturn;
import com.aboyce002.Person_API.services.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public class WithdrawalController {
    @Autowired
    private WithdrawalService withdrawalService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountId}/withdrawals")
    public List<Withdrawal> getAllWithdrawals(@PathVariable Long id){
        return withdrawalService.getAllWithdrawals();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/withdrawals/{withdrawalId}")
    public Optional<Withdrawal> getWithdrawalById(@PathVariable Long id){
        return withdrawalService.getWithdrawalById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/accounts/{accountId}/withdrawals")
    public void addWithdrawal(@Valid @RequestBody Withdrawal withdrawal, @PathVariable Long id){
        withdrawalService.addWithdrawal(withdrawal);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/withdrawals/{withdrawalId}")
    public void updateWithdrawal(@PathVariable long id, @RequestBody Withdrawal withdrawal){
        withdrawalService.updateWithdrawal(id, withdrawal);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/withdrawals/{withdrawalId}")
    public void deleteWithdrawal(@PathVariable long id) {
        withdrawalService.deleteWithdrawal(id);
    }
}
