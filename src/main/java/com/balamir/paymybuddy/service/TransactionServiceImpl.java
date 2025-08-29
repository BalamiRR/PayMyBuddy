package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.Account;
import com.balamir.paymybuddy.model.Transaction;
import com.balamir.paymybuddy.model.TransactionStatus;
import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.repository.AccountRepository;
import com.balamir.paymybuddy.repository.TransactionRepository;
import com.balamir.paymybuddy.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public void sendMoney(User sender, User receiver, BigDecimal amount, String description) {
        Account senderAccount = accountRepository.findByUserId(sender.getId());
        Account receiverAccount = accountRepository.findByUserId(receiver.getId());

        if (senderAccount == null) {
            throw new RuntimeException("Sender has no account!");
        }
        if (receiverAccount == null) {
            throw new RuntimeException("Receiver has no account!");
        }

        if (senderAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance!");
        }

        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        receiverAccount.setBalance(receiverAccount.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(amount);
        transaction.setCreatedAt(Instant.now());
        transaction.setDescription(description);
        transaction.setStatus(TransactionStatus.SUCCESS);
        transaction.setAccount(senderAccount);

        transactionRepository.save(transaction);
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);
    }

    @Override
    public List<Transaction> getMyTransactions(int userId) {
        User me = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepository.findBySenderOrReceiver(me, me)
                .stream().sorted(Comparator.comparing(Transaction::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

}
