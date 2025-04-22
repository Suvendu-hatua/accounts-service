package com.microservices.eazybank.Accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account Information"
)
public class AccountsDto {
    @Schema(
            description = "Account Number of Banking Microservices",example = "3243222561"
    )
    @NotBlank(message = "Account Number can not be a null or blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of Eazy Bank account", example = "Savings"
    )
    @NotBlank(message = "Account Type can not be a null or blank")
    private String accountType;

    @Schema(
            description = "Bank branch address", example = "123 NewYork"
    )
    @NotBlank(message = "Branch Address can not be a null or empty")
    private String branchAddress;
}
