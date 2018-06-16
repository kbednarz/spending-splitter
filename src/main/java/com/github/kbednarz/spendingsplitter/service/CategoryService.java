package com.github.kbednarz.spendingsplitter.service;

import com.github.kbednarz.spendingsplitter.domain.Category;
import com.github.kbednarz.spendingsplitter.domain.Spending;
import com.github.kbednarz.spendingsplitter.domain.User;
import com.github.kbednarz.spendingsplitter.repository.CategoryRepository;
import com.github.kbednarz.spendingsplitter.repository.SpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SpendingRepository spendingRepository;

    public Category findOrCreateCategory(String name) {
        Category category = categoryRepository.findByName(name);

        if (category == null) {
            category = new Category();
            category.setName(name);
            category = categoryRepository.save(category);
        }

        return category;
    }

    public Map<String, Double> prepareChartData(User user) {
        List<Spending> spendings = spendingRepository.findAllByPaidByUser(user);

        Map<String, Double> spendingByCategory = new HashMap<>();
        for (Spending spending : spendings) {
            Double currentAmount = spendingByCategory.getOrDefault(spending.getCategory().getName(), 0.0);

            spendingByCategory.put(spending.getCategory().getName(), spending.getAmount() + currentAmount);
        }

        return spendingByCategory;
    }
}
