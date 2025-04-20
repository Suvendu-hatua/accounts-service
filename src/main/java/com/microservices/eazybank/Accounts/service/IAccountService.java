package com.microservices.eazybank.Accounts.service;

import com.microservices.eazybank.Accounts.dto.CustomerDto;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);
}
