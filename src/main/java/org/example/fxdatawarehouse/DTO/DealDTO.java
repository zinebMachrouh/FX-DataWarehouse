package org.example.fxdatawarehouse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DealDTO {
    private Long id;
    private String fromCurrency;
    private String toCurrency;
    private String dealTimestamp;
    private Double amount;
}
