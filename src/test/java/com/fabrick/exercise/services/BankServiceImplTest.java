package com.fabrick.exercise.services;

import com.fabrick.exercise.models.Balance;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class BankServiceImplTest {

    @Mock
    private RestTemplate restTemplate = new RestTemplate();

    @InjectMocks
    private BankService bankService = new BankServiceImpl();

    private static final String URL = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts";

    @Test
    void getBalance() {
        Balance response = new Balance(new Date(), BigDecimal.valueOf(9), BigDecimal.valueOf(9), "EUR");
        Mockito
            .when(restTemplate.getForEntity(URL + "/14537780/balance", Balance.class))
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