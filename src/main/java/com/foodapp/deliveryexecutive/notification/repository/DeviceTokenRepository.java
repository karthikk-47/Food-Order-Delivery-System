package com.foodapp.deliveryexecutive.notification.repository;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.notification.entity.DeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceTokenRepository extends JpaRepository<DeviceToken, Long> {

    List<DeviceToken> findByUserIdAndUserRoleAndActiveTrue(Long userId, Actor.Role userRole);

    Optional<DeviceToken> findByFcmToken(String fcmToken);

    List<DeviceToken> findByUserRoleAndActiveTrue(Actor.Role userRole);

    void deleteByFcmToken(String fcmToken);

    void deleteByUserIdAndUserRole(Long userId, Actor.Role userRole);
}
