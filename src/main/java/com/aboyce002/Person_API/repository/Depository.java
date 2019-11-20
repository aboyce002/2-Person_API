package com.aboyce002.Person_API.repository;

import com.aboyce002.Person_API.domains.Deposit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Depository extends CrudRepository<Deposit, Long> {
    List<Deposit> findAllByPayeeId(Long accountId);
}
