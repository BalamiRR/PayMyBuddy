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
import org.springframework.web.bind.annotation.*;

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
        return buildContactPage(model, user, null, false);
    }

    @PostMapping
    public String addFriend(Authentication authentication, @ModelAttribute("newFriend") FriendDto friendDto, Model model) {
        User user = userService.findByEmail(authentication.getName());
        String targetEmail = friendDto.getFriendEmail();

        if (user.getEmail().equalsIgnoreCase(targetEmail)) {
            log.error("You cannot add yourself as a friend.");
            return buildContactPage(model, user, "You cannot add yourself as a friend.", true);
        }

        User friendToBe = userService.findByEmail(targetEmail);
        if (friendToBe == null || friendToBe.getAccount() == null) {
            log.error("This user does not exist or has no account yet.");
            return buildContactPage(model, user, "This user does not exist or has no account yet.", true);
        }

        if (friendsService.isAlreadyFriend(user.getId(), friendToBe.getId())) {
            log.error("This user is already your friend.");
            return buildContactPage(model, user, "This user is already your friend.", true);
        }

        Friends friends = new Friends();
        friends.setUser(user);
        friends.setFriend(friendToBe);

        friendsService.save(friends);
        log.info("Added a new friend.");

        return buildContactPage(model, user, null, false);
    }

    private String buildContactPage(Model model, User user, String errorMessage, boolean showModal) {
        List<User> myFriends = friendsService.findAllMyFriends(user.getId());
        model.addAttribute("friendList", myFriends == null ? new ArrayList<>() : myFriends);
        model.addAttribute("newFriend", new FriendDto());
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("showModal", showModal);
        return "contact";
    }

    @PostMapping("/delete")
    public String deleteFriend(Authentication authentication, @RequestParam("friendId") int friendId) {
        User user = userService.findByEmail(authentication.getName());
        friendsService.deleteFriendship(user.getId(), friendId);
        return "redirect:/contact";
    }
}
