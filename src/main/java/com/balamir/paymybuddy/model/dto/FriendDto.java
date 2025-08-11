package com.balamir.paymybuddy.model.dto;

import com.balamir.paymybuddy.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class FriendDto {
    private User user;
    private String friendEmail;
}
