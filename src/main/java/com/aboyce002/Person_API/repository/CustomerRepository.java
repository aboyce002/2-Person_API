package com.aboyce002.Person_API.repository;

import com.aboyce002.Person_API.domains.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
