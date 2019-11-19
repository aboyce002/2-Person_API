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
    private Long payee_id;
    private Double amount;
    private String description;

    public Deposit() {
    }

    public Deposit(DepoWithType type, Medium medium, DepoWithStatus status, Long id, String transaction_date, Long payee_id, Double amount, String description) {
        this.type = type;
        this.medium = medium;
        this.status = status;
        this.id = id;
        this.transaction_date = transaction_date;
        this.payee_id = payee_id;
        this.amount = amount;
        this.description = description;
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

    public Long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(Long payee_id) {
        this.payee_id = payee_id;
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
                ", payee_id=" + payee_id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
