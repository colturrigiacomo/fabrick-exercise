package com.fabrick.exercise.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fee {

    private String feeCode;
    private String description;
    private BigDecimal amount;
    private String currency;

}
