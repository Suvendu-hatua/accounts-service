package com.microservices.eazybank.Accounts.controller;

import com.microservices.eazybank.Accounts.constants.AccountsConstants;
import com.microservices.eazybank.Accounts.dto.CustomerDto;
import com.microservices.eazybank.Accounts.dto.ErrorResponseDto;
import com.microservices.eazybank.Accounts.dto.ResponseDto;
import com.microservices.eazybank.Accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts  Microservices",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path = "/api/v1/accounts")
@Slf4j
public class AccountsController {

    private IAccountService accountService;

    @Autowired
    public AccountsController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer &  Account in Banking"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "HTTP Status CREATED",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status INTERNAL SERVER ERROR",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class
                                    )
                            )
                    )
            }
    )

    @PostMapping("/create")
    public ResponseEntity<ResponseDto>createAccount(@RequestBody CustomerDto customerDto) {
            accountService.createAccount(customerDto);
            log.info("Created account successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseDto(AccountsConstants.MESSAGE_201, AccountsConstants.MESSAGE_201)
            );
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountsDetails(@RequestParam(required = false) String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody CustomerDto customerDto) {
        boolean status = accountService.updateAccount(customerDto);
        if (status) {
            log.info("Updated account successfully.");
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.MESSAGE_200, AccountsConstants.MESSAGE_200));
        }else{
            log.info("Updated account failed.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam(required = false) String mobileNumber) {
        boolean status = accountService.deleteAccount(mobileNumber);
        if(status){
            log.info("Deleted account successfully.");
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.MESSAGE_200, AccountsConstants.MESSAGE_200));
        }else{
            log.info("Deleted account failed.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_DELETE));
        }

    }
}
