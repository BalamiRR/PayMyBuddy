package com.balamir.paymybuddy.model.dto;

import com.balamir.paymybuddy.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class FriendDto {
    @NotNull(message = "User cannot be null")
    private User user;

    @NotBlank(message = "Email cannot be blank")
    @Size(min = 6, message = "Email must be at least 6 characters long")
    private String friendEmail;
}
