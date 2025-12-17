package com.foodapp.deliveryexecutive.websocket.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {
    private Long id;
    private String type;
    private String title;
    private String message;
    private Long targetUserId;
    private String targetRole;
    private Long relatedOrderId;
    private String actionUrl;
    private boolean read;
    private LocalDateTime timestamp;
}
