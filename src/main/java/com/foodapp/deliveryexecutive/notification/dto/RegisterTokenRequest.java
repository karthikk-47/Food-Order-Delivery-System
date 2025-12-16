package com.foodapp.deliveryexecutive.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTokenRequest {
    private String fcmToken;
    private String deviceType; // ANDROID, IOS, WEB
    private String deviceId;
}
