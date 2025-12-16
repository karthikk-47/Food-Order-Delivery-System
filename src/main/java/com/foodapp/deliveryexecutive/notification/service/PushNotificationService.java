package com.foodapp.deliveryexecutive.notification.service;

import com.foodapp.deliveryexecutive.notification.dto.PushNotificationRequest;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PushNotificationService {

    private final FirebaseMessaging firebaseMessaging;

    public PushNotificationService(@Autowired(required = false) FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
        if (firebaseMessaging == null) {
            log.warn("FirebaseMessaging not available - push notifications will be disabled");
        }
    }

    public boolean sendToToken(String token, PushNotificationRequest request) {
        if (firebaseMessaging == null) {
            log.warn("Firebase not configured. Skipping push notification.");
            return false;
        }

        try {
            Message message = buildMessage(token, request);
            String response = firebaseMessaging.send(message);
            log.info("Push notification sent successfully: {}", response);
            return true;
        } catch (FirebaseMessagingException e) {
            log.error("Failed to send push notification to token {}: {}", token, e.getMessage());
            return false;
        }
    }

    public int sendToMultipleTokens(List<String> tokens, PushNotificationRequest request) {
        if (firebaseMessaging == null || tokens.isEmpty()) {
            log.warn("Firebase not configured or no tokens provided. Skipping push notification.");
            return 0;
        }

        try {
            MulticastMessage message = buildMulticastMessage(tokens, request);
            BatchResponse response = firebaseMessaging.sendEachForMulticast(message);
            log.info("Push notifications sent: {} success, {} failures",
                    response.getSuccessCount(), response.getFailureCount());
            return response.getSuccessCount();
        } catch (FirebaseMessagingException e) {
            log.error("Failed to send multicast push notification: {}", e.getMessage());
            return 0;
        }
    }

    private Message buildMessage(String token, PushNotificationRequest request) {
        Message.Builder builder = Message.builder()
                .setToken(token)
                .setNotification(Notification.builder()
                        .setTitle(request.getTitle())
                        .setBody(request.getMessage())
                        .build());

        if (request.getData() != null && !request.getData().isEmpty()) {
            builder.putAllData(request.getData());
        }

        // Add type and referenceId to data
        builder.putData("type", request.getType().name());
        if (request.getReferenceId() != null) {
            builder.putData("referenceId", request.getReferenceId().toString());
        }

        return builder.build();
    }

    private MulticastMessage buildMulticastMessage(List<String> tokens, PushNotificationRequest request) {
        MulticastMessage.Builder builder = MulticastMessage.builder()
                .addAllTokens(tokens)
                .setNotification(Notification.builder()
                        .setTitle(request.getTitle())
                        .setBody(request.getMessage())
                        .build());

        if (request.getData() != null && !request.getData().isEmpty()) {
            builder.putAllData(request.getData());
        }

        builder.putData("type", request.getType().name());
        if (request.getReferenceId() != null) {
            builder.putData("referenceId", request.getReferenceId().toString());
        }

        return builder.build();
    }
}
