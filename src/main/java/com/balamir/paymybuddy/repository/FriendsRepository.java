package com.balamir.paymybuddy.repository;

import com.balamir.paymybuddy.model.Friends;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepository extends JpaRepository <Friends, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Friends f WHERE (f.user.id = :userId AND f.friend.id = :friendId) " +
            "OR (f.user.id = :friendId AND f.friend.id = :userId)")
    void deleteByUserIdAndFriendId(@Param("userId") int userId, @Param("friendId") int friendId);
}
