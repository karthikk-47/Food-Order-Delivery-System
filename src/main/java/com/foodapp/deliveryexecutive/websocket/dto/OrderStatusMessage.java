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
public class OrderStatusMessage {
    private Long orderId;
    private String status;
    private String previousStatus;
    private String message;
    private Long executiveId;
    private String executiveName;
    private Double executiveLatitude;
    private Double executiveLongitude;
    private String estimatedTime;
    private LocalDateTime timestamp;
}
