/*
 * Decompiled with CFR 0.152.
 */
package com.foodapp.deliveryexecutive.payments.dto;

import java.time.LocalDate;

public class SettlementDTO {
    private Long id;
    private Long homemakerId;
    private double amount;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private boolean settled;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHomemakerId() {
        return this.homemakerId;
    }

    public void setHomemakerId(Long homemakerId) {
        this.homemakerId = homemakerId;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPeriodStart() {
        return this.periodStart;
    }

    public void setPeriodStart(LocalDate periodStart) {
        this.periodStart = periodStart;
    }

    public LocalDate getPeriodEnd() {
        return this.periodEnd;
    }

    public void setPeriodEnd(LocalDate periodEnd) {
        this.periodEnd = periodEnd;
    }

    public boolean isSettled() {
        return this.settled;
    }

    public void setSettled(boolean settled) {
        this.settled = settled;
    }
}
