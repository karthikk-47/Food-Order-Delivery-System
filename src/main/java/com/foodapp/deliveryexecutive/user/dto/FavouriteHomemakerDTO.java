/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.user.dto;

import com.foodapp.deliveryexecutive.user.entity.FavouriteHomemaker;
import java.time.LocalDateTime;
import lombok.Generated;

public class FavouriteHomemakerDTO {
    private Long id;
    private Long userId;
    private Long homemakerId;
    private String homemakerName;
    private Double homemakerRating;
    private String homemakerImage;
    private LocalDateTime addedAt;
    private LocalDateTime removedAt;
    private FavouriteHomemaker.FavouriteStatus status;

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
    public String getHomemakerName() {
        return this.homemakerName;
    }

    @Generated
    public Double getHomemakerRating() {
        return this.homemakerRating;
    }

    @Generated
    public String getHomemakerImage() {
        return this.homemakerImage;
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
    public FavouriteHomemaker.FavouriteStatus getStatus() {
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
    public void setHomemakerName(String homemakerName) {
        this.homemakerName = homemakerName;
    }

    @Generated
    public void setHomemakerRating(Double homemakerRating) {
        this.homemakerRating = homemakerRating;
    }

    @Generated
    public void setHomemakerImage(String homemakerImage) {
        this.homemakerImage = homemakerImage;
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
    public void setStatus(FavouriteHomemaker.FavouriteStatus status) {
        this.status = status;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FavouriteHomemakerDTO)) {
            return false;
        }
        FavouriteHomemakerDTO other = (FavouriteHomemakerDTO)o;
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
        Double this$homemakerRating = this.getHomemakerRating();
        Double other$homemakerRating = other.getHomemakerRating();
        if (this$homemakerRating == null ? other$homemakerRating != null : !(this$homemakerRating).equals(other$homemakerRating)) {
            return false;
        }
        String this$homemakerName = this.getHomemakerName();
        String other$homemakerName = other.getHomemakerName();
        if (this$homemakerName == null ? other$homemakerName != null : !this$homemakerName.equals(other$homemakerName)) {
            return false;
        }
        String this$homemakerImage = this.getHomemakerImage();
        String other$homemakerImage = other.getHomemakerImage();
        if (this$homemakerImage == null ? other$homemakerImage != null : !this$homemakerImage.equals(other$homemakerImage)) {
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
        FavouriteHomemaker.FavouriteStatus this$status = this.getStatus();
        FavouriteHomemaker.FavouriteStatus other$status = other.getStatus();
        return !(this$status == null ? other$status != null : !((Object)(this$status)).equals(other$status));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof FavouriteHomemakerDTO;
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
        Double $homemakerRating = this.getHomemakerRating();
        result = result * 59 + ($homemakerRating == null ? 43 : ((Object)$homemakerRating).hashCode());
        String $homemakerName = this.getHomemakerName();
        result = result * 59 + ($homemakerName == null ? 43 : $homemakerName.hashCode());
        String $homemakerImage = this.getHomemakerImage();
        result = result * 59 + ($homemakerImage == null ? 43 : $homemakerImage.hashCode());
        LocalDateTime $addedAt = this.getAddedAt();
        result = result * 59 + ($addedAt == null ? 43 : ((Object)$addedAt).hashCode());
        LocalDateTime $removedAt = this.getRemovedAt();
        result = result * 59 + ($removedAt == null ? 43 : ((Object)$removedAt).hashCode());
        FavouriteHomemaker.FavouriteStatus $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)((Object)$status)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "FavouriteHomemakerDTO(id=" + this.getId() + ", userId=" + this.getUserId() + ", homemakerId=" + this.getHomemakerId() + ", homemakerName=" + this.getHomemakerName() + ", homemakerRating=" + this.getHomemakerRating() + ", homemakerImage=" + this.getHomemakerImage() + ", addedAt=" + String.valueOf(this.getAddedAt()) + ", removedAt=" + String.valueOf(this.getRemovedAt()) + ", status=" + String.valueOf(this.getStatus()) + ")";
    }

    @Generated
    public FavouriteHomemakerDTO() {
    }

    @Generated
    public FavouriteHomemakerDTO(Long id, Long userId, Long homemakerId, String homemakerName, Double homemakerRating, String homemakerImage, LocalDateTime addedAt, LocalDateTime removedAt, FavouriteHomemaker.FavouriteStatus status) {
        this.id = id;
        this.userId = userId;
        this.homemakerId = homemakerId;
        this.homemakerName = homemakerName;
        this.homemakerRating = homemakerRating;
        this.homemakerImage = homemakerImage;
        this.addedAt = addedAt;
        this.removedAt = removedAt;
        this.status = status;
    }
}
