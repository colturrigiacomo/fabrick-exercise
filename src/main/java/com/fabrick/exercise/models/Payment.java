package com.fabrick.exercise.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private Creditor creditor;
    private String description;
    private BigDecimal amount;
    private String executionDate;
    private String currency;

}
