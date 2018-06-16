package com.github.kbednarz.spendingsplitter;

import com.github.kbednarz.spendingsplitter.domain.Category;
import com.github.kbednarz.spendingsplitter.domain.Role;
import com.github.kbednarz.spendingsplitter.domain.User;
import com.github.kbednarz.spendingsplitter.repository.CategoryRepository;
import com.github.kbednarz.spendingsplitter.repository.RoleRepository;
import com.github.kbednarz.spendingsplitter.repository.UserRepository;
import com.github.kbednarz.spendingsplitter.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class DatabaseBootstrap implements ApplicationRunner {
    private final static Log log = LogFactory.getLog(DatabaseBootstrap.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initRoles();
        initAdmin();
        initCategories();
    }

    private void initRoles() {
        String[] roles = {"ROLE_USER", "ROLE_ADMIN"};

        for (String roleName : roles) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                role = new Role(roleName);
                roleRepository.save(role);
            }
        }
    }

    private void initAdmin() {
        User admin = userRepository.findByUsername("admin");
        if (admin == null) {
            admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");

            userService.create(admin, "ROLE_ADMIN", "ROLE_USER");
        }
    }

    private void initCategories() {
        String[] categoryNames = {"Grocery", "Restaurant", "Holiday"};

        for (String categoryName : categoryNames) {
            Category category = categoryRepository.findByName(categoryName);
            if (category == null) {
                category = new Category();
                category.setName(categoryName);
                categoryRepository.save(category);
            }
        }
    }
}
