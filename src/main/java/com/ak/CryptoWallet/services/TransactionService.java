package com.ak.CryptoWallet.services;

import com.ak.CryptoWallet.entity.Account;
import com.ak.CryptoWallet.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TransactionService {

    @Autowired
    private AccountService accountService;

    public Transaction proccesTransaction(String accId, Transaction transaction) {

        Account account = accountService.getCryptoAccount(accId);

        account.setBalance(account.getBalance() + transaction.getAmount());
        transaction.setId(UUID.randomUUID().toString());
        account.getTransactions().add(transaction);
//        account.getTransactions().put(transaction.getId(), transaction);
        return transaction;

    }

    public List<Transaction> getTransactions(String accId) {
        Account account = accountService.getCryptoAccount(accId);
        return account.getTransactions();
        //return accountService.getCryptoAccount(accId).getTransactions().values().stream().collect(Collectors.toList());
    }

    // input needs
    // accId = account ID, trxId = transaction ID, newTrx = updated transaction
    ///rev
    public Transaction updateTransaction(String accId, String trxId, Transaction newTrx) {

        Account account = accountService.getCryptoAccount(accId);

        /// T1 FIND
        Transaction updateTrx = account.getTransactions().stream()
                .filter(transaction -> transaction.getId().equals(trxId))
                .findFirst()
                .orElse(null);

        /// T2 Revert and Update Balance
        if (updateTrx != null) {

            account.setBalance(account.getBalance() - updateTrx.getAmount());

            account.setBalance(account.getBalance() + newTrx.getAmount());
        }

        /// T3 Update old trx
        updateTrx.setAmount(newTrx.getAmount());

        return updateTrx;

    }

    /// rev
    public Transaction deleteTransaction(String accId, String trxId) {

        Account account = accountService.getCryptoAccount(accId);

        Transaction trxDell = account.getTransactions().stream()
                .filter(transaction -> transaction.getId().equals(trxId))
                .findFirst()
                .orElse(null);

        if (trxDell != null) {
            account.getTransactions().remove(trxDell);

            account.setBalance(account.getBalance() - trxDell.getAmount());
        }

        return trxDell;
    }
}
