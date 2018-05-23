package com.github.kbednarz.spendingsplitter.controller;

import com.github.kbednarz.spendingsplitter.domain.CommonGroup;
import com.github.kbednarz.spendingsplitter.repository.CommonGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Set;

@Controller
public class GroupController {

    @Autowired
    CommonGroupRepository commonGroupRepository;

    @GetMapping("/groups")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("user", principal);
        Set<CommonGroup> userGroups = commonGroupRepository.findByMembersUsername(principal.getName());
        model.addAttribute("userGroups", userGroups);
        model.addAttribute("allGroups", commonGroupRepository.findAll());
        return "/groups";
    }

}
