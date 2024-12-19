package com.ak.CryptoWallet.entity;

import java.util.HashMap;
import java.util.Map;

//@Getter
//@Setter
public class Account {

    private String id;
    private double balance;
    private String currency;

    private Map<String, Transaction> transactions = new HashMap<>();



    public Map<String, Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Map<String, Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
