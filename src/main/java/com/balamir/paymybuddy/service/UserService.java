package com.balamir.paymybuddy.service;

import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.model.dto.UserDto;

public interface UserService {
    User findByEmail(String email);
    void save(UserDto userDto);
    User getById(int id);
    void updatePassword(int id, String newPassword);
}
