package com.github.kbednarz.spendingsplitter.service;

import com.github.kbednarz.spendingsplitter.domain.Role;
import com.github.kbednarz.spendingsplitter.domain.User;
import com.github.kbednarz.spendingsplitter.repository.RoleRepository;
import com.github.kbednarz.spendingsplitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User create(User user, String... roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        for (String roleName : roles) {
            Role role = roleRepository.findByName(roleName);
            user.addRole(role);
            role.addUser(user);
            roleRepository.save(role);
        }
        return userRepository.save(user);
    }
}
