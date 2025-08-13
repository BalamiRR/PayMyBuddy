package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.Friends;
import com.balamir.paymybuddy.model.User;

import java.util.List;

public interface FriendsService {
    void save(Friends friends);
    List<User> findAllMyFriends(int id);
    void deleteFriendship(int userId, int friendId);
    boolean isAlreadyFriend(int userId, int friendId);

}
