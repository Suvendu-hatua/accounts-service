package com.microservices.eazybank.Accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer Information"
)
public class CustomerDto {
    @Schema(
            description = "Name of the customer", example = "Suvendu Hatua"
    )
    private String name;
    @Schema(
            description = "Email address of the customer", example = "tutor@eazybytes.com"
    )
    private String email;
    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    private String mobileNumber;
    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;
}
