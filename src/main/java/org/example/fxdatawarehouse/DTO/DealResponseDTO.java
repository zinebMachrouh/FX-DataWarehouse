package org.example.fxdatawarehouse.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DealResponseDTO{
    protected String id;

    @NotBlank(message = "from currency is required")
    @Size(min = 3, max = 3, message = "fromCurrency must be 3 characters")
    private String fromCurrency;

    @NotBlank(message = "to currency is required")
    @Size(min = 3, max = 3, message = "toCurrency must be 3 characters")
    private String toCurrency;

    @NotBlank(message = "amount is required")
    @Min(value = 10, message = "amount must be greater than 10")
    private Integer amount;

    private LocalDateTime dealTime;
}
