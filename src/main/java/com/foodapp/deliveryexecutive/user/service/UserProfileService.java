/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.foodapp.deliveryexecutive.user.service;

import com.foodapp.deliveryexecutive.user.dto.UserProfileDTO;
import com.foodapp.deliveryexecutive.user.entity.UserProfile;
import com.foodapp.deliveryexecutive.user.repository.UserProfileRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserProfileService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(UserProfileService.class);
    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfileDTO createProfile(UserProfileDTO profileDTO) {
        log.info("Creating user profile for user: {}", profileDTO.getUserId());
        UserProfile profile = new UserProfile();
        profile.setUserId(profileDTO.getUserId());
        profile.setFirstName(profileDTO.getFirstName());
        profile.setLastName(profileDTO.getLastName());
        profile.setEmail(profileDTO.getEmail());
        profile.setPhoneNumber(profileDTO.getPhoneNumber());
        profile.setPrimaryAddress(profileDTO.getPrimaryAddress());
        profile.setCity(profileDTO.getCity());
        profile.setState(profileDTO.getState());
        profile.setZipCode(profileDTO.getZipCode());
        profile.setPreferredPaymentMethod(profileDTO.getPreferredPaymentMethod());
        UserProfile savedProfile = (UserProfile)this.userProfileRepository.save(profile);
        log.info("User profile created successfully with ID: {}", savedProfile.getId());
        return this.convertToDTO(savedProfile);
    }

    public UserProfileDTO getProfileById(Long id) {
        log.debug("Fetching profile with ID: {}", id);
        UserProfile profile = (UserProfile)this.userProfileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        return this.convertToDTO(profile);
    }

    public UserProfileDTO getProfileByUserId(Long userId) {
        log.debug("Fetching profile for user: {}", userId);
        UserProfile profile = this.userProfileRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("Profile not found for user"));
        return this.convertToDTO(profile);
    }

    public UserProfileDTO updateProfile(Long id, UserProfileDTO profileDTO) {
        log.info("Updating profile with ID: {}", id);
        UserProfile profile = (UserProfile)this.userProfileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        if (profileDTO.getFirstName() != null) {
            profile.setFirstName(profileDTO.getFirstName());
        }
        if (profileDTO.getLastName() != null) {
            profile.setLastName(profileDTO.getLastName());
        }
        if (profileDTO.getProfileImage() != null) {
            profile.setProfileImage(profileDTO.getProfileImage());
        }
        if (profileDTO.getPrimaryAddress() != null) {
            profile.setPrimaryAddress(profileDTO.getPrimaryAddress());
        }
        if (profileDTO.getPreferredPaymentMethod() != null) {
            profile.setPreferredPaymentMethod(profileDTO.getPreferredPaymentMethod());
        }
        UserProfile updatedProfile = (UserProfile)this.userProfileRepository.save(profile);
        log.info("Profile updated successfully");
        return this.convertToDTO(updatedProfile);
    }

    public UserProfileDTO verifyEmail(Long id) {
        log.info("Verifying email for profile: {}", id);
        UserProfile profile = (UserProfile)this.userProfileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        profile.setEmailVerified(true);
        profile.setEmailVerifiedAt(LocalDateTime.now());
        UserProfile verifiedProfile = (UserProfile)this.userProfileRepository.save(profile);
        log.info("Email verified successfully");
        return this.convertToDTO(verifiedProfile);
    }

    public UserProfileDTO verifyPhone(Long id) {
        log.info("Verifying phone for profile: {}", id);
        UserProfile profile = (UserProfile)this.userProfileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        profile.setPhoneVerified(true);
        profile.setPhoneVerifiedAt(LocalDateTime.now());
        UserProfile verifiedProfile = (UserProfile)this.userProfileRepository.save(profile);
        log.info("Phone verified successfully");
        return this.convertToDTO(verifiedProfile);
    }

    public void updateLastLogin(Long id) {
        log.debug("Updating last login for profile: {}", id);
        this.userProfileRepository.findById(id).ifPresent(profile -> {
            profile.setLastLogin(LocalDateTime.now());
            this.userProfileRepository.save(profile);
        });
    }

    public List<UserProfileDTO> getAllActiveUsers() {
        log.debug("Fetching all active users");
        return this.userProfileRepository.findByAccountStatus(UserProfile.AccountStatus.ACTIVE).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private UserProfileDTO convertToDTO(UserProfile profile) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setId(profile.getId());
        dto.setUserId(profile.getUserId());
        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setEmail(profile.getEmail());
        dto.setPhoneNumber(profile.getPhoneNumber());
        dto.setProfileImage(profile.getProfileImage());
        dto.setPrimaryAddress(profile.getPrimaryAddress());
        dto.setCity(profile.getCity());
        dto.setState(profile.getState());
        dto.setZipCode(profile.getZipCode());
        dto.setLatitude(profile.getLatitude());
        dto.setLongitude(profile.getLongitude());
        dto.setHasAddressSaved(profile.getHasAddressSaved());
        dto.setAddressCount(profile.getAddressCount());
        dto.setAccountStatus(profile.getAccountStatus());
        dto.setEmailVerified(profile.getEmailVerified());
        dto.setPhoneVerified(profile.getPhoneVerified());
        dto.setEmailVerifiedAt(profile.getEmailVerifiedAt());
        dto.setPhoneVerifiedAt(profile.getPhoneVerifiedAt());
        dto.setPreferredPaymentMethod(profile.getPreferredPaymentMethod());
        dto.setNotificationsEnabled(profile.getNotificationsEnabled());
        dto.setPromotionalEmailsEnabled(profile.getPromotionalEmailsEnabled());
        dto.setTotalOrders(profile.getTotalOrders());
        dto.setTotalFavouriteHomemakers(profile.getTotalFavouriteHomemakers());
        dto.setTotalSpent(profile.getTotalSpent());
        dto.setAverageOrderValue(profile.getAverageOrderValue());
        dto.setCreatedAt(profile.getCreatedAt());
        dto.setUpdatedAt(profile.getUpdatedAt());
        dto.setLastLogin(profile.getLastLogin());
        return dto;
    }
}
