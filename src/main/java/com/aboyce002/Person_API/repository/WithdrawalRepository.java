package com.aboyce002.Person_API.repository;

import com.aboyce002.Person_API.domains.Withdrawal;
import org.springframework.data.repository.CrudRepository;

public interface WithdrawalRepository extends CrudRepository<Withdrawal, Long> {
}
