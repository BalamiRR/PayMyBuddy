package com.balamir.paymybuddy.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UserDto {
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 6, message = "Username must be at least 6 characters long")
    private String userName;

    @NotBlank(message = "Email cannot be blank")
    @Size(min = 6, message = "Email must be at least 6 characters long")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
}
