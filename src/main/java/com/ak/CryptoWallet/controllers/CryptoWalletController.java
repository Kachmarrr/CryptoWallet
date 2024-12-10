package com.ak.CryptoWallet.controllers;

import com.ak.CryptoWallet.entity.CryptoAccount;
import com.ak.CryptoWallet.entity.Transaction;
import com.ak.CryptoWallet.services.CryptoWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoWalletController {
    @Autowired
    private CryptoWalletService cryptoWalletService;

    // request
    @GetMapping("/account")
    public CryptoAccount getCryptoAccount() {
        return cryptoWalletService.getCryptoAccount();
    }

    @PostMapping("/transaction")
    public Transaction proccesTransaction(@RequestBody Transaction transaction) {
        return cryptoWalletService.proccesTransaction(transaction);
    }




}
