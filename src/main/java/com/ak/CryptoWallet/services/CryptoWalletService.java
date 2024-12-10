package com.ak.CryptoWallet.services;

import com.ak.CryptoWallet.entity.CryptoAccount;
import com.ak.CryptoWallet.entity.Transaction;
import org.springframework.stereotype.Service;

@Service
public class CryptoWalletService {

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
        return transaction;

    }


}
