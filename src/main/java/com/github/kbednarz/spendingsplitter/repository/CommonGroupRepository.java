package com.github.kbednarz.spendingsplitter.repository;

import com.github.kbednarz.spendingsplitter.domain.CommonGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommonGroupRepository extends JpaRepository<CommonGroup, Long> {
    Set<CommonGroup> findByMembersUsername(String username);
}
