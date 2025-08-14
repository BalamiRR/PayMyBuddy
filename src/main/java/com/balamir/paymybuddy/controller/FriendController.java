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
        String findEmail = friendDto.getFriendEmail();

        boolean showModal = true;
        String errorMessage = null;
        boolean success = false;

        if(user.getEmail().equalsIgnoreCase(findEmail)){
            errorMessage = "You cannot add yourself as a friend.";
            log.error("You cannot add yourself as a friend.");
        } else {
            User friendToBe = userService.findByEmail(findEmail);
            if (friendToBe == null) {
                model.addAttribute("errorMessage", "could not find any user or you can not add yourself.");
                model.addAttribute("showModal", true);
            }
            if(friendToBe != null && friendToBe.getAccount() != null){
                boolean alreadyFriend = friendsService.isAlreadyFriend(user.getId(), friendToBe.getId());
                if(alreadyFriend){
                    errorMessage = "This user is already your friend.";
                    log.error("This user is already your friend");
                } else {
                    Friends friends = new Friends();
                    friends.setUser(user);
                    friends.setFriend(friendToBe);
                    friendsService.save(friends);
                    success = true;
                    showModal = false;
                }
            } else {
                errorMessage = "This user does not exist or has no account yet.";
                log.error("This user does not exist or has no account yet.");
            }
        }

        List<User> myFriends = friendsService.findAllMyFriends(user.getId());
        model.addAttribute("friendList", myFriends);
        model.addAttribute("newFriend", new FriendDto());
        model.addAttribute("success", success);
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
