package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.Account;
import com.balamir.paymybuddy.repository.AccountRepository;
import com.balamir.paymybuddy.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public Account findByUserId(int id) {
        return accountRepository.findByUserId(id);
    }

    @Override
    public Account addMoney(int userId, BigDecimal amount, String currency) {
        Account account = accountRepository.findByUserId(userId);
        if (account == null) {
            throw new RuntimeException("Account not found for user id: " + userId);
        }
        if (account.getBalance() == null) {
            account.setBalance(BigDecimal.ZERO);
        }

        if ("USD".equalsIgnoreCase(currency)) {
            amount = amount.divide(BigDecimal.valueOf(1.17), 2, RoundingMode.HALF_UP);
        }

        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

}
