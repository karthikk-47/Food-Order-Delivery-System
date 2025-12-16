package com.foodapp.deliveryexecutive.notification.repository;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.notification.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Page<Notification> findByUserIdAndUserRoleOrderByCreatedAtDesc(Long userId, Actor.Role userRole, Pageable pageable);

    List<Notification> findByUserIdAndUserRoleAndReadFalseOrderByCreatedAtDesc(Long userId, Actor.Role userRole);

    long countByUserIdAndUserRoleAndReadFalse(Long userId, Actor.Role userRole);

    List<Notification> findByReferenceIdAndType(Long referenceId, Notification.NotificationType type);
}
