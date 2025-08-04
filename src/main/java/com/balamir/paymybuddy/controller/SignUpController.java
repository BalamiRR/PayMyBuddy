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
        String signUpError = null;
        User existUser = userService.findByEmail(userDto.getEmail());

        if(existUser != null){
            signUpError = "The email was already exists";
        }
        if(signUpError == null){
            userServiceImpl.save(userDto);
        }

        if(signUpError == null) {
            redirectAttributes.addFlashAttribute("message", "You have successfully signed up, please login.");
            return "redirect:/login";
        } else {
            model.addAttribute("signupError", true);
        }
        return "signup";
    }

}
