package com.ak.CryptoWallet.services;

import com.ak.CryptoWallet.entity.Account;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    // дабавити exceptions to methods

    private List<Account> accaunts = new ArrayList<>();
    // key = AccountId(String), value = Account
    //private Map<String, Account> accounts = new HashMap<>();

    public Account getCryptoAccount(String id) {
        if (accaunts.contains(id)) {
            return accaunts.get(accaunts.indexOf(id));
        }
        throw new IllegalArgumentException("Account with id " + id + " not found");
    }

    public Account createAccount(Account account) {

        account.setId(UUID.randomUUID().toString());
        accaunts.add(account);

        return account;
    }

    public Account deleteAccount(String id) {
        if (accaunts.contains(id)) {
            accaunts.remove(id);
            return accaunts.get(accaunts.indexOf(id));
        }
        throw new IllegalArgumentException("Account with id " + id + " not found");
    }
}