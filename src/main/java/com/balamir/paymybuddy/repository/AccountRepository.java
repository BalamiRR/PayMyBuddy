package com.balamir.paymybuddy.repository;

import com.balamir.paymybuddy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository <Account, Integer> {
    Account findByUserId(int id);
    void update(Integer id, BigDecimal balance);
}
