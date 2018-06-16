package com.github.kbednarz.spendingsplitter.controller;

import com.github.kbednarz.spendingsplitter.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("list")
    public String list(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());

        return "categories";
    }
}
