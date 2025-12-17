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
public class LocationUpdateMessage {
    private Long orderId;
    private Long executiveId;
    private Double latitude;
    private Double longitude;
    private Double speed;
    private Double heading;
    private String estimatedArrival;
    private Double distanceRemaining;
    private LocalDateTime timestamp;
}
