package com.github.kbednarz.spendingsplitter.controller;

import com.github.kbednarz.spendingsplitter.domain.User;
import com.github.kbednarz.spendingsplitter.service.CategoryService;
import com.github.kbednarz.spendingsplitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("api/category")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    UserService userService;

    @GetMapping("chart")
    public Map getChartData(Principal principal) {
        User user = userService.getUserForPrincipal(principal);

        Map<String, Double> categoryMap = categoryService.prepareChartData(user);

        return categoryMap;
    }
}
