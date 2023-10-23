package com.aninfo.model;



import com.aninfo.Enums.TransactionTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private TransactionTypes transactionType;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @JsonIgnore
    @ManyToOne
    private Account account;

    private double amount;

    private double promo_extra;

    public Transaction(Account account, TransactionTypes transactionType, double amount) {
        this.transactionType = transactionType;
        this.account = account;
        this.amount = amount;
        if (amount >= 2000){
            this.promo_extra = amount * 0.10;
        }else {
            this.promo_extra = 0;
        }
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionTypes getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypes transactionType) {
        this.transactionType = transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getPromo_extra() {
        return promo_extra;
    }

    public void setPromo_extra(double promo_extra) {
        this.promo_extra = promo_extra;
    }
}
