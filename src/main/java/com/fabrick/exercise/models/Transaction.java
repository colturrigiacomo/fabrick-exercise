package com.fabrick.exercise.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private String transactionId;
    private String operationId;
    private Date accountingDate;
    private Date valueDate;
    private BigDecimal amount;
    private String currency;
    private String description;
    private TransactionType type;

}
