package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.Account;
import com.balamir.paymybuddy.repository.AccountRepository;
import com.balamir.paymybuddy.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
