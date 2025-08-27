package com.balamir.paymybuddy.service.unit;

import com.balamir.paymybuddy.model.Account;
import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.repository.AccountRepository;
import com.balamir.paymybuddy.repository.UserRepository;
import com.balamir.paymybuddy.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Test
    void test_findByUserId(){
        User user = new User();
        user.setId(1);

        Account account = new Account();
        account.setUser(user);
        account.setBalance(BigDecimal.TEN);

        when(accountRepository.findByUserId(1)).thenReturn(account);

        Account result = accountServiceImpl.findByUserId(1);

        assertThat(result).isNotNull();
        assertThat(result.getUser()).isEqualTo(user);
        assertThat(result.getBalance()).isEqualByComparingTo(BigDecimal.TEN);
    }

    @Test
    void test_findByUserId_NotExist(){
        when(accountRepository.findByUserId(1)).thenReturn(null);
        Account result = accountServiceImpl.findByUserId(1);
        assertThat(result).isNull();
    }

    @Test
    void test_addMoney(){
        User user = new User();
        user.setId(1);

        Account account = new Account();
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);

        when(accountRepository.findByUserId(1)).thenReturn(account);

        BigDecimal amountToAdd = BigDecimal.valueOf(100);
        accountServiceImpl.addMoney(1, amountToAdd);

        assertEquals(amountToAdd, account.getBalance());
        verify(accountRepository).save(account);
    }

//    @Test
//    void test_addMoney_NegativeAmount() {
//        User user = new User();
//        user.setId(1);
//
//        Account account = new Account();
//        account.setUser(user);
//        account.setBalance(BigDecimal.ZERO);
//
//        when(accountRepository.findByUserId(1)).thenReturn(account);
//
//        BigDecimal negativeAmount = BigDecimal.valueOf(-50);
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            accountServiceImpl.addMoney(1, negativeAmount);
//        });
//
//        verify(accountRepository, never()).save(account);
//    }

    @Test
    void test_addMoney_AccountNotFound() {
        when(accountRepository.findByUserId(1)).thenReturn(null);

        BigDecimal amount = BigDecimal.valueOf(50);

        assertThrows(RuntimeException.class, () -> {
            accountServiceImpl.addMoney(1, amount);
        });
    }

    @Test
    void test_addMoney_NullBalance() {
        User user = new User();
        user.setId(1);

        Account account = new Account();
        account.setUser(user);
        account.setBalance(null);

        when(accountRepository.findByUserId(1)).thenReturn(account);

        BigDecimal amount = BigDecimal.valueOf(75);
        accountServiceImpl.addMoney(1, amount);

        assertEquals(amount, account.getBalance());
        verify(accountRepository).save(account);
    }
}