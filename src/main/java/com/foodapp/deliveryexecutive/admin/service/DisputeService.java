package com.foodapp.deliveryexecutive.admin.service;

import com.foodapp.deliveryexecutive.admin.dto.DisputeDTO;
import com.foodapp.deliveryexecutive.admin.entity.Dispute;
import com.foodapp.deliveryexecutive.admin.repository.DisputeRepository;
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
public class DisputeService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(DisputeService.class);
    @Autowired
    private DisputeRepository disputeRepository;

    public DisputeDTO createDispute(DisputeDTO disputeDTO) {
        log.info("Creating new dispute for order: {}", disputeDTO.getOrderId());
        Dispute dispute = new Dispute();
        dispute.setOrderId(disputeDTO.getOrderId());
        dispute.setComplainantId(disputeDTO.getComplainantId());
        dispute.setRespondentId(disputeDTO.getRespondentId());
        dispute.setComplainantType(disputeDTO.getComplainantType());
        dispute.setRespondentType(disputeDTO.getRespondentType());
        dispute.setCategory(disputeDTO.getCategory());
        dispute.setDescription(disputeDTO.getDescription());
        Dispute savedDispute = (Dispute)this.disputeRepository.save(dispute);
        log.info("Dispute created successfully with ID: {}", savedDispute.getId());
        return this.convertToDTO(savedDispute);
    }

    public DisputeDTO getDisputeById(Long id) {
        log.debug("Fetching dispute by ID: {}", id);
        return this.disputeRepository.findById(id).map(this::convertToDTO).orElseThrow(() -> new IllegalArgumentException("Dispute not found"));
    }

    public List<DisputeDTO> getDisputesByOrderId(Long orderId) {
        log.debug("Fetching disputes for order: {}", orderId);
        return this.disputeRepository.findByOrderId(orderId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<DisputeDTO> getDisputesByComplainant(Long complainantId) {
        log.debug("Fetching disputes by complainant: {}", complainantId);
        return this.disputeRepository.findByComplainantId(complainantId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<DisputeDTO> getOpenDisputes() {
        log.debug("Fetching all open disputes");
        return this.disputeRepository.findByStatus(Dispute.DisputeStatus.OPEN).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public DisputeDTO assignDisputeToAdmin(Long disputeId, Long adminId) {
        log.info("Assigning dispute: {} to admin: {}", disputeId, adminId);
        Dispute dispute = (Dispute)this.disputeRepository.findById(disputeId).orElseThrow(() -> new IllegalArgumentException("Dispute not found"));
        dispute.setAssignedAdminId(adminId);
        dispute.setAssignedAt(LocalDateTime.now());
        dispute.setStatus(Dispute.DisputeStatus.IN_REVIEW);
        Dispute updatedDispute = (Dispute)this.disputeRepository.save(dispute);
        log.info("Dispute assigned successfully");
        return this.convertToDTO(updatedDispute);
    }

    public DisputeDTO resolveDispute(Long disputeId, Dispute.DisputeResolution resolution, Double refundAmount, String notes) {
        log.info("Resolving dispute: {} with resolution: {}", disputeId, resolution);
        Dispute dispute = (Dispute)this.disputeRepository.findById(disputeId).orElseThrow(() -> new IllegalArgumentException("Dispute not found"));
        dispute.setResolution(resolution);
        dispute.setRefundAmount(refundAmount);
        dispute.setResolutionNotes(notes);
        dispute.setStatus(Dispute.DisputeStatus.RESOLVED);
        dispute.setResolvedAt(LocalDateTime.now());
        Dispute updatedDispute = (Dispute)this.disputeRepository.save(dispute);
        log.info("Dispute resolved successfully");
        return this.convertToDTO(updatedDispute);
    }

    public DisputeDTO closeDispute(Long disputeId) {
        log.info("Closing dispute: {}", disputeId);
        Dispute dispute = (Dispute)this.disputeRepository.findById(disputeId).orElseThrow(() -> new IllegalArgumentException("Dispute not found"));
        dispute.setStatus(Dispute.DisputeStatus.CLOSED);
        Dispute updatedDispute = (Dispute)this.disputeRepository.save(dispute);
        log.info("Dispute closed successfully");
        return this.convertToDTO(updatedDispute);
    }

    public DisputeDTO escalateDispute(Long disputeId, Long adminId) {
        log.info("Escalating dispute: {} to admin: {}", disputeId, adminId);
        Dispute dispute = (Dispute)this.disputeRepository.findById(disputeId).orElseThrow(() -> new IllegalArgumentException("Dispute not found"));
        dispute.setEscalationLevel(dispute.getEscalationLevel() + 1);
        dispute.setAssignedAdminId(adminId);
        Dispute updatedDispute = (Dispute)this.disputeRepository.save(dispute);
        log.info("Dispute escalated to level: {}", dispute.getEscalationLevel());
        return this.convertToDTO(updatedDispute);
    }

    public List<DisputeDTO> getDisputesByAssignedAdmin(Long adminId) {
        log.debug("Fetching disputes assigned to admin: {}", adminId);
        return this.disputeRepository.findByAssignedAdminId(adminId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<DisputeDTO> getOpenDisputesByAdmin(Long adminId) {
        log.debug("Fetching open disputes for admin: {}", adminId);
        return this.disputeRepository.findByStatusAndAssignedAdminId(Dispute.DisputeStatus.IN_REVIEW, adminId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<DisputeDTO> getDisputesByCategory(Dispute.DisputeCategory category) {
        log.debug("Fetching disputes by category: {}", category);
        return this.disputeRepository.findByCategory(category).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private DisputeDTO convertToDTO(Dispute dispute) {
        DisputeDTO dto = new DisputeDTO();
        dto.setId(dispute.getId());
        dto.setOrderId(dispute.getOrderId());
        dto.setComplainantId(dispute.getComplainantId());
        dto.setRespondentId(dispute.getRespondentId());
        dto.setComplainantType(dispute.getComplainantType());
        dto.setRespondentType(dispute.getRespondentType());
        dto.setCategory(dispute.getCategory());
        dto.setDescription(dispute.getDescription());
        dto.setStatus(dispute.getStatus());
        dto.setResolution(dispute.getResolution());
        dto.setRefundAmount(dispute.getRefundAmount());
        dto.setResolutionNotes(dispute.getResolutionNotes());
        dto.setCreatedAt(dispute.getCreatedAt());
        dto.setUpdatedAt(dispute.getUpdatedAt());
        dto.setResolvedAt(dispute.getResolvedAt());
        dto.setAssignedAdminId(dispute.getAssignedAdminId());
        dto.setAssignedAt(dispute.getAssignedAt());
        dto.setEscalationLevel(dispute.getEscalationLevel());
        return dto;
    }
}
