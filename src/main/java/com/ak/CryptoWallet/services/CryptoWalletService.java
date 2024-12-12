package com.ak.CryptoWallet.services;

import com.ak.CryptoWallet.entity.Account;
import com.ak.CryptoWallet.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CryptoWalletService {

    // key = AccountId(String), value = Account
    // дабавити exceptions to methods
    private Map<String, Account> accounts = new HashMap<>();

    public Account getCryptoAccount(String id) {
        if (accounts.containsKey(id)) {
            return accounts.get(id);
        }
        throw new IllegalArgumentException("Account with id " + id + " not found");
    }

    public Transaction proccesTransaction(String accId, Transaction transaction) {

        Account account = getCryptoAccount(accId);

        account.setBalance(account.getBalance() + transaction.getAmount());
        transaction.setId(UUID.randomUUID().toString());
        account.getTransactions().put(transaction.getId(), transaction);
        return transaction;

    }

    public List<Transaction> getTransactions(String accId) {
        return getCryptoAccount(accId).getTransactions().values().stream().collect(Collectors.toList());
    }

    public Transaction updateTransaction(String accId, String trxId, Transaction newTrx) {

        Account account = getCryptoAccount(accId);

        if (account.getTransactions().containsKey(trxId)) {

            /// T1 FIND
            Transaction oldTrx = account.getTransactions().get(trxId);

            /// T2 Revert and Update Balance
            account.setBalance(account.getBalance() - oldTrx.getAmount());
            account.setBalance(account.getBalance() + newTrx.getAmount());

            /// T3 Update old trx
            oldTrx.setAmount(newTrx.getAmount());
            return oldTrx;
        }

        throw new IllegalArgumentException("Transaction not found");
    }

    public Transaction deleteTransaction(String accId, String trxId) {

        Account account = getCryptoAccount(accId);

        Transaction oldTrx = account.getTransactions().get(trxId);
        account.setBalance(account.getBalance() - oldTrx.getAmount());

        return account.getTransactions().remove(trxId);
    }

    public Account createAccount(Account account) {

        account.setId(UUID.randomUUID().toString());
        accounts.put(account.getId(), account);

        return account;
    }

    public Account deleteAccount(String accId) {
        return accounts.remove(accId);
    }
}