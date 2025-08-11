package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.Friends;

public interface FriendsService {
    void save(Friends friends);
    Friends findByFriend_Id(String email);
}
