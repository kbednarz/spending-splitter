package com.github.kbednarz.spendingsplitter.service;

import com.github.kbednarz.spendingsplitter.domain.Category;
import com.github.kbednarz.spendingsplitter.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category findOrCreateCategory(String name) {
        Category category = categoryRepository.findByName(name);

        if (category == null) {
            category = new Category();
            category.setName(name);
            category = categoryRepository.save(category);
        }

        return category;
    }
}
