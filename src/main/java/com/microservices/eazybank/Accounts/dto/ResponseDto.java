package com.microservices.eazybank.Accounts.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private String statusCode;
    private String statusMsg;
}
