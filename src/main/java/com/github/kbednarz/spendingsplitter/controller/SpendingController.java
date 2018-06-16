package com.github.kbednarz.spendingsplitter.controller;

import com.github.kbednarz.spendingsplitter.domain.CommonGroup;
import com.github.kbednarz.spendingsplitter.domain.Spending;
import com.github.kbednarz.spendingsplitter.domain.User;
import com.github.kbednarz.spendingsplitter.repository.CommonGroupRepository;
import com.github.kbednarz.spendingsplitter.repository.SpendingRepository;
import com.github.kbednarz.spendingsplitter.service.SpendingService;
import com.github.kbednarz.spendingsplitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("api/spending")
public class SpendingController {

    @Autowired
    SpendingRepository spendingRepository;

    @Autowired
    CommonGroupRepository commonGroupRepository;

    @Autowired
    SpendingService spendingService;

    @Autowired
    UserService userService;

    @PostMapping
    public String saveSpending(@RequestParam Long groupId, @RequestParam Double amount,
                               @RequestParam(required = false) String description, Principal principal, Model model) {
        CommonGroup group = commonGroupRepository.getOne(groupId);
        User paidByUser = userService.getUserForPrincipal(principal);

        spendingService.saveSpending(group, paidByUser, amount, description);

        List<Spending> spendings = spendingRepository.findAllByGroupOrderByDateDesc(group);
        model.addAttribute("spendings", spendings);

        return "group :: #spending-body";
    }

    @DeleteMapping("{spendingId}")
    public String deleteSpending(@PathVariable Long spendingId, Model model) {

        Spending spending = spendingRepository.getOne(spendingId);
        CommonGroup group = spending.getGroup();

        spendingRepository.delete(spending);

        List<Spending> spendings = spendingRepository.findAllByGroupOrderByDateDesc(group);
        model.addAttribute("spendings", spendings);

        return "group :: #spending-body";
    }

    @GetMapping("balance")
    @ResponseBody
    public Double getBalance(@RequestParam Long groupId, Principal principal) throws Exception {
        CommonGroup group = commonGroupRepository.getOne(groupId);

        return spendingService.calculateBalanceForGroupAndUser(group, userService.getUserForPrincipal(principal));
    }
}
