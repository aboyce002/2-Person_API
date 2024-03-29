package com.aboyce002.Person_API.services;

import com.aboyce002.Person_API.domains.Account;
import com.aboyce002.Person_API.domains.Deposit;
import com.aboyce002.Person_API.repository.AccountRepository;
import com.aboyce002.Person_API.repository.Depository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepositService {
    @Autowired
    private Depository depository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Deposit> getAllDepositsForAccount(Long accountId) {
        List<Deposit> listOfDeposits = new ArrayList<>();
        Optional<Account> acc = accountRepository.findById(accountId);
        if (acc.isPresent()){
            listOfDeposits = depository.findAllByPayeeId(accountId);
            /*depository.findAll().forEach(listOfDeposits::add);
            List<Account> accList = accountRepository.findAccountById(accountId);
            Account a = accList.get(0);

            List<Deposit> validDeposits = new ArrayList<>();
            for(Deposit d : listOfDeposits){
                if(d.getPayee_id() == a.getCustomerId())
                    validDeposits.add(d);
            }*/
            return listOfDeposits;
        }
        return listOfDeposits;
    }

    public Optional<Deposit> getDepositById(Long depositId) {
        return depository.findById(depositId);
    }

    public Deposit addDeposit(Deposit deposit) { return depository.save(deposit); }

    public Deposit updateDeposit(Deposit deposit) {
        return depository.save(deposit);
    }

    public void deleteDeposit(Long depositId) {
        depository.deleteById(depositId);
    }
}