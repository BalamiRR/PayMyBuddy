package com.balamir.paymybuddy.controller;

import com.balamir.paymybuddy.model.Account;
import com.balamir.paymybuddy.model.Transaction;
import com.balamir.paymybuddy.model.User;
import com.balamir.paymybuddy.model.dto.TransactionDto;
import com.balamir.paymybuddy.service.AccountService;
import com.balamir.paymybuddy.service.FriendsService;
import com.balamir.paymybuddy.service.TransactionService;
import com.balamir.paymybuddy.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/transfer")
public class TransferController {
    private final UserService userService;
    private final FriendsService friendsService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    @GetMapping
    public String transferPage(Authentication authentication, Model model) {
        User user = userService.findByEmail(authentication.getName());
        List<User> myFriends = friendsService.findAllMyFriends(user.getId());
        Account account = accountService.findByUserId(user.getId());
        BigDecimal balance = account != null && account.getBalance() != null ? account.getBalance() : BigDecimal.ZERO;

        model.addAttribute("balance", balance);
        model.addAttribute("usdBalanceFormatted",
                "+" + balance.multiply(BigDecimal.valueOf(1.17)).setScale(2, RoundingMode.HALF_UP) + " USD");
        model.addAttribute("friendList", myFriends);

        List<Transaction> transaction = transactionService.getMyTransactions(user.getId());
        List<TransactionDto> txnDTOs = transaction.stream()
                .map(t -> new TransactionDto(t, user.getId()))
                .collect(Collectors.toList());
        model.addAttribute("transactions", txnDTOs);
        return "transfer";
    }

    @PostMapping("/add-money")
    public String addMoney(@RequestParam("amount") BigDecimal amount, @RequestParam("currency") String currency, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        accountService.addMoney(user.getId(), amount, currency);
        return "redirect:/transfer";
    }

    @PostMapping("/send-money")
    public String sendMoney(@RequestParam("relation") int receiverId, @RequestParam("sendingAmount") BigDecimal amount,
                            @RequestParam("currency") String currency, @RequestParam("description") String description, Principal principal) {
        User sender = userService.findByEmail(principal.getName());
        User receiver = userService.getById(receiverId);
        transactionService.sendMoney(sender, receiver, amount, currency, description);
        return "redirect:/transfer";
    }


}