package com.fabrick.exercise.services;

import com.fabrick.exercise.models.Balance;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BankService bankService;

    private static final String URL = "http://localhost:8080";

    @Test
    void getBalance() {
        Balance response = new Balance(new Date(), BigDecimal.valueOf(9), BigDecimal.valueOf(9), "EUR");
        Mockito
            .when(restTemplate.getForEntity(URL + "api/banking/accounts/14537780/balance", Balance.class))
            .thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        Balance balance = bankService.getBalance("14537780");
        assertEquals(response, balance);
    }

    @Test
    void getTransactions() {
    }

    @Test
    void createMoneyTransfer() {
    }
}