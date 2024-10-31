package org.example.fxdatawarehouse.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealDTO {

    // The client request may include an id, which determines different handling in the service layer.
    @Builder.Default
    private String id = "";

    @NotBlank(message = "fromCurrency is required")
    @Size(min = 3, max = 3, message = "fromCurrency must be 3 characters")
    private String fromCurrency;

    @NotBlank(message = "toCurrency is required")
    @Size(min = 3, max = 3, message = "toCurrency must be 3 characters")
    private String toCurrency;

    @NotBlank(message = "amount is required")
    @Min(value = 10, message = "amount must be greater than 10")
    private Integer amount;
}
