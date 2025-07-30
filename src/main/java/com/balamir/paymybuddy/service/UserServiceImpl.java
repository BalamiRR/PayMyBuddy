package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
