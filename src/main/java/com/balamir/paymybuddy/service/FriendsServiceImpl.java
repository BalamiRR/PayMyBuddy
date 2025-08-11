package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.Friends;
import com.balamir.paymybuddy.model.dto.FriendDto;
import com.balamir.paymybuddy.repository.FriendsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class FriendsServiceImpl implements FriendsService {
    private final FriendsRepository friendsRepository;

    @Override
    public void save(Friends friends) {

    }

    @Override
    public Friends findByFriend_Id(String email) {
        return null;
    }
}
