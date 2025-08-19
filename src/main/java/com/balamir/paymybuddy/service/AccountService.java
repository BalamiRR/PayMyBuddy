package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.Account;
import java.math.BigDecimal;

public interface AccountService {
    Account findByUserId(int id);
    void addMoney(int userId, BigDecimal amount, String currency);
}
