package com.ak.CryptoWallet.services;

import com.ak.CryptoWallet.entity.Account;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    // дабавити exceptions to methods


    // key = AccountId(String), value = Account
    private Map<String, Account> accounts = new HashMap<>();

    public Account getCryptoAccount(String id) {
        if (accounts.containsKey(id)) {
            return accounts.get(id);
        }
        throw new IllegalArgumentException("Account with id " + id + " not found");
    }

    public Account createAccount(Account account) {

        account.setId(UUID.randomUUID().toString());
        accounts.put(account.getId(), account);

        return account;
    }

    public Account deleteAccount(String id) {
        if (accounts.containsKey(id)) {
            Account account = accounts.get(id);
            accounts.remove(id);
        }

        throw new IllegalArgumentException("Account with id " + id + " not found");
    }
}