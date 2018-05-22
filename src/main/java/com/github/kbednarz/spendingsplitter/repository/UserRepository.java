package com.github.kbednarz.spendingsplitter.repository;

import com.github.kbednarz.spendingsplitter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
