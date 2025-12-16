package com.foodapp.deliveryexecutive.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Generated;

@Entity
@Table(name="disputes")
public class Dispute {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Long complainantId;
    private Long respondentId;
    private String complainantType;
    private String respondentType;
    @Enumerated(value=EnumType.STRING)
    private DisputeCategory category;
    private String description;
    @Enumerated(value=EnumType.STRING)
    private DisputeStatus status;
    @Enumerated(value=EnumType.STRING)
    private DisputeResolution resolution;
    private Double refundAmount;
    private String resolutionNotes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;
    private Long assignedAdminId;
    private LocalDateTime assignedAt;
    private Integer escalationLevel;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = DisputeStatus.OPEN;
        this.escalationLevel = 0;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getOrderId() {
        return this.orderId;
    }

    @Generated
    public Long getComplainantId() {
        return this.complainantId;
    }

    @Generated
    public Long getRespondentId() {
        return this.respondentId;
    }

    @Generated
    public String getComplainantType() {
        return this.complainantType;
    }

    @Generated
    public String getRespondentType() {
        return this.respondentType;
    }

    @Generated
    public DisputeCategory getCategory() {
        return this.category;
    }

    @Generated
    public String getDescription() {
        return this.description;
    }

    @Generated
    public DisputeStatus getStatus() {
        return this.status;
    }

    @Generated
    public DisputeResolution getResolution() {
        return this.resolution;
    }

    @Generated
    public Double getRefundAmount() {
        return this.refundAmount;
    }

    @Generated
    public String getResolutionNotes() {
        return this.resolutionNotes;
    }

    @Generated
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Generated
    public LocalDateTime getResolvedAt() {
        return this.resolvedAt;
    }

    @Generated
    public Long getAssignedAdminId() {
        return this.assignedAdminId;
    }

    @Generated
    public LocalDateTime getAssignedAt() {
        return this.assignedAt;
    }

    @Generated
    public Integer getEscalationLevel() {
        return this.escalationLevel;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Generated
    public void setComplainantId(Long complainantId) {
        this.complainantId = complainantId;
    }

    @Generated
    public void setRespondentId(Long respondentId) {
        this.respondentId = respondentId;
    }

    @Generated
    public void setComplainantType(String complainantType) {
        this.complainantType = complainantType;
    }

    @Generated
    public void setRespondentType(String respondentType) {
        this.respondentType = respondentType;
    }

    @Generated
    public void setCategory(DisputeCategory category) {
        this.category = category;
    }

    @Generated
    public void setDescription(String description) {
        this.description = description;
    }

    @Generated
    public void setStatus(DisputeStatus status) {
        this.status = status;
    }

    @Generated
    public void setResolution(DisputeResolution resolution) {
        this.resolution = resolution;
    }

    @Generated
    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    @Generated
    public void setResolutionNotes(String resolutionNotes) {
        this.resolutionNotes = resolutionNotes;
    }

    @Generated
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated
    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    @Generated
    public void setAssignedAdminId(Long assignedAdminId) {
        this.assignedAdminId = assignedAdminId;
    }

    @Generated
    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    @Generated
    public void setEscalationLevel(Integer escalationLevel) {
        this.escalationLevel = escalationLevel;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Dispute)) {
            return false;
        }
        Dispute other = (Dispute)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !(this$id).equals(other$id)) {
            return false;
        }
        Long this$orderId = this.getOrderId();
        Long other$orderId = other.getOrderId();
        if (this$orderId == null ? other$orderId != null : !(this$orderId).equals(other$orderId)) {
            return false;
        }
        Long this$complainantId = this.getComplainantId();
        Long other$complainantId = other.getComplainantId();
        if (this$complainantId == null ? other$complainantId != null : !(this$complainantId).equals(other$complainantId)) {
            return false;
        }
        Long this$respondentId = this.getRespondentId();
        Long other$respondentId = other.getRespondentId();
        if (this$respondentId == null ? other$respondentId != null : !(this$respondentId).equals(other$respondentId)) {
            return false;
        }
        Double this$refundAmount = this.getRefundAmount();
        Double other$refundAmount = other.getRefundAmount();
        if (this$refundAmount == null ? other$refundAmount != null : !(this$refundAmount).equals(other$refundAmount)) {
            return false;
        }
        Long this$assignedAdminId = this.getAssignedAdminId();
        Long other$assignedAdminId = other.getAssignedAdminId();
        if (this$assignedAdminId == null ? other$assignedAdminId != null : !(this$assignedAdminId).equals(other$assignedAdminId)) {
            return false;
        }
        Integer this$escalationLevel = this.getEscalationLevel();
        Integer other$escalationLevel = other.getEscalationLevel();
        if (this$escalationLevel == null ? other$escalationLevel != null : !(this$escalationLevel).equals(other$escalationLevel)) {
            return false;
        }
        String this$complainantType = this.getComplainantType();
        String other$complainantType = other.getComplainantType();
        if (this$complainantType == null ? other$complainantType != null : !this$complainantType.equals(other$complainantType)) {
            return false;
        }
        String this$respondentType = this.getRespondentType();
        String other$respondentType = other.getRespondentType();
        if (this$respondentType == null ? other$respondentType != null : !this$respondentType.equals(other$respondentType)) {
            return false;
        }
        DisputeCategory this$category = this.getCategory();
        DisputeCategory other$category = other.getCategory();
        if (this$category == null ? other$category != null : !((Object)(this$category)).equals(other$category)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        DisputeStatus this$status = this.getStatus();
        DisputeStatus other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)(this$status)).equals(other$status)) {
            return false;
        }
        DisputeResolution this$resolution = this.getResolution();
        DisputeResolution other$resolution = other.getResolution();
        if (this$resolution == null ? other$resolution != null : !((Object)(this$resolution)).equals(other$resolution)) {
            return false;
        }
        String this$resolutionNotes = this.getResolutionNotes();
        String other$resolutionNotes = other.getResolutionNotes();
        if (this$resolutionNotes == null ? other$resolutionNotes != null : !this$resolutionNotes.equals(other$resolutionNotes)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !(this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$updatedAt = this.getUpdatedAt();
        LocalDateTime other$updatedAt = other.getUpdatedAt();
        if (this$updatedAt == null ? other$updatedAt != null : !(this$updatedAt).equals(other$updatedAt)) {
            return false;
        }
        LocalDateTime this$resolvedAt = this.getResolvedAt();
        LocalDateTime other$resolvedAt = other.getResolvedAt();
        if (this$resolvedAt == null ? other$resolvedAt != null : !(this$resolvedAt).equals(other$resolvedAt)) {
            return false;
        }
        LocalDateTime this$assignedAt = this.getAssignedAt();
        LocalDateTime other$assignedAt = other.getAssignedAt();
        return !(this$assignedAt == null ? other$assignedAt != null : !(this$assignedAt).equals(other$assignedAt));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Dispute;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $orderId = this.getOrderId();
        result = result * 59 + ($orderId == null ? 43 : ((Object)$orderId).hashCode());
        Long $complainantId = this.getComplainantId();
        result = result * 59 + ($complainantId == null ? 43 : ((Object)$complainantId).hashCode());
        Long $respondentId = this.getRespondentId();
        result = result * 59 + ($respondentId == null ? 43 : ((Object)$respondentId).hashCode());
        Double $refundAmount = this.getRefundAmount();
        result = result * 59 + ($refundAmount == null ? 43 : ((Object)$refundAmount).hashCode());
        Long $assignedAdminId = this.getAssignedAdminId();
        result = result * 59 + ($assignedAdminId == null ? 43 : ((Object)$assignedAdminId).hashCode());
        Integer $escalationLevel = this.getEscalationLevel();
        result = result * 59 + ($escalationLevel == null ? 43 : ((Object)$escalationLevel).hashCode());
        String $complainantType = this.getComplainantType();
        result = result * 59 + ($complainantType == null ? 43 : $complainantType.hashCode());
        String $respondentType = this.getRespondentType();
        result = result * 59 + ($respondentType == null ? 43 : $respondentType.hashCode());
        DisputeCategory $category = this.getCategory();
        result = result * 59 + ($category == null ? 43 : ((Object)((Object)$category)).hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        DisputeStatus $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)((Object)$status)).hashCode());
        DisputeResolution $resolution = this.getResolution();
        result = result * 59 + ($resolution == null ? 43 : ((Object)((Object)$resolution)).hashCode());
        String $resolutionNotes = this.getResolutionNotes();
        result = result * 59 + ($resolutionNotes == null ? 43 : $resolutionNotes.hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        LocalDateTime $resolvedAt = this.getResolvedAt();
        result = result * 59 + ($resolvedAt == null ? 43 : ((Object)$resolvedAt).hashCode());
        LocalDateTime $assignedAt = this.getAssignedAt();
        result = result * 59 + ($assignedAt == null ? 43 : ((Object)$assignedAt).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Dispute(id=" + this.getId() + ", orderId=" + this.getOrderId() + ", complainantId=" + this.getComplainantId() + ", respondentId=" + this.getRespondentId() + ", complainantType=" + this.getComplainantType() + ", respondentType=" + this.getRespondentType() + ", category=" + String.valueOf(this.getCategory()) + ", description=" + this.getDescription() + ", status=" + String.valueOf(this.getStatus()) + ", resolution=" + String.valueOf(this.getResolution()) + ", refundAmount=" + this.getRefundAmount() + ", resolutionNotes=" + this.getResolutionNotes() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ", resolvedAt=" + String.valueOf(this.getResolvedAt()) + ", assignedAdminId=" + this.getAssignedAdminId() + ", assignedAt=" + String.valueOf(this.getAssignedAt()) + ", escalationLevel=" + this.getEscalationLevel() + ")";
    }

    @Generated
    public Dispute() {
    }

    @Generated
    public Dispute(Long id, Long orderId, Long complainantId, Long respondentId, String complainantType, String respondentType, DisputeCategory category, String description, DisputeStatus status, DisputeResolution resolution, Double refundAmount, String resolutionNotes, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime resolvedAt, Long assignedAdminId, LocalDateTime assignedAt, Integer escalationLevel) {
        this.id = id;
        this.orderId = orderId;
        this.complainantId = complainantId;
        this.respondentId = respondentId;
        this.complainantType = complainantType;
        this.respondentType = respondentType;
        this.category = category;
        this.description = description;
        this.status = status;
        this.resolution = resolution;
        this.refundAmount = refundAmount;
        this.resolutionNotes = resolutionNotes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.resolvedAt = resolvedAt;
        this.assignedAdminId = assignedAdminId;
        this.assignedAt = assignedAt;
        this.escalationLevel = escalationLevel;
    }

    public static enum DisputeStatus {
        OPEN,
        IN_REVIEW,
        RESOLVED,
        CLOSED;

    }

    public static enum DisputeCategory {
        ORDER_NOT_RECEIVED,
        QUALITY_ISSUE,
        PAYMENT_ISSUE,
        LATE_DELIVERY,
        WRONG_ITEMS,
        HEALTH_SAFETY,
        REFUND_NOT_RECEIVED,
        OTHER;

    }

    public static enum DisputeResolution {
        REFUND,
        CREDIT,
        REPLACEMENT,
        NO_ACTION;

    }
}
