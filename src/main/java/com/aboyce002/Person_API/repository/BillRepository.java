package com.aboyce002.Person_API.repository;

import com.aboyce002.Person_API.domains.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {

    List<Bill> findAccountById(Long accountId);
}
