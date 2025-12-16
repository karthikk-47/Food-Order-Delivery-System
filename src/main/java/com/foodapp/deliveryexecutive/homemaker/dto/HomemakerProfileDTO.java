package com.foodapp.deliveryexecutive.homemaker.dto;

import com.foodapp.deliveryexecutive.homemaker.entity.HomemakerProfile;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Generated;

public class HomemakerProfileDTO {
    private Long id;
    private Long userId;
    private String fullName;
    private String bio;
    private String profileImage;
    private String phoneNumber;
    private String email;
    private HomemakerProfile.AccountStatus accountStatus;
    private Boolean isVerified;
    private LocalDateTime verifiedAt;
    private List<String> specialities;
    private Double averageRating;
    private Integer totalRatings;
    private Integer totalOrders;
    private String cuisinePreference;
    private String businessAddress;
    private String city;
    private String state;
    private String zipCode;
    private String operatingHours;
    private String breakStartTime;
    private String breakEndTime;
    private Integer yearsOfExperience;
    private String certifications;
    private Double totalEarnings;
    private Double walletBalance;
    private String cancellationPolicy;
    private Integer maxCancellationsPerMonth;
    private Integer currentMonthCancellations;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getUserId() {
        return this.userId;
    }

    @Generated
    public String getFullName() {
        return this.fullName;
    }

    @Generated
    public String getBio() {
        return this.bio;
    }

    @Generated
    public String getProfileImage() {
        return this.profileImage;
    }

    @Generated
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public HomemakerProfile.AccountStatus getAccountStatus() {
        return this.accountStatus;
    }

    @Generated
    public Boolean getIsVerified() {
        return this.isVerified;
    }

    @Generated
    public LocalDateTime getVerifiedAt() {
        return this.verifiedAt;
    }

    @Generated
    public List<String> getSpecialities() {
        return this.specialities;
    }

    @Generated
    public Double getAverageRating() {
        return this.averageRating;
    }

    @Generated
    public Integer getTotalRatings() {
        return this.totalRatings;
    }

    @Generated
    public Integer getTotalOrders() {
        return this.totalOrders;
    }

    @Generated
    public String getCuisinePreference() {
        return this.cuisinePreference;
    }

    @Generated
    public String getBusinessAddress() {
        return this.businessAddress;
    }

    @Generated
    public String getCity() {
        return this.city;
    }

    @Generated
    public String getState() {
        return this.state;
    }

    @Generated
    public String getZipCode() {
        return this.zipCode;
    }

    @Generated
    public String getOperatingHours() {
        return this.operatingHours;
    }

    @Generated
    public String getBreakStartTime() {
        return this.breakStartTime;
    }

    @Generated
    public String getBreakEndTime() {
        return this.breakEndTime;
    }

    @Generated
    public Integer getYearsOfExperience() {
        return this.yearsOfExperience;
    }

    @Generated
    public String getCertifications() {
        return this.certifications;
    }

    @Generated
    public Double getTotalEarnings() {
        return this.totalEarnings;
    }

    @Generated
    public Double getWalletBalance() {
        return this.walletBalance;
    }

    @Generated
    public String getCancellationPolicy() {
        return this.cancellationPolicy;
    }

    @Generated
    public Integer getMaxCancellationsPerMonth() {
        return this.maxCancellationsPerMonth;
    }

    @Generated
    public Integer getCurrentMonthCancellations() {
        return this.currentMonthCancellations;
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
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Generated
    public void setBio(String bio) {
        this.bio = bio;
    }

    @Generated
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Generated
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated
    public void setAccountStatus(HomemakerProfile.AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Generated
    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Generated
    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    @Generated
    public void setSpecialities(List<String> specialities) {
        this.specialities = specialities;
    }

    @Generated
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    @Generated
    public void setTotalRatings(Integer totalRatings) {
        this.totalRatings = totalRatings;
    }

    @Generated
    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    @Generated
    public void setCuisinePreference(String cuisinePreference) {
        this.cuisinePreference = cuisinePreference;
    }

    @Generated
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    @Generated
    public void setCity(String city) {
        this.city = city;
    }

    @Generated
    public void setState(String state) {
        this.state = state;
    }

    @Generated
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Generated
    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    @Generated
    public void setBreakStartTime(String breakStartTime) {
        this.breakStartTime = breakStartTime;
    }

    @Generated
    public void setBreakEndTime(String breakEndTime) {
        this.breakEndTime = breakEndTime;
    }

    @Generated
    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Generated
    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    @Generated
    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    @Generated
    public void setWalletBalance(Double walletBalance) {
        this.walletBalance = walletBalance;
    }

    @Generated
    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    @Generated
    public void setMaxCancellationsPerMonth(Integer maxCancellationsPerMonth) {
        this.maxCancellationsPerMonth = maxCancellationsPerMonth;
    }

    @Generated
    public void setCurrentMonthCancellations(Integer currentMonthCancellations) {
        this.currentMonthCancellations = currentMonthCancellations;
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
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomemakerProfileDTO)) {
            return false;
        }
        HomemakerProfileDTO other = (HomemakerProfileDTO)o;
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
        Boolean this$isVerified = this.getIsVerified();
        Boolean other$isVerified = other.getIsVerified();
        if (this$isVerified == null ? other$isVerified != null : !(this$isVerified).equals(other$isVerified)) {
            return false;
        }
        Double this$averageRating = this.getAverageRating();
        Double other$averageRating = other.getAverageRating();
        if (this$averageRating == null ? other$averageRating != null : !(this$averageRating).equals(other$averageRating)) {
            return false;
        }
        Integer this$totalRatings = this.getTotalRatings();
        Integer other$totalRatings = other.getTotalRatings();
        if (this$totalRatings == null ? other$totalRatings != null : !(this$totalRatings).equals(other$totalRatings)) {
            return false;
        }
        Integer this$totalOrders = this.getTotalOrders();
        Integer other$totalOrders = other.getTotalOrders();
        if (this$totalOrders == null ? other$totalOrders != null : !(this$totalOrders).equals(other$totalOrders)) {
            return false;
        }
        Integer this$yearsOfExperience = this.getYearsOfExperience();
        Integer other$yearsOfExperience = other.getYearsOfExperience();
        if (this$yearsOfExperience == null ? other$yearsOfExperience != null : !(this$yearsOfExperience).equals(other$yearsOfExperience)) {
            return false;
        }
        Double this$totalEarnings = this.getTotalEarnings();
        Double other$totalEarnings = other.getTotalEarnings();
        if (this$totalEarnings == null ? other$totalEarnings != null : !(this$totalEarnings).equals(other$totalEarnings)) {
            return false;
        }
        Double this$walletBalance = this.getWalletBalance();
        Double other$walletBalance = other.getWalletBalance();
        if (this$walletBalance == null ? other$walletBalance != null : !(this$walletBalance).equals(other$walletBalance)) {
            return false;
        }
        Integer this$maxCancellationsPerMonth = this.getMaxCancellationsPerMonth();
        Integer other$maxCancellationsPerMonth = other.getMaxCancellationsPerMonth();
        if (this$maxCancellationsPerMonth == null ? other$maxCancellationsPerMonth != null : !(this$maxCancellationsPerMonth).equals(other$maxCancellationsPerMonth)) {
            return false;
        }
        Integer this$currentMonthCancellations = this.getCurrentMonthCancellations();
        Integer other$currentMonthCancellations = other.getCurrentMonthCancellations();
        if (this$currentMonthCancellations == null ? other$currentMonthCancellations != null : !(this$currentMonthCancellations).equals(other$currentMonthCancellations)) {
            return false;
        }
        String this$fullName = this.getFullName();
        String other$fullName = other.getFullName();
        if (this$fullName == null ? other$fullName != null : !this$fullName.equals(other$fullName)) {
            return false;
        }
        String this$bio = this.getBio();
        String other$bio = other.getBio();
        if (this$bio == null ? other$bio != null : !this$bio.equals(other$bio)) {
            return false;
        }
        String this$profileImage = this.getProfileImage();
        String other$profileImage = other.getProfileImage();
        if (this$profileImage == null ? other$profileImage != null : !this$profileImage.equals(other$profileImage)) {
            return false;
        }
        String this$phoneNumber = this.getPhoneNumber();
        String other$phoneNumber = other.getPhoneNumber();
        if (this$phoneNumber == null ? other$phoneNumber != null : !this$phoneNumber.equals(other$phoneNumber)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        HomemakerProfile.AccountStatus this$accountStatus = this.getAccountStatus();
        HomemakerProfile.AccountStatus other$accountStatus = other.getAccountStatus();
        if (this$accountStatus == null ? other$accountStatus != null : !((Object)(this$accountStatus)).equals(other$accountStatus)) {
            return false;
        }
        LocalDateTime this$verifiedAt = this.getVerifiedAt();
        LocalDateTime other$verifiedAt = other.getVerifiedAt();
        if (this$verifiedAt == null ? other$verifiedAt != null : !(this$verifiedAt).equals(other$verifiedAt)) {
            return false;
        }
        List<String> this$specialities = this.getSpecialities();
        List<String> other$specialities = other.getSpecialities();
        if (this$specialities == null ? other$specialities != null : !(this$specialities).equals(other$specialities)) {
            return false;
        }
        String this$cuisinePreference = this.getCuisinePreference();
        String other$cuisinePreference = other.getCuisinePreference();
        if (this$cuisinePreference == null ? other$cuisinePreference != null : !this$cuisinePreference.equals(other$cuisinePreference)) {
            return false;
        }
        String this$businessAddress = this.getBusinessAddress();
        String other$businessAddress = other.getBusinessAddress();
        if (this$businessAddress == null ? other$businessAddress != null : !this$businessAddress.equals(other$businessAddress)) {
            return false;
        }
        String this$city = this.getCity();
        String other$city = other.getCity();
        if (this$city == null ? other$city != null : !this$city.equals(other$city)) {
            return false;
        }
        String this$state = this.getState();
        String other$state = other.getState();
        if (this$state == null ? other$state != null : !this$state.equals(other$state)) {
            return false;
        }
        String this$zipCode = this.getZipCode();
        String other$zipCode = other.getZipCode();
        if (this$zipCode == null ? other$zipCode != null : !this$zipCode.equals(other$zipCode)) {
            return false;
        }
        String this$operatingHours = this.getOperatingHours();
        String other$operatingHours = other.getOperatingHours();
        if (this$operatingHours == null ? other$operatingHours != null : !this$operatingHours.equals(other$operatingHours)) {
            return false;
        }
        String this$breakStartTime = this.getBreakStartTime();
        String other$breakStartTime = other.getBreakStartTime();
        if (this$breakStartTime == null ? other$breakStartTime != null : !this$breakStartTime.equals(other$breakStartTime)) {
            return false;
        }
        String this$breakEndTime = this.getBreakEndTime();
        String other$breakEndTime = other.getBreakEndTime();
        if (this$breakEndTime == null ? other$breakEndTime != null : !this$breakEndTime.equals(other$breakEndTime)) {
            return false;
        }
        String this$certifications = this.getCertifications();
        String other$certifications = other.getCertifications();
        if (this$certifications == null ? other$certifications != null : !this$certifications.equals(other$certifications)) {
            return false;
        }
        String this$cancellationPolicy = this.getCancellationPolicy();
        String other$cancellationPolicy = other.getCancellationPolicy();
        if (this$cancellationPolicy == null ? other$cancellationPolicy != null : !this$cancellationPolicy.equals(other$cancellationPolicy)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !(this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$updatedAt = this.getUpdatedAt();
        LocalDateTime other$updatedAt = other.getUpdatedAt();
        return !(this$updatedAt == null ? other$updatedAt != null : !(this$updatedAt).equals(other$updatedAt));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof HomemakerProfileDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Boolean $isVerified = this.getIsVerified();
        result = result * 59 + ($isVerified == null ? 43 : ((Object)$isVerified).hashCode());
        Double $averageRating = this.getAverageRating();
        result = result * 59 + ($averageRating == null ? 43 : ((Object)$averageRating).hashCode());
        Integer $totalRatings = this.getTotalRatings();
        result = result * 59 + ($totalRatings == null ? 43 : ((Object)$totalRatings).hashCode());
        Integer $totalOrders = this.getTotalOrders();
        result = result * 59 + ($totalOrders == null ? 43 : ((Object)$totalOrders).hashCode());
        Integer $yearsOfExperience = this.getYearsOfExperience();
        result = result * 59 + ($yearsOfExperience == null ? 43 : ((Object)$yearsOfExperience).hashCode());
        Double $totalEarnings = this.getTotalEarnings();
        result = result * 59 + ($totalEarnings == null ? 43 : ((Object)$totalEarnings).hashCode());
        Double $walletBalance = this.getWalletBalance();
        result = result * 59 + ($walletBalance == null ? 43 : ((Object)$walletBalance).hashCode());
        Integer $maxCancellationsPerMonth = this.getMaxCancellationsPerMonth();
        result = result * 59 + ($maxCancellationsPerMonth == null ? 43 : ((Object)$maxCancellationsPerMonth).hashCode());
        Integer $currentMonthCancellations = this.getCurrentMonthCancellations();
        result = result * 59 + ($currentMonthCancellations == null ? 43 : ((Object)$currentMonthCancellations).hashCode());
        String $fullName = this.getFullName();
        result = result * 59 + ($fullName == null ? 43 : $fullName.hashCode());
        String $bio = this.getBio();
        result = result * 59 + ($bio == null ? 43 : $bio.hashCode());
        String $profileImage = this.getProfileImage();
        result = result * 59 + ($profileImage == null ? 43 : $profileImage.hashCode());
        String $phoneNumber = this.getPhoneNumber();
        result = result * 59 + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        HomemakerProfile.AccountStatus $accountStatus = this.getAccountStatus();
        result = result * 59 + ($accountStatus == null ? 43 : ((Object)((Object)$accountStatus)).hashCode());
        LocalDateTime $verifiedAt = this.getVerifiedAt();
        result = result * 59 + ($verifiedAt == null ? 43 : ((Object)$verifiedAt).hashCode());
        List<String> $specialities = this.getSpecialities();
        result = result * 59 + ($specialities == null ? 43 : ((Object)$specialities).hashCode());
        String $cuisinePreference = this.getCuisinePreference();
        result = result * 59 + ($cuisinePreference == null ? 43 : $cuisinePreference.hashCode());
        String $businessAddress = this.getBusinessAddress();
        result = result * 59 + ($businessAddress == null ? 43 : $businessAddress.hashCode());
        String $city = this.getCity();
        result = result * 59 + ($city == null ? 43 : $city.hashCode());
        String $state = this.getState();
        result = result * 59 + ($state == null ? 43 : $state.hashCode());
        String $zipCode = this.getZipCode();
        result = result * 59 + ($zipCode == null ? 43 : $zipCode.hashCode());
        String $operatingHours = this.getOperatingHours();
        result = result * 59 + ($operatingHours == null ? 43 : $operatingHours.hashCode());
        String $breakStartTime = this.getBreakStartTime();
        result = result * 59 + ($breakStartTime == null ? 43 : $breakStartTime.hashCode());
        String $breakEndTime = this.getBreakEndTime();
        result = result * 59 + ($breakEndTime == null ? 43 : $breakEndTime.hashCode());
        String $certifications = this.getCertifications();
        result = result * 59 + ($certifications == null ? 43 : $certifications.hashCode());
        String $cancellationPolicy = this.getCancellationPolicy();
        result = result * 59 + ($cancellationPolicy == null ? 43 : $cancellationPolicy.hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "HomemakerProfileDTO(id=" + this.getId() + ", userId=" + this.getUserId() + ", fullName=" + this.getFullName() + ", bio=" + this.getBio() + ", profileImage=" + this.getProfileImage() + ", phoneNumber=" + this.getPhoneNumber() + ", email=" + this.getEmail() + ", accountStatus=" + String.valueOf(this.getAccountStatus()) + ", isVerified=" + this.getIsVerified() + ", verifiedAt=" + String.valueOf(this.getVerifiedAt()) + ", specialities=" + String.valueOf(this.getSpecialities()) + ", averageRating=" + this.getAverageRating() + ", totalRatings=" + this.getTotalRatings() + ", totalOrders=" + this.getTotalOrders() + ", cuisinePreference=" + this.getCuisinePreference() + ", businessAddress=" + this.getBusinessAddress() + ", city=" + this.getCity() + ", state=" + this.getState() + ", zipCode=" + this.getZipCode() + ", operatingHours=" + this.getOperatingHours() + ", breakStartTime=" + this.getBreakStartTime() + ", breakEndTime=" + this.getBreakEndTime() + ", yearsOfExperience=" + this.getYearsOfExperience() + ", certifications=" + this.getCertifications() + ", totalEarnings=" + this.getTotalEarnings() + ", walletBalance=" + this.getWalletBalance() + ", cancellationPolicy=" + this.getCancellationPolicy() + ", maxCancellationsPerMonth=" + this.getMaxCancellationsPerMonth() + ", currentMonthCancellations=" + this.getCurrentMonthCancellations() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ")";
    }

    @Generated
    public HomemakerProfileDTO() {
    }

    @Generated
    public HomemakerProfileDTO(Long id, Long userId, String fullName, String bio, String profileImage, String phoneNumber, String email, HomemakerProfile.AccountStatus accountStatus, Boolean isVerified, LocalDateTime verifiedAt, List<String> specialities, Double averageRating, Integer totalRatings, Integer totalOrders, String cuisinePreference, String businessAddress, String city, String state, String zipCode, String operatingHours, String breakStartTime, String breakEndTime, Integer yearsOfExperience, String certifications, Double totalEarnings, Double walletBalance, String cancellationPolicy, Integer maxCancellationsPerMonth, Integer currentMonthCancellations, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.bio = bio;
        this.profileImage = profileImage;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accountStatus = accountStatus;
        this.isVerified = isVerified;
        this.verifiedAt = verifiedAt;
        this.specialities = specialities;
        this.averageRating = averageRating;
        this.totalRatings = totalRatings;
        this.totalOrders = totalOrders;
        this.cuisinePreference = cuisinePreference;
        this.businessAddress = businessAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.operatingHours = operatingHours;
        this.breakStartTime = breakStartTime;
        this.breakEndTime = breakEndTime;
        this.yearsOfExperience = yearsOfExperience;
        this.certifications = certifications;
        this.totalEarnings = totalEarnings;
        this.walletBalance = walletBalance;
        this.cancellationPolicy = cancellationPolicy;
        this.maxCancellationsPerMonth = maxCancellationsPerMonth;
        this.currentMonthCancellations = currentMonthCancellations;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
