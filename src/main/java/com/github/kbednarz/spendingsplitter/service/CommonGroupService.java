package com.github.kbednarz.spendingsplitter.service;

import com.github.kbednarz.spendingsplitter.domain.CommonGroup;
import com.github.kbednarz.spendingsplitter.domain.User;
import com.github.kbednarz.spendingsplitter.repository.CommonGroupRepository;
import com.github.kbednarz.spendingsplitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CommonGroupService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommonGroupRepository commonGroupRepository;

    public void assignUserToGroup(User user, CommonGroup group) {
        user.addCommonGroup(group);
        userRepository.save(user);

        group.addMember(user);
        commonGroupRepository.save(group);
    }

    public void deleteUserFromGroup(User user, CommonGroup group) {
        user.getCommonGroups().remove(group);
        userRepository.save(user);

        group.getMembers().remove(user);
        commonGroupRepository.save(group);
    }
}
