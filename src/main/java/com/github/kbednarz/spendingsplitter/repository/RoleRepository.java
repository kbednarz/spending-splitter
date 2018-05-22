package com.github.kbednarz.spendingsplitter.repository;

import com.github.kbednarz.spendingsplitter.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
