package com.github.kbednarz.spendingsplitter.repository;

import com.github.kbednarz.spendingsplitter.domain.CommonGroup;
import com.github.kbednarz.spendingsplitter.domain.Spending;
import com.github.kbednarz.spendingsplitter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SpendingRepository extends JpaRepository<Spending, Long> {

    @Query("SELECT SUM(s.amount) FROM Spending s WHERE s.group = :group")
    Double sumAmountByGroup(@Param("group") CommonGroup group);

    @Query("SELECT SUM(s.amount) FROM Spending s WHERE s.group = :group AND s.paidByUser = :user")
    Double sumAmountByGroupAndPaidByUser(@Param("group") CommonGroup group, @Param("user") User user);

    List<Spending> findAllByGroupOrderByDateDesc(CommonGroup group);
}
