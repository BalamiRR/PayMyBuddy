package com.balamir.paymybuddy.service.integration;

import com.balamir.paymybuddy.model.Account;
import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.repository.AccountRepository;
import com.balamir.paymybuddy.repository.UserRepository;
import com.balamir.paymybuddy.service.AccountService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class AccountServiceImplTest {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setUserName("tester");
        user.setPassword("1234");

        userRepository.save(user);

        Account account = new Account();
        account.setUser(user);
        account.setBalance(BigDecimal.TEN);
        account.setCreatedAt(Instant.now());
        account.setUpdatedAt(Instant.now());
        accountRepository.save(account);
    }

    @Test
    void test_findByUserId_shouldReturnAccount() {
        Account result = accountService.findByUserId(user.getId());

        assertThat(result).isNotNull();
        assertThat(result.getUser().getEmail()).isEqualTo("test@example.com");
        assertThat(result.getBalance()).isEqualByComparingTo(BigDecimal.TEN);
    }

}
