package com.foodapp.deliveryexecutive.notification.controller;

import com.foodapp.deliveryexecutive.notification.dto.NotificationDTO;
import com.foodapp.deliveryexecutive.notification.dto.RegisterTokenRequest;
import com.foodapp.deliveryexecutive.notification.service.NotificationService;
import com.foodapp.deliveryexecutive.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/register-token")
    public ResponseEntity<Map<String, String>> registerToken(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody RegisterTokenRequest request) {
        
        notificationService.registerToken(principal.getId(), principal.getRole(), request);
        return ResponseEntity.ok(Collections.singletonMap("message", "Token registered successfully"));
    }

    @DeleteMapping("/unregister-token")
    public ResponseEntity<Map<String, String>> unregisterToken(@RequestParam String fcmToken) {
        notificationService.unregisterToken(fcmToken);
        return ResponseEntity.ok(Collections.singletonMap("message", "Token unregistered successfully"));
    }

    @GetMapping
    public ResponseEntity<Page<NotificationDTO>> getNotifications(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        Page<NotificationDTO> notifications = notificationService.getNotifications(
                principal.getId(), principal.getRole(), PageRequest.of(page, size));
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/unread")
    public ResponseEntity<List<NotificationDTO>> getUnreadNotifications(
            @AuthenticationPrincipal UserPrincipal principal) {
        
        List<NotificationDTO> notifications = notificationService.getUnreadNotifications(
                principal.getId(), principal.getRole());
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/unread-count")
    public ResponseEntity<Map<String, Long>> getUnreadCount(
            @AuthenticationPrincipal UserPrincipal principal) {
        
        long count = notificationService.getUnreadCount(principal.getId(), principal.getRole());
        return ResponseEntity.ok(Collections.singletonMap("count", count));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Map<String, String>> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok(Collections.singletonMap("message", "Notification marked as read"));
    }

    @PutMapping("/read-all")
    public ResponseEntity<Map<String, String>> markAllAsRead(
            @AuthenticationPrincipal UserPrincipal principal) {
        
        notificationService.markAllAsRead(principal.getId(), principal.getRole());
        return ResponseEntity.ok(Collections.singletonMap("message", "All notifications marked as read"));
    }
}
