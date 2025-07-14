package com.microservices.eazybank.Accounts.repository;

import com.microservices.eazybank.Accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

  Optional<Accounts> findByCustomerId(long customerId);

  void deleteByCustomerId(Long customerId);
}
