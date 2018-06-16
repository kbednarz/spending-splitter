package com.github.kbednarz.spendingsplitter.repository;

import com.github.kbednarz.spendingsplitter.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
