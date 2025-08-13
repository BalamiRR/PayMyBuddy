package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.Friends;
import com.balamir.paymybuddy.model.User;

import java.util.List;

public interface FriendsService {
    void save(Friends friends);
    Friends findByFriend_Id(int id);
    List<User> findAllMyFriends(int id);

}
