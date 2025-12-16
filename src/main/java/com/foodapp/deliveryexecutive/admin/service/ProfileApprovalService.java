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

import com.foodapp.deliveryexecutive.admin.dto.ProfileApprovalDTO;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileApprovalService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(ProfileApprovalService.class);
    @Autowired
    private HomeMakerRepository homeMakerRepository;
    @Autowired
    private DeliveryExecutiveRepository deliveryExecutiveRepository;

    public ProfileApprovalDTO approveHomemaker(Long homemakerId, Long adminId) {
        log.info("Approving homemaker profile with ID: {} by admin: {}", homemakerId, adminId);
        HomeMaker homeMaker = (HomeMaker)this.homeMakerRepository.findById(homemakerId).orElseThrow(() -> new IllegalArgumentException("Homemaker not found with ID: " + homemakerId));
        if (homeMaker.getApprovalStatus() == HomeMaker.ApprovalStatus.APPROVED) {
            throw new IllegalStateException("Homemaker is already approved");
        }
        homeMaker.setApprovalStatus(HomeMaker.ApprovalStatus.APPROVED);
        homeMaker.setRejectionReason(null);
        HomeMaker savedHomeMaker = (HomeMaker)this.homeMakerRepository.save(homeMaker);
        log.info("Homemaker {} approved successfully by admin {}", homemakerId, adminId);
        return this.convertHomemakerToDTO(savedHomeMaker, adminId, LocalDateTime.now());
    }

    public ProfileApprovalDTO rejectHomemaker(Long homemakerId, Long adminId, String reason) {
        log.info("Rejecting homemaker profile with ID: {} by admin: {} with reason: {}", new Object[]{homemakerId, adminId, reason});
        HomeMaker homeMaker = (HomeMaker)this.homeMakerRepository.findById(homemakerId).orElseThrow(() -> new IllegalArgumentException("Homemaker not found with ID: " + homemakerId));
        homeMaker.setApprovalStatus(HomeMaker.ApprovalStatus.REJECTED);
        homeMaker.setRejectionReason(reason);
        HomeMaker savedHomeMaker = (HomeMaker)this.homeMakerRepository.save(homeMaker);
        log.info("Homemaker {} rejected successfully by admin {}", homemakerId, adminId);
        return this.convertHomemakerToDTO(savedHomeMaker, adminId, LocalDateTime.now());
    }

    public List<ProfileApprovalDTO> getPendingHomemakerApprovals() {
        log.debug("Fetching pending homemaker approvals");
        List<HomeMaker> pendingHomemakers = this.homeMakerRepository.findByApprovalStatus(HomeMaker.ApprovalStatus.PENDING);
        return pendingHomemakers.stream().map(h -> this.convertHomemakerToDTO((HomeMaker)h, null, null)).collect(Collectors.toList());
    }

    public List<ProfileApprovalDTO> getApprovedHomemakers() {
        log.debug("Fetching approved homemakers");
        List<HomeMaker> approvedHomemakers = this.homeMakerRepository.findByApprovalStatus(HomeMaker.ApprovalStatus.APPROVED);
        return approvedHomemakers.stream().map(h -> this.convertHomemakerToDTO((HomeMaker)h, null, null)).collect(Collectors.toList());
    }

    public List<ProfileApprovalDTO> getRejectedHomemakers() {
        log.debug("Fetching rejected homemakers");
        List<HomeMaker> rejectedHomemakers = this.homeMakerRepository.findByApprovalStatus(HomeMaker.ApprovalStatus.REJECTED);
        return rejectedHomemakers.stream().map(h -> this.convertHomemakerToDTO((HomeMaker)h, null, null)).collect(Collectors.toList());
    }

    public ProfileApprovalDTO revertHomemakerToPending(Long homemakerId, Long adminId) {
        log.info("Reverting homemaker {} to pending status by admin {}", homemakerId, adminId);
        HomeMaker homeMaker = (HomeMaker)this.homeMakerRepository.findById(homemakerId).orElseThrow(() -> new IllegalArgumentException("Homemaker not found with ID: " + homemakerId));
        homeMaker.setApprovalStatus(HomeMaker.ApprovalStatus.PENDING);
        homeMaker.setRejectionReason(null);
        HomeMaker savedHomeMaker = (HomeMaker)this.homeMakerRepository.save(homeMaker);
        log.info("Homemaker {} reverted to pending successfully", homemakerId);
        return this.convertHomemakerToDTO(savedHomeMaker, null, null);
    }

    public ProfileApprovalDTO approveExecutive(Long executiveId, Long adminId) {
        log.info("Approving delivery executive profile with ID: {} by admin: {}", executiveId, adminId);
        DeliveryExecutive executive = (DeliveryExecutive)this.deliveryExecutiveRepository.findById(executiveId).orElseThrow(() -> new IllegalArgumentException("Delivery Executive not found with ID: " + executiveId));
        if (executive.getApprovalStatus() == DeliveryExecutive.ApprovalStatus.APPROVED) {
            throw new IllegalStateException("Delivery Executive is already approved");
        }
        executive.setApprovalStatus(DeliveryExecutive.ApprovalStatus.APPROVED);
        executive.setRejectionReason(null);
        DeliveryExecutive savedExecutive = (DeliveryExecutive)this.deliveryExecutiveRepository.save(executive);
        log.info("Delivery Executive {} approved successfully by admin {}", executiveId, adminId);
        return this.convertExecutiveToDTO(savedExecutive, adminId, LocalDateTime.now());
    }

    public ProfileApprovalDTO rejectExecutive(Long executiveId, Long adminId, String reason) {
        log.info("Rejecting delivery executive profile with ID: {} by admin: {} with reason: {}", new Object[]{executiveId, adminId, reason});
        DeliveryExecutive executive = (DeliveryExecutive)this.deliveryExecutiveRepository.findById(executiveId).orElseThrow(() -> new IllegalArgumentException("Delivery Executive not found with ID: " + executiveId));
        executive.setApprovalStatus(DeliveryExecutive.ApprovalStatus.REJECTED);
        executive.setRejectionReason(reason);
        DeliveryExecutive savedExecutive = (DeliveryExecutive)this.deliveryExecutiveRepository.save(executive);
        log.info("Delivery Executive {} rejected successfully by admin {}", executiveId, adminId);
        return this.convertExecutiveToDTO(savedExecutive, adminId, LocalDateTime.now());
    }

    public List<ProfileApprovalDTO> getPendingExecutiveApprovals() {
        log.debug("Fetching pending delivery executive approvals");
        List<DeliveryExecutive> pendingExecutives = this.deliveryExecutiveRepository.findByApprovalStatus(DeliveryExecutive.ApprovalStatus.PENDING);
        return pendingExecutives.stream().map(e -> this.convertExecutiveToDTO((DeliveryExecutive)e, null, null)).collect(Collectors.toList());
    }

    public List<ProfileApprovalDTO> getApprovedExecutives() {
        log.debug("Fetching approved delivery executives");
        List<DeliveryExecutive> approvedExecutives = this.deliveryExecutiveRepository.findByApprovalStatus(DeliveryExecutive.ApprovalStatus.APPROVED);
        return approvedExecutives.stream().map(e -> this.convertExecutiveToDTO((DeliveryExecutive)e, null, null)).collect(Collectors.toList());
    }

    public List<ProfileApprovalDTO> getRejectedExecutives() {
        log.debug("Fetching rejected delivery executives");
        List<DeliveryExecutive> rejectedExecutives = this.deliveryExecutiveRepository.findByApprovalStatus(DeliveryExecutive.ApprovalStatus.REJECTED);
        return rejectedExecutives.stream().map(e -> this.convertExecutiveToDTO((DeliveryExecutive)e, null, null)).collect(Collectors.toList());
    }

    public ProfileApprovalDTO revertExecutiveToPending(Long executiveId, Long adminId) {
        log.info("Reverting delivery executive {} to pending status by admin {}", executiveId, adminId);
        DeliveryExecutive executive = (DeliveryExecutive)this.deliveryExecutiveRepository.findById(executiveId).orElseThrow(() -> new IllegalArgumentException("Delivery Executive not found with ID: " + executiveId));
        executive.setApprovalStatus(DeliveryExecutive.ApprovalStatus.PENDING);
        executive.setRejectionReason(null);
        DeliveryExecutive savedExecutive = (DeliveryExecutive)this.deliveryExecutiveRepository.save(executive);
        log.info("Delivery Executive {} reverted to pending successfully", executiveId);
        return this.convertExecutiveToDTO(savedExecutive, null, null);
    }

    public List<ProfileApprovalDTO> getAllPendingApprovals() {
        log.debug("Fetching all pending approvals");
        ArrayList<ProfileApprovalDTO> allPending = new ArrayList<ProfileApprovalDTO>();
        allPending.addAll(this.getPendingHomemakerApprovals());
        allPending.addAll(this.getPendingExecutiveApprovals());
        return allPending;
    }

    public Map<String, Object> getApprovalStatistics() {
        log.debug("Fetching approval statistics");
        HashMap<String, Object> stats = new HashMap<String, Object>();
        long pendingHomemakers = this.homeMakerRepository.countByApprovalStatus(HomeMaker.ApprovalStatus.PENDING);
        long approvedHomemakers = this.homeMakerRepository.countByApprovalStatus(HomeMaker.ApprovalStatus.APPROVED);
        long rejectedHomemakers = this.homeMakerRepository.countByApprovalStatus(HomeMaker.ApprovalStatus.REJECTED);
        long pendingExecutives = this.deliveryExecutiveRepository.countByApprovalStatus(DeliveryExecutive.ApprovalStatus.PENDING);
        long approvedExecutives = this.deliveryExecutiveRepository.countByApprovalStatus(DeliveryExecutive.ApprovalStatus.APPROVED);
        long rejectedExecutives = this.deliveryExecutiveRepository.countByApprovalStatus(DeliveryExecutive.ApprovalStatus.REJECTED);
        stats.put("pendingHomemakers", pendingHomemakers);
        stats.put("approvedHomemakers", approvedHomemakers);
        stats.put("rejectedHomemakers", rejectedHomemakers);
        stats.put("totalHomemakers", pendingHomemakers + approvedHomemakers + rejectedHomemakers);
        stats.put("pendingExecutives", pendingExecutives);
        stats.put("approvedExecutives", approvedExecutives);
        stats.put("rejectedExecutives", rejectedExecutives);
        stats.put("totalExecutives", pendingExecutives + approvedExecutives + rejectedExecutives);
        stats.put("totalPending", pendingHomemakers + pendingExecutives);
        stats.put("totalApproved", approvedHomemakers + approvedExecutives);
        stats.put("totalRejected", rejectedHomemakers + rejectedExecutives);
        return stats;
    }

    public List<ProfileApprovalDTO> bulkApproveHomemakers(List<Long> homemakerIds, Long adminId) {
        log.info("Bulk approving {} homemakers by admin {}", homemakerIds.size(), adminId);
        ArrayList<ProfileApprovalDTO> results = new ArrayList<ProfileApprovalDTO>();
        for (Long id : homemakerIds) {
            try {
                results.add(this.approveHomemaker(id, adminId));
            }
            catch (Exception e) {
                log.error("Failed to approve homemaker {}: {}", id, e.getMessage());
            }
        }
        return results;
    }

    public List<ProfileApprovalDTO> bulkApproveExecutives(List<Long> executiveIds, Long adminId) {
        log.info("Bulk approving {} delivery executives by admin {}", executiveIds.size(), adminId);
        ArrayList<ProfileApprovalDTO> results = new ArrayList<ProfileApprovalDTO>();
        for (Long id : executiveIds) {
            try {
                results.add(this.approveExecutive(id, adminId));
            }
            catch (Exception e) {
                log.error("Failed to approve delivery executive {}: {}", id, e.getMessage());
            }
        }
        return results;
    }

    public boolean isHomemakerApproved(Long homemakerId) {
        return this.homeMakerRepository.findById(homemakerId).map(h -> h.getApprovalStatus() == HomeMaker.ApprovalStatus.APPROVED).orElse(false);
    }

    public boolean isExecutiveApproved(Long executiveId) {
        return this.deliveryExecutiveRepository.findById(executiveId).map(e -> e.getApprovalStatus() == DeliveryExecutive.ApprovalStatus.APPROVED).orElse(false);
    }

    public String getHomemakerApprovalStatus(Long homemakerId) {
        return this.homeMakerRepository.findById(homemakerId).map(h -> h.getApprovalStatus().name()).orElseThrow(() -> new IllegalArgumentException("Homemaker not found"));
    }

    public String getExecutiveApprovalStatus(Long executiveId) {
        return this.deliveryExecutiveRepository.findById(executiveId).map(e -> e.getApprovalStatus().name()).orElseThrow(() -> new IllegalArgumentException("Delivery Executive not found"));
    }

    private ProfileApprovalDTO convertHomemakerToDTO(HomeMaker homeMaker, Long approvedBy, LocalDateTime approvedAt) {
        ProfileApprovalDTO dto = new ProfileApprovalDTO();
        dto.setId(homeMaker.getId());
        dto.setName(homeMaker.getName());
        dto.setMobile(homeMaker.getMobile());
        dto.setUserType("HOMEMAKER");
        dto.setApprovalStatus(homeMaker.getApprovalStatus().name());
        dto.setRejectionReason(homeMaker.getRejectionReason());
        dto.setAddress(homeMaker.getAddress());
        dto.setApprovedBy(approvedBy);
        dto.setApprovedAt(approvedAt);
        return dto;
    }

    private ProfileApprovalDTO convertExecutiveToDTO(DeliveryExecutive executive, Long approvedBy, LocalDateTime approvedAt) {
        ProfileApprovalDTO dto = new ProfileApprovalDTO();
        dto.setId(executive.getId());
        dto.setName(executive.getName());
        dto.setMobile(executive.getMobile());
        dto.setUserType("DELIVERY_EXECUTIVE");
        dto.setApprovalStatus(executive.getApprovalStatus().name());
        dto.setRejectionReason(executive.getRejectionReason());
        dto.setAadharNo(executive.getAadharNo());
        dto.setLicenseNo(executive.getLicenseNo());
        dto.setApprovedBy(approvedBy);
        dto.setApprovedAt(approvedAt);
        return dto;
    }
}
