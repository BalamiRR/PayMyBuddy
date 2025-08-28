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


}