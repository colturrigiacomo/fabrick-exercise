package com.fabrick.exercise.services;

import com.fabrick.exercise.exceptions.FabrickException;
import com.fabrick.exercise.models.Balance;
import com.fabrick.exercise.models.Transaction;
import com.fabrick.exercise.models.requests.MoneyTransfer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
class BankServiceImplTest {

    @Autowired
    private BankServiceImpl bankService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static final String ACCOUNT_ID = "14537780";

    @Test
    void getBalance() {
        Balance balance = bankService.getBalance(ACCOUNT_ID);
        assertNotNull(balance);
    }

    @Test
    void getBalanceError() {
        assertThrows(FabrickException.class, () -> bankService.getBalance("1453778"));
    }

    @Test
    void getTransactions() throws ParseException {
        List<Transaction> transactions = bankService.getTransactions(ACCOUNT_ID, sdf.parse("2019-01-01"),
                sdf.parse("2019-12-01"));
        assertNotNull(transactions);
    }

    @Test
    void createMoneyTransfer() {
        MoneyTransfer moneyTransfer = new MoneyTransfer("John Doe", "test",
                "IT23A0336844430152923804660", "EUR", BigDecimal.valueOf(800), "2021-11-23");
        assertThrows(FabrickException.class, () -> bankService.createMoneyTransfer(moneyTransfer, ACCOUNT_ID));
    }
}