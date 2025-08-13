package com.balamir.paymybuddy.controller;

import org.springframework.ui.Model;
import com.balamir.paymybuddy.model.Friends;
import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.model.dto.FriendDto;
import com.balamir.paymybuddy.service.FriendsService;
import com.balamir.paymybuddy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/contact")
public class FriendController {
    private final UserService userService;
    private final FriendsService friendsService;

    @GetMapping
    public String contactPage(Model model, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        List<User> myFriends = friendsService.findAllMyFriends(user.getId());
        model.addAttribute("active", "contact.html");
        model.addAttribute("newFriend", new FriendDto());
        model.addAttribute("friendList", myFriends == null ? new ArrayList<>() : myFriends);
        return "contact";
    }

    @PostMapping
    public String addFriend(Authentication authentication, @ModelAttribute("newFriend") FriendDto friendDto, Model model){
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        boolean success = false;

        String findEmail = friendDto.getFriendEmail();
        User friendToBe = userService.findByEmail(findEmail);

        if (friendToBe != null && friendToBe.getAccount() != null) {
            Friends friends = new Friends();
            friends.setUser(user);
            friends.setFriend(friendToBe);
            friendsService.save(friends);
            success = true;
        } else {
            log.error("The friend is the user OR has no account yet: {}", email);
        }

        User userA = userService.findByEmail(authentication.getName());
        List<User> myFriends = friendsService.findAllMyFriends(userA.getId());
        model.addAttribute("friendList", myFriends);
        model.addAttribute("newFriend", new FriendDto());
        model.addAttribute("success", success);
        return "contact";
    }

}
