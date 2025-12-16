package com.foodapp.deliveryexecutive.wallet.entity;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private long customerId;
    @Enumerated(value=EnumType.STRING)
    private Actor.Role role;
    private double balance;

    public Actor.Role getRole() {
        return this.role;
    }

    public void setRole(Actor.Role role) {
        this.role = role;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
