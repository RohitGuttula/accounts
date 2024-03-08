package com.microservices.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name="error response",
        description = "schema to hold error response"
)
public class ErrorResponseDTO {

    private String apiPath;
    @Schema(
            description = "error code representing error happened"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "error message representing error happened"
    )
    private String errorMsg;
    @Schema(
            description = "error time representing error happened"
    )
    private LocalDateTime errorTime;
}
