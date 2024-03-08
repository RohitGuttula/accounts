package com.microservices.accounts.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDTO {
    @Schema(
            description = "name of customer", example = "Rohit"
    )
    @NotEmpty(message = "name can not be null or empty")
    @Size(min=5,max=20,message = "The length of the customer name should be between 5 and 20")
    private String name;
    @Schema(
            description = "email of customer", example = "rohitguttula1@gmail.com"
    )
    @NotEmpty(message = "email can not be null or empty")
    @Email(message = "email address should be a valid one")
    private String email;
    @Schema(
            description = "mobileNumber of customer", example = "9542698298"
    )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "mobile number should be 10 digits")
    private String mobileNumber;
    @Schema(
            description = "account details of customer"
    )
    private AccountDTO accountDTO;
}
