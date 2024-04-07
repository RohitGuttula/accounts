package com.microservices.accounts.Controller;

import com.microservices.accounts.Constants.AccountConstants;
import com.microservices.accounts.DTO.*;
import com.microservices.accounts.Enitity.Customer;
import com.microservices.accounts.Service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
@Tag(
        name = "CRUD Rest Api's for Accounts Microservice",
        description = "Crud Rest apis to Create,Fetch,Update,Delete for Accounts Microservice"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {
    private IAccountService iAccountService;

    public AccountsController (IAccountService iAccountService){
        this.iAccountService=iAccountService;
    }
    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @Operation(
            summary = "Create Rest API for Accounts controller",
            description = "Rest API to create new Customer,Account inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http Status Created"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema=@Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO){
           iAccountService.createAccount(customerDTO);
           return ResponseEntity
                   .status(HttpStatus.CREATED)
                   .body(new ResponseDTO(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }
    @Operation(
            summary = "Fetch Rest API for Accounts controller",
            description = "Rest API to fetch Customer,Account details inside EazyBank"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})",message = "mobile number should be 10 digits")
                                                               String mobileNumber){
           CustomerDTO customerDTO=iAccountService.fetchAccountDetails(mobileNumber);
           return ResponseEntity.status(HttpStatus.OK)
                   .body(customerDTO);
    }
    @Operation(
            summary = "Update Rest API for Accounts controller",
            description = "Rest API to update Customer,Account details inside EazyBank"
    )
    @ApiResponses ({
        @ApiResponse(
                responseCode = "200",
                description = "Http Status OK"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Internal Server Error",
                content = @Content(
                schema=@Schema(
                        implementation = ErrorResponseDTO.class
                )
        )
        ), @ApiResponse(
                responseCode = "417",
                description = "Expectation Failed"
        )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO){
        boolean isUpdated=iAccountService.updateAccount(customerDTO);
        if(isUpdated==true){
            return ResponseEntity.status(HttpStatus.OK).body
                    (new ResponseDTO(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountConstants.STATUS_417,AccountConstants.MESSAGE_417_UPDATE));
        }
    }
    @Operation(
            summary = "Delete Rest API for Accounts controller",
            description = "Rest API to Delete Customer,Account details inside EazyBank"
    )
    @ApiResponses ({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema=@Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )

            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})",message = "mobile number should be 10 digits")
                                                         String mobileNumber){
        boolean isDeleted= iAccountService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountConstants.STATUS_417,AccountConstants.MESSAGE_417_DELETE));
        }

    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }
}
