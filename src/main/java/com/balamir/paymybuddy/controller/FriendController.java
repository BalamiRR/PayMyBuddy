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

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/friend")
public class FriendController {
    private UserService userService;
    private FriendsService friendsService;

    @PostMapping
    public String addFriend(Authentication authentication, @ModelAttribute("newFriend") FriendDto friendDto, Model model){
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        int errorType = 0;
        boolean success = false;

        String findEmail = friendDto.getFriendEmail();
        User friendToBe = userService.findByEmail(findEmail);

        if(friendToBe == null || friendToBe.getAccount() == null){
            log.error("The friend is the user OR has no account yet: {}", email);
            errorType = 4;
        } else {
            Friends friends = new Friends();
            friends.setUserId(user);
            friends.setFriend(friendToBe);
            friendsService.save(friends);
        }

        model.addAttribute("success", success);
        model.addAttribute("errorType", errorType);

        return "result";
    }

}
