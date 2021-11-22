package com.fabrick.exercise.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyTransfer {

    private String receiverName;
    private String description;
    private String accountCode;
    private String currency;
    private BigDecimal amount;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String executionDate;

}
