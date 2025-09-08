package com.balamir.paymybuddy.repository;

import com.balamir.paymybuddy.model.Transaction;
import com.balamir.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findBySenderOrReceiver(User sender, User receiver);
}
