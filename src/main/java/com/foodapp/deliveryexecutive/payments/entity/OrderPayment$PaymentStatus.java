package com.foodapp.deliveryexecutive.payments.entity;

public static enum OrderPayment.PaymentStatus {
    CREATED,
    PENDING,
    PROCESSING,
    SUCCESS,
    FAILED,
    REFUNDED,
    CANCELLED;

}
