package com.balamir.paymybuddy.repository;

import com.balamir.paymybuddy.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepository extends JpaRepository <Friends, Integer> {
    Friends findByFriend_Id(int id);

}
