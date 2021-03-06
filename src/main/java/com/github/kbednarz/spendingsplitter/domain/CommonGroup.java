package com.github.kbednarz.spendingsplitter.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CommonGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "commonGroups")
    private Set<User> members = new HashSet<>();

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="group")
    private final Set<Spending> spendings = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public void addMember(User member) {
        members.add(member);
    }

    public Set<Spending> getSpendings() {
        return spendings;
    }

    public void addSpending(Spending spending) {
        spendings.add(spending);
    }
}
