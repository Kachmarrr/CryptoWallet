package com.ak.CryptoWallet.controllers;

import com.ak.CryptoWallet.entity.Account;
import com.ak.CryptoWallet.entity.Transaction;
import com.ak.CryptoWallet.services.CryptoWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CryptoWalletController {

    @Autowired
    private CryptoWalletService cryptoWalletService;

    // request
    @GetMapping("/accounts/{accId}")
    public Account getCryptoAccount(@PathVariable String accId) {
        return cryptoWalletService.getCryptoAccount(accId);
    }

    @PostMapping("/accounts/{accId}/transactions")
    public Transaction proccesTransaction(@PathVariable String accId, @RequestBody Transaction transaction) {
        return cryptoWalletService.proccesTransaction(accId, transaction);
    }

    @GetMapping("/accounts/{accId}/transactions")
    public List<Transaction> getTransactions(@PathVariable String accId){
        return cryptoWalletService.getTransactions(accId);
    }

    @PutMapping("/accounts/{accId}/transactions/{trxId}")
    public Transaction updateTransaction(@PathVariable String accId, @PathVariable String trxId, @RequestBody Transaction newTrx){
        return cryptoWalletService.updateTransaction(accId, trxId, newTrx);
    }

    @DeleteMapping("/accounts/{accId}/transactions/{trxId}")
    public Transaction deleteTransaction(@PathVariable String accId, @PathVariable String trxId){
        return cryptoWalletService.deleteTransaction(accId, trxId);
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account) {
        return cryptoWalletService.createAccount(account);
    }

    @DeleteMapping("/accounts/{accId}")
    public Account deleteAccount(@PathVariable String accId){
        return cryptoWalletService.deleteAccount(accId);
    }
}