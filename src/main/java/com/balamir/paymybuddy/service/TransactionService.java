package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.Transaction;
import com.balamir.paymybuddy.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    void sendMoney(User sender, User receiver, BigDecimal amount, String description);
    List<Transaction> getMyTransactions(int userId);
}
