/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Embeddable
 */
package com.foodapp.deliveryexecutive.payments.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class StatusDetails {
    private String description;
    private String source;
    private String reason;
}
