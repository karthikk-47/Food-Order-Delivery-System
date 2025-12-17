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
public class NewOrderMessage {
    private Long orderId;
    private Long homemakerId;
    private String homemakerName;
    private Double pickupLatitude;
    private Double pickupLongitude;
    private String pickupAddress;
    private Double deliveryLatitude;
    private Double deliveryLongitude;
    private String deliveryAddress;
    private Double distance;
    private Double deliveryFee;
    private String priority;
    private LocalDateTime timestamp;
}
