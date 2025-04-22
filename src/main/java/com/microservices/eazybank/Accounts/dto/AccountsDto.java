package com.microservices.eazybank.Accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account Information"
)
public class AccountsDto {
    @Schema(
            description = "Account Number of Banking Microservices",example = "32432222256"
    )
    private Long accountNumber;
    @Schema(
            description = "Account type of Eazy Bank account", example = "Savings"
    )
    private String accountType;
    @Schema(
            description = "Bank branch address", example = "123 NewYork"
    )
    private String branchAddress;
}
