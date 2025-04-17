package com.microservices.eazybank.Accounts.repository;

import com.microservices.eazybank.Accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
