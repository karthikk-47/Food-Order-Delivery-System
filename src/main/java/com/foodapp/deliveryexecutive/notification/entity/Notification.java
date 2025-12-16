package com.foodapp.deliveryexecutive.notification.entity;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Actor.Role userRole;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @Column
    private Long referenceId; // orderId, etc.

    @Column
    private boolean read = false;

    @Column
    private boolean sent = false;

    @Column
    private LocalDateTime sentAt;

    @Column
    private LocalDateTime readAt;

    @Column
    private LocalDateTime createdAt;

    public enum NotificationType {
        ORDER_PLACED,
        ORDER_CONFIRMED,
        ORDER_PREPARING,
        ORDER_READY,
        ORDER_PICKED_UP,
        ORDER_OUT_FOR_DELIVERY,
        ORDER_DELIVERED,
        ORDER_CANCELLED,
        NEW_ORDER_NEARBY,
        PAYMENT_RECEIVED,
        WITHDRAWAL_PROCESSED,
        ACCOUNT_APPROVED,
        ACCOUNT_REJECTED,
        PROMOTIONAL
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
