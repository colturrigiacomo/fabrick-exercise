package com.fabrick.exercise.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Amount {

    private BigDecimal debtorAmount;
    private String debtorCurrency;
    private BigDecimal creditorAmount;
    private String creditorCurrency;
    private Date creditorCurrencyDate;
    private BigDecimal exchangeRate;

}
