package com.aboyce002.Person_API.services;

import com.aboyce002.Person_API.domains.Withdrawal;
import com.aboyce002.Person_API.domains.response.ResponseStateReturn;
import com.aboyce002.Person_API.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WithdrawalService {
    @Autowired
    private WithdrawalRepository withdrawalRepository;

    public List<Withdrawal> getAllWithdrawals(){
        List<Withdrawal> listOfWithdrawals = new ArrayList<>();
        withdrawalRepository.findAll().forEach(listOfWithdrawals::add);
        return listOfWithdrawals;
    }

    public Optional<Withdrawal> getWithdrawalById(long id){
        return withdrawalRepository.findById(id);
    }

    public void addWithdrawal(Withdrawal withdrawal) {
        withdrawalRepository.save(withdrawal);
    }

    public void updateWithdrawal(long id, Withdrawal withdrawal) {
        withdrawalRepository.save(withdrawal);
    }

    public void deleteWithdrawal(long id) {
        withdrawalRepository.deleteById(id);
    }
}
