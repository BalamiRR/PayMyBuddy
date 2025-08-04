package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.model.dto.UserDto;
import com.balamir.paymybuddy.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

            userRepository.save(newUser);
        }
    }


}
