package com.fabrick.exercise.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Balance {

    private Date date;
    private BigDecimal balance;
    private BigDecimal availableBalance;
    private String currency;

}
