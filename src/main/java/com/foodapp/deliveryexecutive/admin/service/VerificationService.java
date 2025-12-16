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
package com.foodapp.deliveryexecutive.admin.service;

import com.foodapp.deliveryexecutive.admin.dto.VerificationDTO;
import com.foodapp.deliveryexecutive.admin.entity.Verification;
import com.foodapp.deliveryexecutive.admin.repository.VerificationRepository;
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
public class VerificationService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(VerificationService.class);
    @Autowired
    private VerificationRepository verificationRepository;

    public VerificationDTO submitVerification(VerificationDTO verificationDTO) {
        log.info("Submitting verification for user: {} of type: {}", verificationDTO.getUserId(), verificationDTO.getUserType());
        Verification verification = new Verification();
        verification.setUserId(verificationDTO.getUserId());
        verification.setUserType(verificationDTO.getUserType());
        verification.setVerificationType(verificationDTO.getVerificationType());
        verification.setDocumentUrl(verificationDTO.getDocumentUrl());
        verification.setIdentityNumber(verificationDTO.getIdentityNumber());
        verification.setBankAccountNumber(verificationDTO.getBankAccountNumber());
        verification.setPhoneNumber(verificationDTO.getPhoneNumber());
        verification.setEmail(verificationDTO.getEmail());
        Verification savedVerification = (Verification)this.verificationRepository.save(verification);
        log.info("Verification submitted successfully with ID: {}", savedVerification.getId());
        return this.convertToDTO(savedVerification);
    }

    public VerificationDTO approveVerification(Long verificationId, Long adminId) {
        log.info("Approving verification with ID: {} by admin: {}", verificationId, adminId);
        Verification verification = (Verification)this.verificationRepository.findById(verificationId).orElseThrow(() -> new IllegalArgumentException("Verification not found"));
        verification.setStatus(Verification.VerificationStatus.APPROVED);
        verification.setVerifiedBy(adminId);
        verification.setVerifiedAt(LocalDateTime.now());
        verification.setExpiresAt(LocalDateTime.now().plusYears(1L));
        Verification updatedVerification = (Verification)this.verificationRepository.save(verification);
        log.info("Verification approved successfully");
        return this.convertToDTO(updatedVerification);
    }

    public VerificationDTO rejectVerification(Long verificationId, Long adminId, String reason) {
        log.info("Rejecting verification with ID: {} by admin: {} with reason: {}", new Object[]{verificationId, adminId, reason});
        Verification verification = (Verification)this.verificationRepository.findById(verificationId).orElseThrow(() -> new IllegalArgumentException("Verification not found"));
        verification.setStatus(Verification.VerificationStatus.REJECTED);
        verification.setVerifiedBy(adminId);
        verification.setVerifiedAt(LocalDateTime.now());
        verification.setRejectionReason(reason);
        Verification updatedVerification = (Verification)this.verificationRepository.save(verification);
        log.info("Verification rejected successfully");
        return this.convertToDTO(updatedVerification);
    }

    public VerificationDTO getVerificationById(Long id) {
        log.debug("Fetching verification by ID: {}", id);
        return this.verificationRepository.findById(id).map(this::convertToDTO).orElseThrow(() -> new IllegalArgumentException("Verification not found"));
    }

    public List<VerificationDTO> getUserVerifications(Long userId, String userType) {
        log.debug("Fetching verifications for user: {} of type: {}", userId, userType);
        return this.verificationRepository.findByUserIdAndUserType(userId, userType).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<VerificationDTO> getPendingVerifications() {
        log.debug("Fetching pending verifications");
        return this.verificationRepository.findByStatus(Verification.VerificationStatus.PENDING).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<VerificationDTO> getPendingVerificationsByUserType(String userType) {
        log.debug("Fetching pending verifications for user type: {}", userType);
        return this.verificationRepository.findByUserTypeAndStatus(userType, Verification.VerificationStatus.PENDING).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public boolean hasRequiredVerification(Long userId, String userType, Verification.VerificationType type) {
        log.debug("Checking if user has required verification: {}", type);
        return this.verificationRepository.findByUserIdAndUserTypeAndVerificationType(userId, userType, type).filter(v -> v.getStatus() == Verification.VerificationStatus.APPROVED).filter(v -> v.getExpiresAt() == null || v.getExpiresAt().isAfter(LocalDateTime.now())).isPresent();
    }

    private VerificationDTO convertToDTO(Verification verification) {
        VerificationDTO dto = new VerificationDTO();
        dto.setId(verification.getId());
        dto.setUserId(verification.getUserId());
        dto.setUserType(verification.getUserType());
        dto.setVerificationType(verification.getVerificationType());
        dto.setStatus(verification.getStatus());
        dto.setDocumentUrl(verification.getDocumentUrl());
        dto.setIdentityNumber(verification.getIdentityNumber());
        dto.setBankAccountNumber(verification.getBankAccountNumber());
        dto.setPhoneNumber(verification.getPhoneNumber());
        dto.setEmail(verification.getEmail());
        dto.setRejectionReason(verification.getRejectionReason());
        dto.setSubmittedAt(verification.getSubmittedAt());
        dto.setVerifiedAt(verification.getVerifiedAt());
        dto.setExpiresAt(verification.getExpiresAt());
        dto.setVerificationAttempts(verification.getVerificationAttempts());
        return dto;
    }
}
