package com.balamir.paymybuddy.service.unit;

import com.balamir.paymybuddy.model.Account;
import com.balamir.paymybuddy.model.Transaction;
import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.repository.AccountRepository;
import com.balamir.paymybuddy.repository.TransactionRepository;
import com.balamir.paymybuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.balamir.paymybuddy.service.TransactionServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    private User sender;
    private User receiver;
    private Account senderAccount;
    private Account receiverAccount;

    @BeforeEach
    void setUp(){
        sender = new User();
        sender.setId(1);

        receiver = new User();
        receiver.setId(2);

        senderAccount = new Account();
        senderAccount.setUser(sender);
        senderAccount.setBalance(BigDecimal.valueOf(100));

        receiverAccount = new Account();
        receiverAccount.setUser(receiver);
        receiverAccount.setBalance(BigDecimal.valueOf(50));
    }

    @Test
    void test_sendMoney_success(){
        BigDecimal amount = BigDecimal.valueOf(30);
        String description = "Payment";

        when(accountRepository.findByUserId(sender.getId())).thenReturn(senderAccount);
        when(accountRepository.findByUserId(receiver.getId())).thenReturn(receiverAccount);

        transactionServiceImpl.sendMoney(sender, receiver, amount, description);

        assertEquals(BigDecimal.valueOf(70), senderAccount.getBalance());
        assertEquals(BigDecimal.valueOf(80), receiverAccount.getBalance());

        verify(transactionRepository).save(any(Transaction.class));

        verify(accountRepository).save(senderAccount);
        verify(accountRepository).save(receiverAccount);
    }

    @Test
    void testGetMyTransactions() {
        Transaction tx1 = new Transaction();
        tx1.setSender(sender);
        tx1.setReceiver(receiver);
        tx1.setCreatedAt(Instant.now().minusSeconds(60));

        Transaction tx2 = new Transaction();
        tx2.setSender(receiver);
        tx2.setReceiver(sender);
        tx2.setCreatedAt(Instant.now());

        when(userRepository.findById(sender.getId())).thenReturn(Optional.of(sender));
        when(transactionRepository.findBySenderOrReceiver(sender, sender)).thenReturn(List.of(tx1, tx2));

        List<Transaction> result = transactionServiceImpl.getMyTransactions(sender.getId());

        assertEquals(2, result.size());
        assertEquals(tx2, result.get(0));
        assertEquals(tx1, result.get(1));
    }
}