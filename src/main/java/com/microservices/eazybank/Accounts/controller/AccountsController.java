package com.microservices.eazybank.Accounts.controller;

import com.microservices.eazybank.Accounts.constants.AccountsConstants;
import com.microservices.eazybank.Accounts.dto.CustomerDto;
import com.microservices.eazybank.Accounts.dto.ResponseDto;
import com.microservices.eazybank.Accounts.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/accounts")
@Slf4j
public class AccountsController {

    private IAccountService accountService;

    @Autowired
    public AccountsController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto>createAccount(@RequestBody CustomerDto customerDto) {
            accountService.createAccount(customerDto);
            log.info("Created account successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseDto(AccountsConstants.MESSAGE_201, AccountsConstants.MESSAGE_201)
            );
    }
}
