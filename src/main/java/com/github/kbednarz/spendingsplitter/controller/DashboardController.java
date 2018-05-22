package com.github.kbednarz.spendingsplitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @RequestMapping("/dashboard")
    public String dashboard(Model model) {


        return "/dashboard";
    }
}
