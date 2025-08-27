package com.balamir.paymybuddy.service.unit;

import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.model.dto.UserDto;
import com.balamir.paymybuddy.repository.UserRepository;
import com.balamir.paymybuddy.service.UserServiceImpl;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void test_findByEmail(){
        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        user.setUserName("TestUser");

        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        User result = userServiceImpl.findByEmail("test@example.com");

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        assertEquals("TestUser", result.getUserName());

        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void test_save(){
        UserDto userDto = new UserDto();
        userDto.setUserName("TestUser");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        userServiceImpl.save(userDto);

        verify(userRepository).save(any(User.class));
    }

    @Test
    void test_getById(){
        User user = new User();
        user.setId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userServiceImpl.getById(1);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
    }

    @Test
    void test_updatePassword() {
        User user = new User();
        user.setId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedPassword");

        userServiceImpl.updatePassword(1, "newPassword");

        assertThat(user.getPassword()).isEqualTo("encodedPassword");
        verify(userRepository).save(user);
    }
}