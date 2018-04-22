package com.github.kbednarz.spendingsplitter.security.service;

import com.github.kbednarz.spendingsplitter.security.dao.UserDAO;
import com.github.kbednarz.spendingsplitter.security.domain.User;
import com.github.kbednarz.spendingsplitter.security.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        Hibernate.initialize(user.getRoles());
        return new UserDAO(user);
    }
}
