package com.aboyce002.Person_API.domains;

import javax.persistence.*;

@Entity
public class Withdrawal {

    @Enumerated(EnumType.STRING)
    private DepoWithType type;

    @Enumerated(EnumType.STRING)
    private Medium medium;

    @Enumerated(EnumType.STRING)
    private DepoWithStatus status;

    @Id
    @Column(name = "WITHDRAW_ID")
    private Long id;
    private String transactionDate;
    private Long payerId;
    private Double amount;
    private String description;
    private Long accountId;

    public Withdrawal() {
    }

    public Withdrawal(DepoWithType type, Medium medium, DepoWithStatus status, Long id, String transactionDate, Long payerId, Double amount, String description, Long accountId) {
        this.type = type;
        this.medium = medium;
        this.status = status;
        this.id = id;
        this.transactionDate = transactionDate;
        this.payerId = payerId;
        this.amount = amount;
        this.description = description;
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DepoWithType getType() {
        return type;
    }

    public void setType(DepoWithType type) {
        this.type = type;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public DepoWithStatus getStatus() {
        return status;
    }

    public void setStatus(DepoWithStatus status) {
        this.status = status;
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
                "type=" + type +
                ", medium=" + medium +
                ", status=" + status +
                ", id=" + id +
                ", transactionDate='" + transactionDate + '\'' +
                ", payerId=" + payerId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
