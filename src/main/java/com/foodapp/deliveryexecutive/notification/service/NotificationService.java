package com.foodapp.deliveryexecutive.notification.service;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.notification.dto.NotificationDTO;
import com.foodapp.deliveryexecutive.notification.dto.PushNotificationRequest;
import com.foodapp.deliveryexecutive.notification.dto.RegisterTokenRequest;
import com.foodapp.deliveryexecutive.notification.entity.DeviceToken;
import com.foodapp.deliveryexecutive.notification.entity.Notification;
import com.foodapp.deliveryexecutive.notification.repository.DeviceTokenRepository;
import com.foodapp.deliveryexecutive.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final DeviceTokenRepository deviceTokenRepository;
    private final PushNotificationService pushNotificationService;

    @Transactional
    public void registerToken(Long userId, Actor.Role role, RegisterTokenRequest request) {
        // Check if token already exists
        deviceTokenRepository.findByFcmToken(request.getFcmToken())
                .ifPresentOrElse(
                        existingToken -> {
                            existingToken.setUserId(userId);
                            existingToken.setUserRole(role);
                            existingToken.setActive(true);
                            deviceTokenRepository.save(existingToken);
                        },
                        () -> {
                            DeviceToken token = DeviceToken.builder()
                                    .userId(userId)
                                    .userRole(role)
                                    .fcmToken(request.getFcmToken())
                                    .deviceType(request.getDeviceType())
                                    .deviceId(request.getDeviceId())
                                    .active(true)
                                    .build();
                            deviceTokenRepository.save(token);
                        }
                );
        log.info("Registered FCM token for user {} with role {}", userId, role);
    }

    @Transactional
    public void unregisterToken(String fcmToken) {
        deviceTokenRepository.deleteByFcmToken(fcmToken);
        log.info("Unregistered FCM token");
    }

    @Async
    @Transactional
    public void sendNotification(Long userId, Actor.Role role, PushNotificationRequest request) {
        // Save notification to database
        Notification notification = Notification.builder()
                .userId(userId)
                .userRole(role)
                .title(request.getTitle())
                .message(request.getMessage())
                .type(request.getType())
                .referenceId(request.getReferenceId())
                .build();
        notificationRepository.save(notification);

        // Send push notification
        List<DeviceToken> tokens = deviceTokenRepository.findByUserIdAndUserRoleAndActiveTrue(userId, role);
        if (!tokens.isEmpty()) {
            List<String> fcmTokens = tokens.stream()
                    .map(DeviceToken::getFcmToken)
                    .collect(Collectors.toList());
            
            int sent = pushNotificationService.sendToMultipleTokens(fcmTokens, request);
            if (sent > 0) {
                notification.setSent(true);
                notification.setSentAt(LocalDateTime.now());
                notificationRepository.save(notification);
            }
        }
    }

    @Async
    @Transactional
    public void sendToAllByRole(Actor.Role role, PushNotificationRequest request) {
        List<DeviceToken> tokens = deviceTokenRepository.findByUserRoleAndActiveTrue(role);
        if (!tokens.isEmpty()) {
            List<String> fcmTokens = tokens.stream()
                    .map(DeviceToken::getFcmToken)
                    .collect(Collectors.toList());
            pushNotificationService.sendToMultipleTokens(fcmTokens, request);
        }
    }

    public Page<NotificationDTO> getNotifications(Long userId, Actor.Role role, Pageable pageable) {
        return notificationRepository.findByUserIdAndUserRoleOrderByCreatedAtDesc(userId, role, pageable)
                .map(NotificationDTO::fromEntity);
    }

    public List<NotificationDTO> getUnreadNotifications(Long userId, Actor.Role role) {
        return notificationRepository.findByUserIdAndUserRoleAndReadFalseOrderByCreatedAtDesc(userId, role)
                .stream()
                .map(NotificationDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public long getUnreadCount(Long userId, Actor.Role role) {
        return notificationRepository.countByUserIdAndUserRoleAndReadFalse(userId, role);
    }

    @Transactional
    public void markAsRead(Long notificationId) {
        notificationRepository.findById(notificationId).ifPresent(notification -> {
            notification.setRead(true);
            notification.setReadAt(LocalDateTime.now());
            notificationRepository.save(notification);
        });
    }

    @Transactional
    public void markAllAsRead(Long userId, Actor.Role role) {
        List<Notification> unread = notificationRepository
                .findByUserIdAndUserRoleAndReadFalseOrderByCreatedAtDesc(userId, role);
        unread.forEach(notification -> {
            notification.setRead(true);
            notification.setReadAt(LocalDateTime.now());
        });
        notificationRepository.saveAll(unread);
    }
}
