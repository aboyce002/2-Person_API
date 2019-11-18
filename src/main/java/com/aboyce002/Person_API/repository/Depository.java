package com.aboyce002.Person_API.repository;

import com.aboyce002.Person_API.domains.Deposit;
import org.springframework.data.repository.CrudRepository;

public interface Depository extends CrudRepository<Deposit, Long> {
}
