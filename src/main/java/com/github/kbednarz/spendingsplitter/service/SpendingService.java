package com.github.kbednarz.spendingsplitter.service;

import com.github.kbednarz.spendingsplitter.domain.CommonGroup;
import com.github.kbednarz.spendingsplitter.domain.User;
import com.github.kbednarz.spendingsplitter.repository.SpendingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SpendingService {
    private static final Logger log = LoggerFactory.getLogger(SpendingService.class);

    @Autowired
    SpendingRepository spendingRepository;

    /**
     * Calculates cash balance within group for user
     *
     * @param group
     * @param user
     * @return positive or negative balance
     */
    public Double calculateBalanceForGroupAndUser(CommonGroup group, User user) throws Exception {
        log.debug("Calculating balance for group [{}] and user [{}]", group.getName(), user.getUsername());
        if (!group.getMembers().contains(user)) {
            throw new Exception("User isn't member of given group");
        }

        Double allSpendings = spendingRepository.sumAmountByGroup(group);
        Double userSpendings = spendingRepository.sumAmountByGroupAndPaidByUser(group, user);
        Integer memberSize = group.getMembers().size();

        if (allSpendings == null) allSpendings = 0.0;
        if (userSpendings == null) userSpendings = 0.0;

        Double avgBalance = allSpendings / memberSize;
        Double userBalance = userSpendings - avgBalance;

        return userBalance;
    }

}
