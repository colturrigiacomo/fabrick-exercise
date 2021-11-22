package com.fabrick.exercise.controllers;

import com.fabrick.exercise.exceptions.FabrickException;
import com.fabrick.exercise.models.requests.MoneyTransfer;
import com.fabrick.exercise.services.BankService;
import com.fabrick.exercise.utility.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/banking/accounts")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<?> getBalance(@PathVariable String accountId) {
        try {
            return ResponseEntity.ok(bankService.getBalance(accountId));
        } catch (FabrickException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getErrors());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtil.genericError(e.getMessage()));
        }
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<?> getTransactions(@PathVariable String accountId,
                                             @RequestParam("fromAccountingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFrom,
                                             @RequestParam("toAccountingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateTo) {
        try {
            return ResponseEntity.ok(bankService.getTransactions(accountId, dateFrom, dateTo));
        } catch (FabrickException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getErrors());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtil.genericError(e.getMessage()));
        }
    }

    @PostMapping("/{accountId}/payments/money-transfers")
    public ResponseEntity<?> createMoneyTransfer(@PathVariable String accountId, @RequestBody MoneyTransfer req) {
        try {
            return ResponseEntity.ok(bankService.createMoneyTransfer(req, accountId));
        } catch (FabrickException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getErrors());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtil.genericError(e.getMessage()));
        }
    }
}
