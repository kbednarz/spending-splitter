package com.github.kbednarz.spendingsplitter.controller;

import com.github.kbednarz.spendingsplitter.domain.User;
import com.github.kbednarz.spendingsplitter.repository.UserRepository;
import com.github.kbednarz.spendingsplitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model, User user) {
        model.addAttribute("user", user);

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute @Valid User user) {

        User userExists = userRepository.findByUsername(user.getUsername());

        if (userExists != null) {
            model.addAttribute("error", "User already exists");
            return "register";
        }
        userService.create(user, "ROLE_USER");
        model.addAttribute("isRegistred", true);

        return "register";
    }

}