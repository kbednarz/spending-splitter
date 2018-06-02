package com.github.kbednarz.spendingsplitter.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Spending {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    User paidByUser;

    @ManyToOne(fetch = FetchType.LAZY)
    CommonGroup commonGroup;

    @Temporal(TemporalType.TIMESTAMP)
    Date date;

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getPaidByUser() {
        return paidByUser;
    }

    public void setPaidByUser(User paidByUser) {
        this.paidByUser = paidByUser;
    }

    public CommonGroup getCommonGroup() {
        return commonGroup;
    }

    public void setCommonGroup(CommonGroup commonGroup) {
        this.commonGroup = commonGroup;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
