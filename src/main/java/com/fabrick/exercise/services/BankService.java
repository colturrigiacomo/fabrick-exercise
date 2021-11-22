package com.fabrick.exercise.services;

import com.fabrick.exercise.models.Balance;
import com.fabrick.exercise.models.PaymentResponse;
import com.fabrick.exercise.models.Transaction;
import com.fabrick.exercise.models.requests.MoneyTransfer;

import java.util.Date;
import java.util.List;

public interface BankService {

    Balance getBalance(String accountId);

    List<Transaction> getTransactions(String accountId, Date dateFrom, Date dateTo);

    PaymentResponse createMoneyTransfer(MoneyTransfer req, String accountId);
}
