package com.balamir.paymybuddy.controller;

import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.service.FriendsService;
import com.balamir.paymybuddy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/transfer")
public class TransferController {
    private final UserService userService;
    private final FriendsService friendsService;

    @GetMapping
    public String transferPage(Authentication authentication, Model model) {
        User user = userService.findByEmail(authentication.getName());
        List<User> myFriends = friendsService.findAllMyFriends(user.getId());
        model.addAttribute("friendList", myFriends);
        return "transfer";
    }

}
