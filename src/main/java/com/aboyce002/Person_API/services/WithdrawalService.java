package com.aboyce002.Person_API.services;

import com.aboyce002.Person_API.domains.Account;
import com.aboyce002.Person_API.domains.Withdrawal;
import com.aboyce002.Person_API.repository.AccountRepository;
import com.aboyce002.Person_API.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalService {
    @Autowired
    private WithdrawalRepository withdrawalRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Withdrawal> getAllWithdrawalsForAccount(Long accountId){
        List<Withdrawal> listOfWithdrawals = new ArrayList<>();
        Optional<Account> acc = accountRepository.findById(accountId);
        if (acc.isPresent()){
            listOfWithdrawals = withdrawalRepository.findAllByPayerId(accountId);
            /*List<Account> accList = accountRepository.findAccountById(accountId);
            Account a = accList.get(0);

            List<Withdrawal> validDeposits = new ArrayList<>();
            for(Withdrawal w: listOfWithdrawals){
                if(w.getPayerId().equals(accountId))
                    validDeposits.add(w);*/
            return listOfWithdrawals;
        }
        return listOfWithdrawals;
    }

    public Optional<Withdrawal> getWithdrawalById(Long withdrawalId){
        return withdrawalRepository.findById(withdrawalId);
    }

    public Withdrawal addWithdrawal(Withdrawal withdrawal) {
        return withdrawalRepository.save(withdrawal);
    }

    public Withdrawal updateWithdrawal(Long id, Withdrawal withdrawal) {
        return withdrawalRepository.save(withdrawal);
    }

    public void deleteWithdrawal(Long id) {
        withdrawalRepository.deleteById(id);
    }
}
