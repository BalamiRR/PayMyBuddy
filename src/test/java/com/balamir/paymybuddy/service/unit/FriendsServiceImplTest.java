package com.balamir.paymybuddy.service.unit;


import com.balamir.paymybuddy.model.Friends;
import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.repository.FriendsRepository;
import com.balamir.paymybuddy.repository.UserRepository;
import com.balamir.paymybuddy.service.FriendsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FriendsServiceImplTest {

    @Mock
    private FriendsRepository friendsRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FriendsServiceImpl friendsService;

    @Test
    void testSave() {
        Friends friends = new Friends();
        friendsService.save(friends);
        verify(friendsRepository, times(1)).save(any(Friends.class));
    }

    @Test
    void testFindAllMyFriends_UserExists() {
        User user = new User();
        user.setId(1);

        User friend1 = new User();
        friend1.setId(2);

        User friend2 = new User();
        friend2.setId(3);

        Friends f1 = new Friends();
        f1.setUser(user);
        f1.setFriend(friend1);

        Friends f2 = new Friends();
        f2.setUser(user);
        f2.setFriend(friend2);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(friendsRepository.findAll()).thenReturn(Arrays.asList(f1, f2));

        List<User> result = friendsService.findAllMyFriends(1);

        assertThat(result).containsExactlyInAnyOrder(friend1, friend2);
    }

    @Test
    void testFindAllMyFriends_UserNotExists() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        List<User> result = friendsService.findAllMyFriends(1);

        assertThat(result).isEmpty();
    }

    @Test
    void testIsAlreadyFriend_WhenFriendExists() {
        User user = new User();
        user.setId(1);

        User friend = new User();
        friend.setId(2);

        Friends f = new Friends();
        f.setUser(user);
        f.setFriend(friend);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(friendsRepository.findAll()).thenReturn(Collections.singletonList(f));

        boolean result = friendsService.isAlreadyFriend(1, 2);

        assertThat(result).isTrue();
    }

    @Test
    void testIsAlreadyFriend_WhenFriendNotExists() {
        User user = new User();
        user.setId(1);

        User friend = new User();
        friend.setId(2);

        Friends f = new Friends();
        f.setUser(user);
        f.setFriend(friend);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(friendsRepository.findAll()).thenReturn(Collections.singletonList(f));

        boolean result = friendsService.isAlreadyFriend(1, 3);

        assertThat(result).isFalse();
    }

    @Test
    void testDeleteFriendship() {
        friendsService.deleteFriendship(1, 2);

        verify(friendsRepository, times(1)).deleteByUserIdAndFriendId(1, 2);
    }
}