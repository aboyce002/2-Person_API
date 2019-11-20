package com.aboyce002.Person_API.domains;

import javax.persistence.*;

@Entity
public class Deposit {

    @Enumerated(EnumType.STRING)
    private DepoWithType type;

    @Enumerated(EnumType.STRING)
    private Medium medium;

    @Enumerated(EnumType.STRING)
    private DepoWithStatus status;

    @Id
    @Column(name="DEPOSIT_ID")
    private Long id;
    private String transaction_date;
    private Long payeeId;
    private Double amount;
    private String description;
    private Long accountId;

    public Deposit() {
    }

    public Deposit(DepoWithType type, Medium medium, DepoWithStatus status, Long id, String transaction_date, Long payeeId, Double amount, String description, Long accountId) {
        this.type = type;
        this.medium = medium;
        this.status = status;
        this.id = id;
        this.transaction_date = transaction_date;
        this.payeeId = payeeId;
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

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public DepoWithStatus getStatus() {
        return status;
    }

    public void setStatus(DepoWithStatus status) {
        this.status = status;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
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
        return "Deposit{" +
                "type=" + type +
                ", medium=" + medium +
                ", status=" + status +
                ", id=" + id +
                ", transaction_date='" + transaction_date + '\'' +
                ", payeeId=" + payeeId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
