package com.foodapp.deliveryexecutive.security.service;

import com.foodapp.deliveryexecutive.admin.entity.Admin;
import com.foodapp.deliveryexecutive.admin.repository.AdminRepository;
import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.security.entity.PasswordResetToken;
import com.foodapp.deliveryexecutive.security.repository.PasswordResetTokenRepository;
import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final HomeMakerRepository homeMakerRepository;
    private final DeliveryExecutiveRepository deliveryExecutiveRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public PasswordResetResult requestPasswordReset(String mobile) {
        log.info("Password reset requested for mobile: {}", mobile);
        
        // Find user by mobile across all user types
        UserInfo userInfo = findUserByMobile(mobile);
        if (userInfo == null) {
            log.warn("No user found with mobile: {}", mobile);
            return new PasswordResetResult(false, "No account found with this mobile number", null);
        }
        
        // Invalidate any existing tokens for this mobile
        tokenRepository.findByMobile(mobile).forEach(token -> {
            token.setUsed(true);
            tokenRepository.save(token);
        });
        
        // Generate new token (6-digit OTP for mobile-based reset)
        String otp = generateOTP();
        
        PasswordResetToken resetToken = PasswordResetToken.builder()
                .token(otp)
                .mobile(mobile)
                .userId(userInfo.userId)
                .userRole(userInfo.role)
                .expiryDate(LocalDateTime.now().plusMinutes(15)) // OTP valid for 15 minutes
                .used(false)
                .build();
        
        tokenRepository.save(resetToken);
        
        // In production, send OTP via SMS
        log.info("Password reset OTP generated for mobile: {} - OTP: {}", mobile, otp);
        
        return new PasswordResetResult(true, "OTP sent to your mobile number", otp);
    }

    @Transactional
    public PasswordResetResult verifyOTPAndResetPassword(String mobile, String otp, String newPassword) {
        log.info("Verifying OTP for mobile: {}", mobile);
        
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(otp);
        
        if (tokenOpt.isEmpty()) {
            return new PasswordResetResult(false, "Invalid OTP", null);
        }
        
        PasswordResetToken token = tokenOpt.get();
        
        if (!token.getMobile().equals(mobile)) {
            return new PasswordResetResult(false, "OTP does not match mobile number", null);
        }
        
        if (token.isUsed()) {
            return new PasswordResetResult(false, "OTP has already been used", null);
        }
        
        if (token.isExpired()) {
            return new PasswordResetResult(false, "OTP has expired. Please request a new one", null);
        }
        
        // Update password based on user role
        boolean passwordUpdated = updatePassword(token.getUserId(), token.getUserRole(), newPassword);
        
        if (!passwordUpdated) {
            return new PasswordResetResult(false, "Failed to update password", null);
        }
        
        // Mark token as used
        token.setUsed(true);
        tokenRepository.save(token);
        
        log.info("Password reset successful for mobile: {}", mobile);
        return new PasswordResetResult(true, "Password reset successful. You can now login with your new password", null);
    }

    @Transactional
    public PasswordResetResult verifyOTP(String mobile, String otp) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(otp);
        
        if (tokenOpt.isEmpty()) {
            return new PasswordResetResult(false, "Invalid OTP", null);
        }
        
        PasswordResetToken token = tokenOpt.get();
        
        if (!token.getMobile().equals(mobile)) {
            return new PasswordResetResult(false, "OTP does not match mobile number", null);
        }
        
        if (token.isUsed()) {
            return new PasswordResetResult(false, "OTP has already been used", null);
        }
        
        if (token.isExpired()) {
            return new PasswordResetResult(false, "OTP has expired", null);
        }
        
        return new PasswordResetResult(true, "OTP verified successfully", otp);
    }

    private UserInfo findUserByMobile(String mobile) {
        // Check User
        Optional<User> user = userRepository.findByMobile(mobile);
        if (user.isPresent()) {
            return new UserInfo(user.get().getId(), Actor.Role.USER);
        }
        
        // Check HomeMaker
        Optional<HomeMaker> homeMaker = homeMakerRepository.findByMobile(mobile);
        if (homeMaker.isPresent()) {
            return new UserInfo(homeMaker.get().getId(), Actor.Role.HOMEMAKER);
        }
        
        // Check DeliveryExecutive
        Optional<DeliveryExecutive> executive = deliveryExecutiveRepository.findByMobile(mobile);
        if (executive.isPresent()) {
            return new UserInfo(executive.get().getId(), Actor.Role.DELIVERYEXECUTIVE);
        }
        
        // Check Admin
        Optional<Admin> admin = adminRepository.findByUsername(mobile);
        if (admin.isPresent()) {
            return new UserInfo(admin.get().getId(), Actor.Role.ADMIN);
        }
        
        return null;
    }

    private boolean updatePassword(Long userId, Actor.Role role, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        
        switch (role) {
            case USER:
                return userRepository.findById(userId).map(user -> {
                    user.setPassword(encodedPassword);
                    userRepository.save(user);
                    return true;
                }).orElse(false);
                
            case HOMEMAKER:
                return homeMakerRepository.findById(userId).map(homeMaker -> {
                    homeMaker.setPassword(encodedPassword);
                    homeMakerRepository.save(homeMaker);
                    return true;
                }).orElse(false);
                
            case DELIVERYEXECUTIVE:
                return deliveryExecutiveRepository.findById(userId).map(executive -> {
                    executive.setPassword(encodedPassword);
                    deliveryExecutiveRepository.save(executive);
                    return true;
                }).orElse(false);
                
            case ADMIN:
                return adminRepository.findById(userId).map(admin -> {
                    admin.setPassword(encodedPassword);
                    adminRepository.save(admin);
                    return true;
                }).orElse(false);
                
            default:
                return false;
        }
    }

    private String generateOTP() {
        // Generate 6-digit OTP
        return String.format("%06d", (int) (Math.random() * 1000000));
    }

    // Inner classes for results
    public record PasswordResetResult(boolean success, String message, String token) {}
    
    private record UserInfo(Long userId, Actor.Role role) {}
}
