package com.foodapp.deliveryexecutive.security.repository;

import com.foodapp.deliveryexecutive.security.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    
    Optional<PasswordResetToken> findByToken(String token);
    
    Optional<PasswordResetToken> findByMobileAndUsedFalseAndExpiryDateAfter(String mobile, LocalDateTime now);
    
    List<PasswordResetToken> findByMobile(String mobile);
    
    void deleteByExpiryDateBefore(LocalDateTime dateTime);
}
