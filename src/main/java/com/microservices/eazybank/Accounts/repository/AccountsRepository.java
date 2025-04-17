package com.microservices.eazybank.Accounts.repository;

import com.microservices.eazybank.Accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
}
