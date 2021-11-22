package com.fabrick.exercise.mapper;

import com.fabrick.exercise.models.Creditor;
import com.fabrick.exercise.models.CreditorAccount;
import com.fabrick.exercise.models.Payment;
import com.fabrick.exercise.models.requests.MoneyTransfer;

public class Mapper {

    public static Payment mapMoneyTrasferToPayment(MoneyTransfer req) {
        return Payment.builder()
                .amount(req.getAmount())
                .currency(req.getCurrency())
                .description(req.getDescription())
                .executionDate(req.getExecutionDate())
                .creditor(
                        Creditor.builder()
                                .name(req.getReceiverName())
                                .account(
                                        CreditorAccount.builder()
                                        .accountCode(req.getAccountCode())
                                        .build()
                                )
                                .build()
                )
                .build();

    }
}
