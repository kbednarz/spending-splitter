package com.github.kbednarz.spendingsplitter.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Spending {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    Double amount;

    String description;

    @JsonIgnoreProperties({"commonGroups", "roles", "spendings"})
    @ManyToOne(fetch = FetchType.LAZY)
    User paidByUser;

    @JsonIgnoreProperties({"members", "spendings"})
    @ManyToOne(fetch = FetchType.LAZY)
    CommonGroup group;

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

    public CommonGroup getGroup() {
        return group;
    }

    public void setGroup(CommonGroup group) {
        this.group = group;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
