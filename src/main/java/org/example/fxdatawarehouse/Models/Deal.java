package org.example.fxdatawarehouse.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "from_currency", nullable = false)
    private String fromCurrency;

    @Column(name = "to_currency", nullable = false)
    private String toCurrency;

    @Column(name = "deal_timestamp", nullable = false)
    private LocalDateTime dealTimestamp;

    @Column(name = "deal_amount", nullable = false)
    private Double amount;
}
