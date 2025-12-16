/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Entity
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.filestorage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Generated;

@Entity
@Table(name="file_info")
public class FileInfo {
    @Id
    private String id;
    private String originalFilename;
    private String fileType;
    private long size;
    private String filePath;
    private String fileUrl;
    private String entityType;
    private String entityId;
    private boolean isPrimary;
    private int sortOrder;
    private LocalDateTime uploadedAt;
    private String uploadedBy;

    @Generated
    public FileInfo() {
    }

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public String getOriginalFilename() {
        return this.originalFilename;
    }

    @Generated
    public String getFileType() {
        return this.fileType;
    }

    @Generated
    public long getSize() {
        return this.size;
    }

    @Generated
    public String getFilePath() {
        return this.filePath;
    }

    @Generated
    public String getFileUrl() {
        return this.fileUrl;
    }

    @Generated
    public String getEntityType() {
        return this.entityType;
    }

    @Generated
    public String getEntityId() {
        return this.entityId;
    }

    @Generated
    public boolean isPrimary() {
        return this.isPrimary;
    }

    @Generated
    public int getSortOrder() {
        return this.sortOrder;
    }

    @Generated
    public LocalDateTime getUploadedAt() {
        return this.uploadedAt;
    }

    @Generated
    public String getUploadedBy() {
        return this.uploadedBy;
    }

    @Generated
    public void setId(String id) {
        this.id = id;
    }

    @Generated
    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    @Generated
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Generated
    public void setSize(long size) {
        this.size = size;
    }

    @Generated
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Generated
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Generated
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    @Generated
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    @Generated
    public void setPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    @Generated
    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Generated
    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    @Generated
    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FileInfo)) {
            return false;
        }
        FileInfo other = (FileInfo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getSize() != other.getSize()) {
            return false;
        }
        if (this.isPrimary() != other.isPrimary()) {
            return false;
        }
        if (this.getSortOrder() != other.getSortOrder()) {
            return false;
        }
        String this$id = this.getId();
        String other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) {
            return false;
        }
        String this$originalFilename = this.getOriginalFilename();
        String other$originalFilename = other.getOriginalFilename();
        if (this$originalFilename == null ? other$originalFilename != null : !this$originalFilename.equals(other$originalFilename)) {
            return false;
        }
        String this$fileType = this.getFileType();
        String other$fileType = other.getFileType();
        if (this$fileType == null ? other$fileType != null : !this$fileType.equals(other$fileType)) {
            return false;
        }
        String this$filePath = this.getFilePath();
        String other$filePath = other.getFilePath();
        if (this$filePath == null ? other$filePath != null : !this$filePath.equals(other$filePath)) {
            return false;
        }
        String this$fileUrl = this.getFileUrl();
        String other$fileUrl = other.getFileUrl();
        if (this$fileUrl == null ? other$fileUrl != null : !this$fileUrl.equals(other$fileUrl)) {
            return false;
        }
        String this$entityType = this.getEntityType();
        String other$entityType = other.getEntityType();
        if (this$entityType == null ? other$entityType != null : !this$entityType.equals(other$entityType)) {
            return false;
        }
        String this$entityId = this.getEntityId();
        String other$entityId = other.getEntityId();
        if (this$entityId == null ? other$entityId != null : !this$entityId.equals(other$entityId)) {
            return false;
        }
        LocalDateTime this$uploadedAt = this.getUploadedAt();
        LocalDateTime other$uploadedAt = other.getUploadedAt();
        if (this$uploadedAt == null ? other$uploadedAt != null : !((Object)this$uploadedAt).equals(other$uploadedAt)) {
            return false;
        }
        String this$uploadedBy = this.getUploadedBy();
        String other$uploadedBy = other.getUploadedBy();
        return !(this$uploadedBy == null ? other$uploadedBy != null : !this$uploadedBy.equals(other$uploadedBy));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof FileInfo;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $size = this.getSize();
        result = result * 59 + (int)($size >>> 32 ^ $size);
        result = result * 59 + (this.isPrimary() ? 79 : 97);
        result = result * 59 + this.getSortOrder();
        String $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        String $originalFilename = this.getOriginalFilename();
        result = result * 59 + ($originalFilename == null ? 43 : $originalFilename.hashCode());
        String $fileType = this.getFileType();
        result = result * 59 + ($fileType == null ? 43 : $fileType.hashCode());
        String $filePath = this.getFilePath();
        result = result * 59 + ($filePath == null ? 43 : $filePath.hashCode());
        String $fileUrl = this.getFileUrl();
        result = result * 59 + ($fileUrl == null ? 43 : $fileUrl.hashCode());
        String $entityType = this.getEntityType();
        result = result * 59 + ($entityType == null ? 43 : $entityType.hashCode());
        String $entityId = this.getEntityId();
        result = result * 59 + ($entityId == null ? 43 : $entityId.hashCode());
        LocalDateTime $uploadedAt = this.getUploadedAt();
        result = result * 59 + ($uploadedAt == null ? 43 : ((Object)$uploadedAt).hashCode());
        String $uploadedBy = this.getUploadedBy();
        result = result * 59 + ($uploadedBy == null ? 43 : $uploadedBy.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "FileInfo(id=" + this.getId() + ", originalFilename=" + this.getOriginalFilename() + ", fileType=" + this.getFileType() + ", size=" + this.getSize() + ", filePath=" + this.getFilePath() + ", fileUrl=" + this.getFileUrl() + ", entityType=" + this.getEntityType() + ", entityId=" + this.getEntityId() + ", isPrimary=" + this.isPrimary() + ", sortOrder=" + this.getSortOrder() + ", uploadedAt=" + String.valueOf(this.getUploadedAt()) + ", uploadedBy=" + this.getUploadedBy() + ")";
    }
}
