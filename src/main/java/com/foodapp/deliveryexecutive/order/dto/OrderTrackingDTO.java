/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.order.dto;

import java.time.LocalDateTime;
import lombok.Generated;

public class OrderTrackingDTO {
    private Long orderId;
    private String status;
    private String statusMessage;
    private Integer stepNumber;
    private Integer totalSteps;
    private Double executiveLatitude;
    private Double executiveLongitude;
    private Double pickupLatitude;
    private Double pickupLongitude;
    private Double deliveryLatitude;
    private Double deliveryLongitude;
    private Long executiveId;
    private String executiveName;
    private String executivePhone;
    private String executivePhoto;
    private Integer estimatedPrepTime;
    private Integer estimatedDeliveryTime;
    private Integer remainingMinutes;
    private LocalDateTime createdAt;
    private LocalDateTime acceptedAt;
    private LocalDateTime pickedUpAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime lastUpdated;
    private String homemakerName;
    private Double amount;
    private String paymentMethod;
    private String customerOtp;

    @Generated
    public Long getOrderId() {
        return this.orderId;
    }

    @Generated
    public String getStatus() {
        return this.status;
    }

    @Generated
    public String getStatusMessage() {
        return this.statusMessage;
    }

    @Generated
    public Integer getStepNumber() {
        return this.stepNumber;
    }

    @Generated
    public Integer getTotalSteps() {
        return this.totalSteps;
    }

    @Generated
    public Double getExecutiveLatitude() {
        return this.executiveLatitude;
    }

    @Generated
    public Double getExecutiveLongitude() {
        return this.executiveLongitude;
    }

    @Generated
    public Double getPickupLatitude() {
        return this.pickupLatitude;
    }

    @Generated
    public Double getPickupLongitude() {
        return this.pickupLongitude;
    }

    @Generated
    public Double getDeliveryLatitude() {
        return this.deliveryLatitude;
    }

    @Generated
    public Double getDeliveryLongitude() {
        return this.deliveryLongitude;
    }

    @Generated
    public Long getExecutiveId() {
        return this.executiveId;
    }

    @Generated
    public String getExecutiveName() {
        return this.executiveName;
    }

    @Generated
    public String getExecutivePhone() {
        return this.executivePhone;
    }

    @Generated
    public String getExecutivePhoto() {
        return this.executivePhoto;
    }

    @Generated
    public Integer getEstimatedPrepTime() {
        return this.estimatedPrepTime;
    }

    @Generated
    public Integer getEstimatedDeliveryTime() {
        return this.estimatedDeliveryTime;
    }

    @Generated
    public Integer getRemainingMinutes() {
        return this.remainingMinutes;
    }

    @Generated
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public LocalDateTime getAcceptedAt() {
        return this.acceptedAt;
    }

    @Generated
    public LocalDateTime getPickedUpAt() {
        return this.pickedUpAt;
    }

    @Generated
    public LocalDateTime getDeliveredAt() {
        return this.deliveredAt;
    }

    @Generated
    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    @Generated
    public String getHomemakerName() {
        return this.homemakerName;
    }

    @Generated
    public Double getAmount() {
        return this.amount;
    }

    @Generated
    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    @Generated
    public String getCustomerOtp() {
        return this.customerOtp;
    }

    @Generated
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Generated
    public void setStatus(String status) {
        this.status = status;
    }

    @Generated
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Generated
    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    @Generated
    public void setTotalSteps(Integer totalSteps) {
        this.totalSteps = totalSteps;
    }

    @Generated
    public void setExecutiveLatitude(Double executiveLatitude) {
        this.executiveLatitude = executiveLatitude;
    }

    @Generated
    public void setExecutiveLongitude(Double executiveLongitude) {
        this.executiveLongitude = executiveLongitude;
    }

    @Generated
    public void setPickupLatitude(Double pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    @Generated
    public void setPickupLongitude(Double pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    @Generated
    public void setDeliveryLatitude(Double deliveryLatitude) {
        this.deliveryLatitude = deliveryLatitude;
    }

    @Generated
    public void setDeliveryLongitude(Double deliveryLongitude) {
        this.deliveryLongitude = deliveryLongitude;
    }

    @Generated
    public void setExecutiveId(Long executiveId) {
        this.executiveId = executiveId;
    }

    @Generated
    public void setExecutiveName(String executiveName) {
        this.executiveName = executiveName;
    }

    @Generated
    public void setExecutivePhone(String executivePhone) {
        this.executivePhone = executivePhone;
    }

    @Generated
    public void setExecutivePhoto(String executivePhoto) {
        this.executivePhoto = executivePhoto;
    }

    @Generated
    public void setEstimatedPrepTime(Integer estimatedPrepTime) {
        this.estimatedPrepTime = estimatedPrepTime;
    }

    @Generated
    public void setEstimatedDeliveryTime(Integer estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    @Generated
    public void setRemainingMinutes(Integer remainingMinutes) {
        this.remainingMinutes = remainingMinutes;
    }

    @Generated
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public void setAcceptedAt(LocalDateTime acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    @Generated
    public void setPickedUpAt(LocalDateTime pickedUpAt) {
        this.pickedUpAt = pickedUpAt;
    }

    @Generated
    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    @Generated
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Generated
    public void setHomemakerName(String homemakerName) {
        this.homemakerName = homemakerName;
    }

    @Generated
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Generated
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Generated
    public void setCustomerOtp(String customerOtp) {
        this.customerOtp = customerOtp;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OrderTrackingDTO)) {
            return false;
        }
        OrderTrackingDTO other = (OrderTrackingDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$orderId = this.getOrderId();
        Long other$orderId = other.getOrderId();
        if (this$orderId == null ? other$orderId != null : !((Object)this$orderId).equals(other$orderId)) {
            return false;
        }
        Integer this$stepNumber = this.getStepNumber();
        Integer other$stepNumber = other.getStepNumber();
        if (this$stepNumber == null ? other$stepNumber != null : !((Object)this$stepNumber).equals(other$stepNumber)) {
            return false;
        }
        Integer this$totalSteps = this.getTotalSteps();
        Integer other$totalSteps = other.getTotalSteps();
        if (this$totalSteps == null ? other$totalSteps != null : !((Object)this$totalSteps).equals(other$totalSteps)) {
            return false;
        }
        Double this$executiveLatitude = this.getExecutiveLatitude();
        Double other$executiveLatitude = other.getExecutiveLatitude();
        if (this$executiveLatitude == null ? other$executiveLatitude != null : !((Object)this$executiveLatitude).equals(other$executiveLatitude)) {
            return false;
        }
        Double this$executiveLongitude = this.getExecutiveLongitude();
        Double other$executiveLongitude = other.getExecutiveLongitude();
        if (this$executiveLongitude == null ? other$executiveLongitude != null : !((Object)this$executiveLongitude).equals(other$executiveLongitude)) {
            return false;
        }
        Double this$pickupLatitude = this.getPickupLatitude();
        Double other$pickupLatitude = other.getPickupLatitude();
        if (this$pickupLatitude == null ? other$pickupLatitude != null : !((Object)this$pickupLatitude).equals(other$pickupLatitude)) {
            return false;
        }
        Double this$pickupLongitude = this.getPickupLongitude();
        Double other$pickupLongitude = other.getPickupLongitude();
        if (this$pickupLongitude == null ? other$pickupLongitude != null : !((Object)this$pickupLongitude).equals(other$pickupLongitude)) {
            return false;
        }
        Double this$deliveryLatitude = this.getDeliveryLatitude();
        Double other$deliveryLatitude = other.getDeliveryLatitude();
        if (this$deliveryLatitude == null ? other$deliveryLatitude != null : !((Object)this$deliveryLatitude).equals(other$deliveryLatitude)) {
            return false;
        }
        Double this$deliveryLongitude = this.getDeliveryLongitude();
        Double other$deliveryLongitude = other.getDeliveryLongitude();
        if (this$deliveryLongitude == null ? other$deliveryLongitude != null : !((Object)this$deliveryLongitude).equals(other$deliveryLongitude)) {
            return false;
        }
        Long this$executiveId = this.getExecutiveId();
        Long other$executiveId = other.getExecutiveId();
        if (this$executiveId == null ? other$executiveId != null : !((Object)this$executiveId).equals(other$executiveId)) {
            return false;
        }
        Integer this$estimatedPrepTime = this.getEstimatedPrepTime();
        Integer other$estimatedPrepTime = other.getEstimatedPrepTime();
        if (this$estimatedPrepTime == null ? other$estimatedPrepTime != null : !((Object)this$estimatedPrepTime).equals(other$estimatedPrepTime)) {
            return false;
        }
        Integer this$estimatedDeliveryTime = this.getEstimatedDeliveryTime();
        Integer other$estimatedDeliveryTime = other.getEstimatedDeliveryTime();
        if (this$estimatedDeliveryTime == null ? other$estimatedDeliveryTime != null : !((Object)this$estimatedDeliveryTime).equals(other$estimatedDeliveryTime)) {
            return false;
        }
        Integer this$remainingMinutes = this.getRemainingMinutes();
        Integer other$remainingMinutes = other.getRemainingMinutes();
        if (this$remainingMinutes == null ? other$remainingMinutes != null : !((Object)this$remainingMinutes).equals(other$remainingMinutes)) {
            return false;
        }
        Double this$amount = this.getAmount();
        Double other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !((Object)this$amount).equals(other$amount)) {
            return false;
        }
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) {
            return false;
        }
        String this$statusMessage = this.getStatusMessage();
        String other$statusMessage = other.getStatusMessage();
        if (this$statusMessage == null ? other$statusMessage != null : !this$statusMessage.equals(other$statusMessage)) {
            return false;
        }
        String this$executiveName = this.getExecutiveName();
        String other$executiveName = other.getExecutiveName();
        if (this$executiveName == null ? other$executiveName != null : !this$executiveName.equals(other$executiveName)) {
            return false;
        }
        String this$executivePhone = this.getExecutivePhone();
        String other$executivePhone = other.getExecutivePhone();
        if (this$executivePhone == null ? other$executivePhone != null : !this$executivePhone.equals(other$executivePhone)) {
            return false;
        }
        String this$executivePhoto = this.getExecutivePhoto();
        String other$executivePhoto = other.getExecutivePhoto();
        if (this$executivePhoto == null ? other$executivePhoto != null : !this$executivePhoto.equals(other$executivePhoto)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !((Object)this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$acceptedAt = this.getAcceptedAt();
        LocalDateTime other$acceptedAt = other.getAcceptedAt();
        if (this$acceptedAt == null ? other$acceptedAt != null : !((Object)this$acceptedAt).equals(other$acceptedAt)) {
            return false;
        }
        LocalDateTime this$pickedUpAt = this.getPickedUpAt();
        LocalDateTime other$pickedUpAt = other.getPickedUpAt();
        if (this$pickedUpAt == null ? other$pickedUpAt != null : !((Object)this$pickedUpAt).equals(other$pickedUpAt)) {
            return false;
        }
        LocalDateTime this$deliveredAt = this.getDeliveredAt();
        LocalDateTime other$deliveredAt = other.getDeliveredAt();
        if (this$deliveredAt == null ? other$deliveredAt != null : !((Object)this$deliveredAt).equals(other$deliveredAt)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        String this$homemakerName = this.getHomemakerName();
        String other$homemakerName = other.getHomemakerName();
        if (this$homemakerName == null ? other$homemakerName != null : !this$homemakerName.equals(other$homemakerName)) {
            return false;
        }
        String this$paymentMethod = this.getPaymentMethod();
        String other$paymentMethod = other.getPaymentMethod();
        if (this$paymentMethod == null ? other$paymentMethod != null : !this$paymentMethod.equals(other$paymentMethod)) {
            return false;
        }
        String this$customerOtp = this.getCustomerOtp();
        String other$customerOtp = other.getCustomerOtp();
        return !(this$customerOtp == null ? other$customerOtp != null : !this$customerOtp.equals(other$customerOtp));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof OrderTrackingDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $orderId = this.getOrderId();
        result = result * 59 + ($orderId == null ? 43 : ((Object)$orderId).hashCode());
        Integer $stepNumber = this.getStepNumber();
        result = result * 59 + ($stepNumber == null ? 43 : ((Object)$stepNumber).hashCode());
        Integer $totalSteps = this.getTotalSteps();
        result = result * 59 + ($totalSteps == null ? 43 : ((Object)$totalSteps).hashCode());
        Double $executiveLatitude = this.getExecutiveLatitude();
        result = result * 59 + ($executiveLatitude == null ? 43 : ((Object)$executiveLatitude).hashCode());
        Double $executiveLongitude = this.getExecutiveLongitude();
        result = result * 59 + ($executiveLongitude == null ? 43 : ((Object)$executiveLongitude).hashCode());
        Double $pickupLatitude = this.getPickupLatitude();
        result = result * 59 + ($pickupLatitude == null ? 43 : ((Object)$pickupLatitude).hashCode());
        Double $pickupLongitude = this.getPickupLongitude();
        result = result * 59 + ($pickupLongitude == null ? 43 : ((Object)$pickupLongitude).hashCode());
        Double $deliveryLatitude = this.getDeliveryLatitude();
        result = result * 59 + ($deliveryLatitude == null ? 43 : ((Object)$deliveryLatitude).hashCode());
        Double $deliveryLongitude = this.getDeliveryLongitude();
        result = result * 59 + ($deliveryLongitude == null ? 43 : ((Object)$deliveryLongitude).hashCode());
        Long $executiveId = this.getExecutiveId();
        result = result * 59 + ($executiveId == null ? 43 : ((Object)$executiveId).hashCode());
        Integer $estimatedPrepTime = this.getEstimatedPrepTime();
        result = result * 59 + ($estimatedPrepTime == null ? 43 : ((Object)$estimatedPrepTime).hashCode());
        Integer $estimatedDeliveryTime = this.getEstimatedDeliveryTime();
        result = result * 59 + ($estimatedDeliveryTime == null ? 43 : ((Object)$estimatedDeliveryTime).hashCode());
        Integer $remainingMinutes = this.getRemainingMinutes();
        result = result * 59 + ($remainingMinutes == null ? 43 : ((Object)$remainingMinutes).hashCode());
        Double $amount = this.getAmount();
        result = result * 59 + ($amount == null ? 43 : ((Object)$amount).hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        String $statusMessage = this.getStatusMessage();
        result = result * 59 + ($statusMessage == null ? 43 : $statusMessage.hashCode());
        String $executiveName = this.getExecutiveName();
        result = result * 59 + ($executiveName == null ? 43 : $executiveName.hashCode());
        String $executivePhone = this.getExecutivePhone();
        result = result * 59 + ($executivePhone == null ? 43 : $executivePhone.hashCode());
        String $executivePhoto = this.getExecutivePhoto();
        result = result * 59 + ($executivePhoto == null ? 43 : $executivePhoto.hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $acceptedAt = this.getAcceptedAt();
        result = result * 59 + ($acceptedAt == null ? 43 : ((Object)$acceptedAt).hashCode());
        LocalDateTime $pickedUpAt = this.getPickedUpAt();
        result = result * 59 + ($pickedUpAt == null ? 43 : ((Object)$pickedUpAt).hashCode());
        LocalDateTime $deliveredAt = this.getDeliveredAt();
        result = result * 59 + ($deliveredAt == null ? 43 : ((Object)$deliveredAt).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        String $homemakerName = this.getHomemakerName();
        result = result * 59 + ($homemakerName == null ? 43 : $homemakerName.hashCode());
        String $paymentMethod = this.getPaymentMethod();
        result = result * 59 + ($paymentMethod == null ? 43 : $paymentMethod.hashCode());
        String $customerOtp = this.getCustomerOtp();
        result = result * 59 + ($customerOtp == null ? 43 : $customerOtp.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "OrderTrackingDTO(orderId=" + this.getOrderId() + ", status=" + this.getStatus() + ", statusMessage=" + this.getStatusMessage() + ", stepNumber=" + this.getStepNumber() + ", totalSteps=" + this.getTotalSteps() + ", executiveLatitude=" + this.getExecutiveLatitude() + ", executiveLongitude=" + this.getExecutiveLongitude() + ", pickupLatitude=" + this.getPickupLatitude() + ", pickupLongitude=" + this.getPickupLongitude() + ", deliveryLatitude=" + this.getDeliveryLatitude() + ", deliveryLongitude=" + this.getDeliveryLongitude() + ", executiveId=" + this.getExecutiveId() + ", executiveName=" + this.getExecutiveName() + ", executivePhone=" + this.getExecutivePhone() + ", executivePhoto=" + this.getExecutivePhoto() + ", estimatedPrepTime=" + this.getEstimatedPrepTime() + ", estimatedDeliveryTime=" + this.getEstimatedDeliveryTime() + ", remainingMinutes=" + this.getRemainingMinutes() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", acceptedAt=" + String.valueOf(this.getAcceptedAt()) + ", pickedUpAt=" + String.valueOf(this.getPickedUpAt()) + ", deliveredAt=" + String.valueOf(this.getDeliveredAt()) + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", homemakerName=" + this.getHomemakerName() + ", amount=" + this.getAmount() + ", paymentMethod=" + this.getPaymentMethod() + ", customerOtp=" + this.getCustomerOtp() + ")";
    }

    @Generated
    public OrderTrackingDTO() {
    }

    @Generated
    public OrderTrackingDTO(Long orderId, String status, String statusMessage, Integer stepNumber, Integer totalSteps, Double executiveLatitude, Double executiveLongitude, Double pickupLatitude, Double pickupLongitude, Double deliveryLatitude, Double deliveryLongitude, Long executiveId, String executiveName, String executivePhone, String executivePhoto, Integer estimatedPrepTime, Integer estimatedDeliveryTime, Integer remainingMinutes, LocalDateTime createdAt, LocalDateTime acceptedAt, LocalDateTime pickedUpAt, LocalDateTime deliveredAt, LocalDateTime lastUpdated, String homemakerName, Double amount, String paymentMethod, String customerOtp) {
        this.orderId = orderId;
        this.status = status;
        this.statusMessage = statusMessage;
        this.stepNumber = stepNumber;
        this.totalSteps = totalSteps;
        this.executiveLatitude = executiveLatitude;
        this.executiveLongitude = executiveLongitude;
        this.pickupLatitude = pickupLatitude;
        this.pickupLongitude = pickupLongitude;
        this.deliveryLatitude = deliveryLatitude;
        this.deliveryLongitude = deliveryLongitude;
        this.executiveId = executiveId;
        this.executiveName = executiveName;
        this.executivePhone = executivePhone;
        this.executivePhoto = executivePhoto;
        this.estimatedPrepTime = estimatedPrepTime;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
        this.remainingMinutes = remainingMinutes;
        this.createdAt = createdAt;
        this.acceptedAt = acceptedAt;
        this.pickedUpAt = pickedUpAt;
        this.deliveredAt = deliveredAt;
        this.lastUpdated = lastUpdated;
        this.homemakerName = homemakerName;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.customerOtp = customerOtp;
    }
}
