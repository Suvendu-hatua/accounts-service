package com.microservices.eazybank.Accounts.repository;

import com.microservices.eazybank.Accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Optional<Customer> findByMobileNumber(String mobileNumber);
}
