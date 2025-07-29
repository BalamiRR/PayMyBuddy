package com.balamir.paymybuddy.repository;

import com.balamir.paymybuddy.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Integer> {
    Friends findByFriendId(int id);
    List<Friends> findAllByUserId(int id);
    Integer deleteByFriendId(int id);
}
