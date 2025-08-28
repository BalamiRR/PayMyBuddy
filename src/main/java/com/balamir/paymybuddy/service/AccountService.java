package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.Account;

public interface AccountService {
    Account findByUserId(int id);
}
