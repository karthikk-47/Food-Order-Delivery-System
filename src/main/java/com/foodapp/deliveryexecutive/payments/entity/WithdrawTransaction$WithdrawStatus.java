package com.foodapp.deliveryexecutive.payments.entity;

public static enum WithdrawTransaction.WithdrawStatus {
    PENDING,
    PROCESSING,
    PROCESSED,
    REVERSED,
    FAILED,
    CANCELLED;

}
