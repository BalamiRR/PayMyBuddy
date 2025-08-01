package com.balamir.paymybuddy.repository;

import com.balamir.paymybuddy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository <Account, Integer> {
    Account findByUserId_Id(int id);
}
