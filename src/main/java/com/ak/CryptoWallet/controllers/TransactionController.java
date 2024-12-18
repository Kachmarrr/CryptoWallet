package com.ak.CryptoWallet.controllers;

import com.ak.CryptoWallet.entity.Transaction;
import com.ak.CryptoWallet.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/accounts/{accId}/transactions")
    public List<Transaction> getTransactions(@PathVariable String accId){
        return transactionService.getTransactions(accId);
    }

    @PostMapping("/accounts/{accId}/transactions")
    public Transaction proccesTransaction(@PathVariable String accId, @RequestBody Transaction transaction) {
        return transactionService.proccesTransaction(accId, transaction);
    }

    @PutMapping("/accounts/{accId}/transactions/{trxId}")
    public Transaction updateTransaction(@PathVariable String accId, @PathVariable String trxId, @RequestBody Transaction newTrx){
        return transactionService.updateTransaction(accId, trxId, newTrx);
    }

    @DeleteMapping("/accounts/{accId}/transactions/{trxId}")
    public Transaction deleteTransaction(@PathVariable String accId, @PathVariable String trxId){
        return transactionService.deleteTransaction(accId, trxId);
    }
}