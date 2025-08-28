package com.balamir.paymybuddy.service.integration;

import com.balamir.paymybuddy.model.Account;
import com.balamir.paymybuddy.model.Transaction;
import com.balamir.paymybuddy.model.TransactionStatus;
import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.model.dto.TransactionDto;
import com.balamir.paymybuddy.repository.AccountRepository;
import com.balamir.paymybuddy.repository.TransactionRepository;
import com.balamir.paymybuddy.repository.UserRepository;
import com.balamir.paymybuddy.service.TransactionService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionServiceImplTest {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private User sender;
    private User receiver;

    @BeforeAll
    void setUp() {
        sender = new User();
        sender.setUserName("Alice");
        sender.setEmail("alice@example.com");
        sender.setPassword("123");
        sender = userRepository.save(sender);

        receiver = new User();
        receiver.setUserName("Bob");
        receiver.setEmail("bob@example.com");
        receiver.setPassword("123");
        receiver = userRepository.save(receiver);

        Account senderAccount = new Account();
        senderAccount.setUser(sender);
        senderAccount.setBalance(new BigDecimal("1000.00"));
        senderAccount.setCreatedAt(Instant.now());
        senderAccount.setUpdatedAt(Instant.now());
        accountRepository.save(senderAccount);

        Account receiverAccount = new Account();
        receiverAccount.setUser(receiver);
        receiverAccount.setBalance(new BigDecimal("500.00"));
        receiverAccount.setBalance(new BigDecimal("500.00"));
        receiverAccount.setCreatedAt(Instant.now());
        receiverAccount.setUpdatedAt(Instant.now());
        accountRepository.save(receiverAccount);
    }

    @AfterAll
    void cleanUp() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Order(1)
    void testSendMoneySuccess() {
        BigDecimal amount = new BigDecimal("200.00");
        transactionService.sendMoney(sender, receiver, amount, "Rent payment");

        Account senderAccount = accountRepository.findByUserId(sender.getId());
        Account receiverAccount = accountRepository.findByUserId(receiver.getId());

        assertEquals(new BigDecimal("800.00"), senderAccount.getBalance());
        assertEquals(new BigDecimal("700.00"), receiverAccount.getBalance());

        List<Transaction> senderTransactions = transactionService.getMyTransactions(sender.getId());
        assertEquals(1, senderTransactions.size());
        assertEquals(TransactionStatus.SUCCESS, senderTransactions.get(0).getStatus());
    }

    @Test
    @Order(2)
    void testSendMoneyInsufficientBalance() {
        BigDecimal largeAmount = new BigDecimal("5000.00");
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                transactionService.sendMoney(sender, receiver, largeAmount, "Too large payment"));
        assertEquals("Insufficient balance !", exception.getMessage());
    }

    @Test
    @Order(3)
    void testGetMyTransactionsDirection() {
        List<Transaction> senderTransactions = transactionService.getMyTransactions(sender.getId());
        TransactionDto dto = new TransactionDto(senderTransactions.get(0), sender.getId());

        assertEquals("OUTGOING", dto.getDirection());
        assertEquals("Bob", dto.getUserName());

        TransactionDto dtoReceiver = new TransactionDto(senderTransactions.get(0), receiver.getId());
        assertEquals("INCOMING", dtoReceiver.getDirection());
        assertEquals("Alice", dtoReceiver.getUserName());
    }

}
