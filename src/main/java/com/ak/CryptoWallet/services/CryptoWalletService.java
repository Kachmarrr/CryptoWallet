package com.ak.CryptoWallet.services;

import com.ak.CryptoWallet.entity.CryptoAccount;
import com.ak.CryptoWallet.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CryptoWalletService {

    //питання НЕ до теми пояснити на чому всередині та як працює ArrayList
    //private List<Transaction> transactions = new ArrayList<>();
    private Map<String, Transaction> transactions = new HashMap<>();

    CryptoAccount ca = new CryptoAccount();

    public CryptoWalletService() {
        ca.setBalance(1700.00);
        ca.setCurrency("EUR");
    }

    public CryptoAccount getCryptoAccount() {
        return ca;
    }

    public Transaction proccesTransaction(Transaction transaction) {
        ca.setBalance(ca.getBalance() + transaction.getAmount());
        transaction.setId(UUID.randomUUID().toString());
        transactions.put(transaction.getId(), transaction);
        return transaction;

    }

    public List<Transaction> getTransactions() {
        return transactions.values().stream().collect(Collectors.toList());
    }

    public Transaction updateTransaction(String id, Transaction newTrx) {

        if (transactions.containsKey(id)) {
            /// T1 FIND
            Transaction oldTrx = transactions.get(id);

            /// T2 Revert and Update Balance
            ca.setBalance(ca.getBalance() - oldTrx.getAmount());
            ca.setBalance(ca.getBalance() + newTrx.getAmount());

            /// T3 Update old trx
            oldTrx.setAmount(newTrx.getAmount());
            return oldTrx;
        }

        throw new IllegalArgumentException("Transaction not found");
    }
    //попросити розказати структуру

    // що повинен повертати метод ? він повинен показувати транзакцію яку ми видалили, чи void і нічого не повертати
    // зробив так, щоб повертало транзакцію яку видалило
    public Transaction deleteTransaction(String id) {
        return transactions.remove(id);
    }

}