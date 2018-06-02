package com.github.kbednarz.spendingsplitter.controller;

import com.github.kbednarz.spendingsplitter.domain.CommonGroup;
import com.github.kbednarz.spendingsplitter.repository.CommonGroupRepository;
import com.github.kbednarz.spendingsplitter.service.CommonGroupService;
import com.github.kbednarz.spendingsplitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("groups")
public class GroupController {

    @Autowired
    CommonGroupRepository commonGroupRepository;

    @Autowired
    CommonGroupService commonGroupService;

    @Autowired
    UserService userService;

    @GetMapping("show/{id}")
    public String show(@PathVariable Long id, Model model, Principal principal) throws Exception {
        model.addAttribute("user", principal);
        Optional<CommonGroup> group = commonGroupRepository.findById(id);

        if (!group.isPresent()) {
            throw new Exception("Group does not exist");
        }

        model.addAttribute("group", group.get());

        return "group";
    }

    @GetMapping("list")
    public String list(Model model, Principal principal) {
        model.addAttribute("user", principal);
        Set<CommonGroup> userGroups = commonGroupRepository.findByMembersUsername(principal.getName());
        model.addAttribute("userGroups", userGroups);
        model.addAttribute("allGroups", commonGroupRepository.findAll());
        return "groups";
    }

    @PostMapping("join")
    public String joinGroup(@RequestParam(required = true) Long groupId, Principal principal) throws Exception {
        Optional<CommonGroup> group = commonGroupRepository.findById(groupId);
        if (!group.isPresent()) {
            throw new Exception("Group with given id does not exist");
        }

        commonGroupService.assignUserToGroup(userService.getUserForPrincipal(principal), group.get());

        return "redirect:list";
    }

}
