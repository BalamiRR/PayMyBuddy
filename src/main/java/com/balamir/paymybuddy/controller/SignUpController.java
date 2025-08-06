package com.balamir.paymybuddy.controller;

import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.model.dto.UserDto;
import com.balamir.paymybuddy.service.UserService;
import com.balamir.paymybuddy.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/signup")
public class SignUpController {
    private final UserService userService;
    private final UserServiceImpl userServiceImpl;

    @GetMapping
    public String signUp(){
        return "signup";
    }

    @PostMapping
    public String signUpUser(@ModelAttribute UserDto userDto, Model model, RedirectAttributes redirectAttributes){
        String email = userDto.getEmail();
        log.info("Sign-up request received for email: {}", email);

        User existUser = userService.findByEmail(email);
        if (existUser != null) {
            log.warn("Sign-up failed: email already registered - {}", email);
            model.addAttribute("signUpError", true);
            return "signup";
        }

        log.info("Email is available, attempting to save new user: {}", email);
        userServiceImpl.save(userDto);

        redirectAttributes.addFlashAttribute("message", "You've successfully signed up, please login.");

        log.info("Sign-up successful for user: {}", email);
        return "redirect:/login";
    }

}
