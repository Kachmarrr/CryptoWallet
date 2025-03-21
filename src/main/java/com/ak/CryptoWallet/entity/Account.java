package com.ak.CryptoWallet.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Account {

    private String id;
    private double balance;
    private String currency;

    //private Map<String, Transaction> transactions = new HashMap<>();
    private List<Transaction> transactions = new ArrayList<Transaction>();

}
