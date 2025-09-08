package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.Account;
import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.model.dto.UserDto;
import com.balamir.paymybuddy.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(UserDto userDto) {
        if(userDto != null){
            User newUser = new User();
            newUser.setUserName(userDto.getUserName());
            newUser.setEmail(userDto.getEmail());

            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            newUser.setPassword(encodedPassword);

            Account account = new Account();
            account.setUser(newUser);
            account.setBalance(BigDecimal.ZERO);
            account.setCreatedAt(Instant.now());
            account.setUpdatedAt(Instant.now());

            newUser.setAccount(account);
            userRepository.save(newUser);
        }
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public void updatePassword(int id, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

}
