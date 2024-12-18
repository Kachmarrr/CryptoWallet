package com.ak.CryptoWallet.controllers;

import com.ak.CryptoWallet.entity.Account;
import com.ak.CryptoWallet.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts/{accId}")
    public Account getCryptoAccount(@PathVariable String accId) {
        return accountService.getCryptoAccount(accId);
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @DeleteMapping("/accounts/{accId}")
    public Account deleteAccount(@PathVariable String accId){
        return accountService.deleteAccount(accId);
    }
}