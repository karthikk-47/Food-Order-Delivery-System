package com.foodapp.deliveryexecutive.payments.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class StatusDetails {
    private String description;
    private String source;
    private String reason;
}
