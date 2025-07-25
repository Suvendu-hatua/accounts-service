package com.microservices.eazybank.Accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
  @NotBlank(message = "Name can not be a null or empty")
  @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
  private String name;

  @Schema(
          description = "Email address of the customer", example = "tutor@eazybytes.com"
  )
  @NotBlank(message = "Email Address can not be a null or empty")
  @Email(message = "Email address should be a valid value")
  private String email;

  @Schema(
          description = "Mobile Number of the customer", example = "9345432123"
  )
  @NotBlank(message = "Mobile Number can not be a null or empty")
  @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
  private String mobileNumber;

  @Schema(
          description = "Account details of the Customer"
  )
  private AccountsDto accountsDto;
}
