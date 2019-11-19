package com.aboyce002.Person_API.domains;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @Column(name="ACCOUNT_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String nickname;
    private int rewards;
    private double balance;
    private Long customerId;

    public Account() {
    }

    public Account(Long id, Type type, String nickname, int rewards, double balance, Long customerId) {
        this.id = id;
        this.type = type;
        this.nickname = nickname;
        this.rewards = rewards;
        this.balance = balance;
        this.customerId = customerId;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Type  type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getRewards() {
        return rewards;
    }

    public void setRewards(int rewards) {
        this.rewards = rewards;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", type=" + type +
                ", nickname='" + nickname + '\'' +
                ", rewards=" + rewards +
                ", balance=" + balance +
                ", customerId=" + customerId +
                '}';
    }
}
