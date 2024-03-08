package com.microservices.accounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
public class ResponseDTO {
      @Schema(
              description = "Status code in Response"
      )
      private String statusCode;
      @Schema(
              description = "Status message in Response"
      )
      private String statusMsg;
}
