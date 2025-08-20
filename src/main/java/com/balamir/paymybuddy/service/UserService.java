package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.model.dto.UserDto;

import java.util.Optional;

public interface UserService {
    User findByEmail(String email);
    void save(UserDto userDto);
    User getById(int id);
}
