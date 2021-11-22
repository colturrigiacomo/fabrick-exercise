package com.fabrick.exercise.services;

import com.fabrick.exercise.models.Balance;
import com.fabrick.exercise.models.requests.MoneyTransfer;

import java.util.Date;

public interface BankService {

    Balance getBalance(String accountId);

    Object getTransactions(String accountId, Date dateFrom, Date dateTo);

    Object createMoneyTransfer(MoneyTransfer req, String accountId);
}
