package com.ak.CryptoWallet.services;

import com.ak.CryptoWallet.entity.Account;
import com.ak.CryptoWallet.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class TransactionService {

    @Autowired
    private AccountService accountService;


    public Transaction proccesTransaction(String accId, Transaction transaction) {

        Account account = accountService.getCryptoAccount(accId);

        account.setBalance(account.getBalance() + transaction.getAmount());
        transaction.setId(UUID.randomUUID().toString());
        account.getTransactions().put(transaction.getId(), transaction);
        return transaction;

    }

    public List<Transaction> getTransactions(String accId) {
        return accountService.getCryptoAccount(accId).getTransactions().values().stream().collect(Collectors.toList());
    }

    public Transaction updateTransaction(String accId, String trxId, Transaction newTrx) {

        Account account = accountService.getCryptoAccount(accId);

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

        Account account = accountService.getCryptoAccount(accId);

        Transaction oldTrx = account.getTransactions().get(trxId);
        account.setBalance(account.getBalance() - oldTrx.getAmount());

        return account.getTransactions().remove(trxId);
    }
}
