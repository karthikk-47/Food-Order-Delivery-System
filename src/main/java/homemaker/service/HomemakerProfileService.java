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
package com.foodapp.deliveryexecutive.homemaker.service;

import com.foodapp.deliveryexecutive.homemaker.dto.HomemakerProfileDTO;
import com.foodapp.deliveryexecutive.homemaker.entity.HomemakerProfile;
import com.foodapp.deliveryexecutive.homemaker.repository.HomemakerProfileRepository;
import java.time.LocalDateTime;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HomemakerProfileService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(HomemakerProfileService.class);
    @Autowired
    private HomemakerProfileRepository homemakerProfileRepository;

    public HomemakerProfileDTO createProfile(HomemakerProfileDTO profileDTO) {
        log.info("Creating homemaker profile for user: {}", (Object)profileDTO.getUserId());
        HomemakerProfile profile = new HomemakerProfile();
        profile.setUserId(profileDTO.getUserId());
        profile.setFullName(profileDTO.getFullName());
        profile.setBio(profileDTO.getBio());
        profile.setPhoneNumber(profileDTO.getPhoneNumber());
        profile.setEmail(profileDTO.getEmail());
        profile.setSpecialities(profileDTO.getSpecialities());
        profile.setBusinessAddress(profileDTO.getBusinessAddress());
        profile.setCity(profileDTO.getCity());
        profile.setState(profileDTO.getState());
        profile.setZipCode(profileDTO.getZipCode());
        profile.setOperatingHours(profileDTO.getOperatingHours());
        profile.setYearsOfExperience(profileDTO.getYearsOfExperience());
        HomemakerProfile savedProfile = (HomemakerProfile)this.homemakerProfileRepository.save(profile);
        log.info("Homemaker profile created successfully with ID: {}", (Object)savedProfile.getId());
        return this.convertToDTO(savedProfile);
    }

    public HomemakerProfileDTO getProfileById(Long id) {
        log.debug("Fetching profile with ID: {}", (Object)id);
        HomemakerProfile profile = (HomemakerProfile)this.homemakerProfileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        return this.convertToDTO(profile);
    }

    public HomemakerProfileDTO getProfileByUserId(Long userId) {
        log.debug("Fetching profile for user: {}", (Object)userId);
        HomemakerProfile profile = this.homemakerProfileRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("Profile not found for user"));
        return this.convertToDTO(profile);
    }

    public HomemakerProfileDTO updateProfile(Long id, HomemakerProfileDTO profileDTO) {
        log.info("Updating profile with ID: {}", (Object)id);
        HomemakerProfile profile = (HomemakerProfile)this.homemakerProfileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        if (profileDTO.getFullName() != null) {
            profile.setFullName(profileDTO.getFullName());
        }
        if (profileDTO.getBio() != null) {
            profile.setBio(profileDTO.getBio());
        }
        if (profileDTO.getSpecialities() != null) {
            profile.setSpecialities(profileDTO.getSpecialities());
        }
        if (profileDTO.getOperatingHours() != null) {
            profile.setOperatingHours(profileDTO.getOperatingHours());
        }
        if (profileDTO.getCancellationPolicy() != null) {
            profile.setCancellationPolicy(profileDTO.getCancellationPolicy());
        }
        HomemakerProfile updatedProfile = (HomemakerProfile)this.homemakerProfileRepository.save(profile);
        log.info("Profile updated successfully");
        return this.convertToDTO(updatedProfile);
    }

    public HomemakerProfileDTO verifyHomemaker(Long id) {
        log.info("Verifying homemaker with ID: {}", (Object)id);
        HomemakerProfile profile = (HomemakerProfile)this.homemakerProfileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        profile.setIsVerified(true);
        profile.setVerifiedAt(LocalDateTime.now());
        profile.setAccountStatus(HomemakerProfile.AccountStatus.ACTIVE);
        HomemakerProfile verifiedProfile = (HomemakerProfile)this.homemakerProfileRepository.save(profile);
        log.info("Homemaker verified successfully");
        return this.convertToDTO(verifiedProfile);
    }

    public HomemakerProfileDTO suspendAccount(Long id) {
        log.info("Suspending account for homemaker: {}", (Object)id);
        HomemakerProfile profile = (HomemakerProfile)this.homemakerProfileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        profile.setAccountStatus(HomemakerProfile.AccountStatus.SUSPENDED);
        HomemakerProfile suspendedProfile = (HomemakerProfile)this.homemakerProfileRepository.save(profile);
        log.info("Account suspended successfully");
        return this.convertToDTO(suspendedProfile);
    }

    public void updateWalletBalance(Long id, Double amount) {
        log.info("Updating wallet balance for homemaker: {} with amount: {}", (Object)id, (Object)amount);
        HomemakerProfile profile = (HomemakerProfile)this.homemakerProfileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        profile.setWalletBalance(profile.getWalletBalance() + amount);
        this.homemakerProfileRepository.save(profile);
        log.info("Wallet balance updated successfully");
    }

    private HomemakerProfileDTO convertToDTO(HomemakerProfile profile) {
        HomemakerProfileDTO dto = new HomemakerProfileDTO();
        dto.setId(profile.getId());
        dto.setUserId(profile.getUserId());
        dto.setFullName(profile.getFullName());
        dto.setBio(profile.getBio());
        dto.setProfileImage(profile.getProfileImage());
        dto.setPhoneNumber(profile.getPhoneNumber());
        dto.setEmail(profile.getEmail());
        dto.setAccountStatus(profile.getAccountStatus());
        dto.setIsVerified(profile.getIsVerified());
        dto.setVerifiedAt(profile.getVerifiedAt());
        dto.setSpecialities(profile.getSpecialities());
        dto.setAverageRating(profile.getAverageRating());
        dto.setTotalRatings(profile.getTotalRatings());
        dto.setTotalOrders(profile.getTotalOrders());
        dto.setCuisinePreference(profile.getCuisinePreference());
        dto.setBusinessAddress(profile.getBusinessAddress());
        dto.setCity(profile.getCity());
        dto.setState(profile.getState());
        dto.setZipCode(profile.getZipCode());
        dto.setOperatingHours(profile.getOperatingHours());
        dto.setYearsOfExperience(profile.getYearsOfExperience());
        dto.setTotalEarnings(profile.getTotalEarnings());
        dto.setWalletBalance(profile.getWalletBalance());
        dto.setCreatedAt(profile.getCreatedAt());
        dto.setUpdatedAt(profile.getUpdatedAt());
        return dto;
    }
}
