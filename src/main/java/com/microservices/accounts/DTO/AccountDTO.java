package com.microservices.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(
        name = "Account",
        description = "Schema to hold account information"
)
public class AccountDTO {
    @Schema(
            description = "account number of customer"
    )
    @NotEmpty(message = "Account number should not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "mobile number should be 10 digits")
    private Long accountNumber;
    @Schema(
            description = "account type of customer",example = "savings"
    )
    @NotEmpty(message = "account type should not be null or empty")
    private String accountType;
    @Schema(
            description = "branch address of bank",example = "electronic city"
    )
    @NotEmpty(message = "branch address should not be null or empty")
    private String branchAddress;

}
