package org.example.fxdatawarehouse.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealDTO {

    // The client request may include an id, which determines different handling in the service layer.
    private UUID id = null;

    @NotBlank(message = "fromCurrency is required")
    @Size(min = 2, max = 3, message = "fromCurrency must be between 2 and 3 characters")
    private String fromCurrency;

    @NotBlank(message = "toCurrency is required")
    @Size(min = 2, max = 3, message = "toCurrency must be between 2 and 3 characters")
    private String toCurrency;

    private String dealTimestamp;

    @NotBlank(message = "amount is required")
    @Min(value = 10, message = "amount must be greater than 10")
    private Double amount;
}
