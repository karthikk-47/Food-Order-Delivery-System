package com.foodapp.deliveryexecutive.notification.dto;

import com.foodapp.deliveryexecutive.notification.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushNotificationRequest {
    private String title;
    private String message;
    private Notification.NotificationType type;
    private Long referenceId;
    private Map<String, String> data;
}
