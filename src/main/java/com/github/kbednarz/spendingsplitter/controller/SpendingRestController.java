package com.github.kbednarz.spendingsplitter.controller;

import com.github.kbednarz.spendingsplitter.domain.CommonGroup;
import com.github.kbednarz.spendingsplitter.domain.Spending;
import com.github.kbednarz.spendingsplitter.domain.User;
import com.github.kbednarz.spendingsplitter.repository.CommonGroupRepository;
import com.github.kbednarz.spendingsplitter.repository.SpendingRepository;
import com.github.kbednarz.spendingsplitter.service.SpendingService;
import com.github.kbednarz.spendingsplitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/spending")
public class SpendingRestController {

    @Autowired
    SpendingRepository spendingRepository;

    @Autowired
    CommonGroupRepository commonGroupRepository;

    @Autowired
    SpendingService spendingService;

    @Autowired
    UserService userService;

    @GetMapping("{id}")
    public Spending getSpending(@PathVariable Long id) {
        return spendingRepository.getOne(id);
    }

    @PostMapping
    public Spending saveSpending(@RequestParam Long groupId, @RequestParam Double amount, Principal principal) {
        CommonGroup group = commonGroupRepository.getOne(groupId);
        User paidByUser = userService.getUserForPrincipal(principal);

        return spendingService.saveSpending(group, paidByUser, amount);
    }

}
