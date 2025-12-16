package com.foodapp.deliveryexecutive.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Generated;

@Entity
@Table(name="user_favourite_homemakers")
public class FavouriteHomemaker {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long homemakerId;
    private LocalDateTime addedAt;
    private LocalDateTime removedAt;
    @Enumerated(value=EnumType.STRING)
    private FavouriteStatus status;

    @PrePersist
    protected void onCreate() {
        this.addedAt = LocalDateTime.now();
        this.status = FavouriteStatus.ACTIVE;
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getUserId() {
        return this.userId;
    }

    @Generated
    public Long getHomemakerId() {
        return this.homemakerId;
    }

    @Generated
    public LocalDateTime getAddedAt() {
        return this.addedAt;
    }

    @Generated
    public LocalDateTime getRemovedAt() {
        return this.removedAt;
    }

    @Generated
    public FavouriteStatus getStatus() {
        return this.status;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated
    public void setHomemakerId(Long homemakerId) {
        this.homemakerId = homemakerId;
    }

    @Generated
    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    @Generated
    public void setRemovedAt(LocalDateTime removedAt) {
        this.removedAt = removedAt;
    }

    @Generated
    public void setStatus(FavouriteStatus status) {
        this.status = status;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FavouriteHomemaker)) {
            return false;
        }
        FavouriteHomemaker other = (FavouriteHomemaker)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !(this$id).equals(other$id)) {
            return false;
        }
        Long this$userId = this.getUserId();
        Long other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !(this$userId).equals(other$userId)) {
            return false;
        }
        Long this$homemakerId = this.getHomemakerId();
        Long other$homemakerId = other.getHomemakerId();
        if (this$homemakerId == null ? other$homemakerId != null : !(this$homemakerId).equals(other$homemakerId)) {
            return false;
        }
        LocalDateTime this$addedAt = this.getAddedAt();
        LocalDateTime other$addedAt = other.getAddedAt();
        if (this$addedAt == null ? other$addedAt != null : !(this$addedAt).equals(other$addedAt)) {
            return false;
        }
        LocalDateTime this$removedAt = this.getRemovedAt();
        LocalDateTime other$removedAt = other.getRemovedAt();
        if (this$removedAt == null ? other$removedAt != null : !(this$removedAt).equals(other$removedAt)) {
            return false;
        }
        FavouriteStatus this$status = this.getStatus();
        FavouriteStatus other$status = other.getStatus();
        return !(this$status == null ? other$status != null : !((Object)(this$status)).equals(other$status));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof FavouriteHomemaker;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Long $homemakerId = this.getHomemakerId();
        result = result * 59 + ($homemakerId == null ? 43 : ((Object)$homemakerId).hashCode());
        LocalDateTime $addedAt = this.getAddedAt();
        result = result * 59 + ($addedAt == null ? 43 : ((Object)$addedAt).hashCode());
        LocalDateTime $removedAt = this.getRemovedAt();
        result = result * 59 + ($removedAt == null ? 43 : ((Object)$removedAt).hashCode());
        FavouriteStatus $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)((Object)$status)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "FavouriteHomemaker(id=" + this.getId() + ", userId=" + this.getUserId() + ", homemakerId=" + this.getHomemakerId() + ", addedAt=" + String.valueOf(this.getAddedAt()) + ", removedAt=" + String.valueOf(this.getRemovedAt()) + ", status=" + String.valueOf(this.getStatus()) + ")";
    }

    @Generated
    public FavouriteHomemaker() {
    }

    @Generated
    public FavouriteHomemaker(Long id, Long userId, Long homemakerId, LocalDateTime addedAt, LocalDateTime removedAt, FavouriteStatus status) {
        this.id = id;
        this.userId = userId;
        this.homemakerId = homemakerId;
        this.addedAt = addedAt;
        this.removedAt = removedAt;
        this.status = status;
    }

    public static enum FavouriteStatus {
        ACTIVE,
        REMOVED;

    }
}
