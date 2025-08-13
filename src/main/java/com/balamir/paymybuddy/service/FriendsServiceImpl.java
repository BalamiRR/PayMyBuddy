package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.Friends;
import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.repository.FriendsRepository;
import com.balamir.paymybuddy.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
@Transactional
public class FriendsServiceImpl implements FriendsService {
    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    public void save(Friends friends) {
        friendsRepository.save(friends);
    }

    @Override
    public Friends findByFriend_Id(int id) {
        return friendsRepository.findByFriend_Id(id);
    }

    @Override
    public List<User> findAllMyFriends(int id) {
        List<User> friendSet = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        List<Friends> friends = friendsRepository.findAll();

        if(user.isPresent()){
            for(Friends friend : friends){
                if(friend.getFriend().getId() == id){
                    friendSet.add(friend.getUser());
                } else if (friend.getUser().getId() == id) {
                    friendSet.add(friend.getFriend());
                }
            }
        }
        return friendSet;
    }

}
