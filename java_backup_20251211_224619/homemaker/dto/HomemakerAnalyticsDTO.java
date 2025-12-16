/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.foodapp.deliveryexecutive.homemaker.dto;

import java.time.LocalDateTime;
import lombok.Generated;

public class HomemakerAnalyticsDTO {
    private Long id;
    private Long homemakerId;
    private Integer totalOrdersThisMonth;
    private Integer totalOrdersThisWeek;
    private Integer totalOrdersToday;
    private Double totalRevenueThisMonth;
    private Double totalRevenueThisWeek;
    private Double totalRevenueToday;
    private Double averageOrderValue;
    private Double averageRating;
    private Integer totalCustomers;
    private Integer repeatCustomers;
    private Integer ordersCompleted;
    private Integer ordersCancelled;
    private Integer ordersRefunded;
    private Double completionRate;
    private Double cancellationRate;
    private Integer dishesListed;
    private Integer activeDishes;
    private Integer totalReviews;
    private Integer positiveReviews;
    private Integer negativeReviews;
    private LocalDateTime lastUpdated;
    private LocalDateTime createdAt;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Long getHomemakerId() {
        return this.homemakerId;
    }

    @Generated
    public Integer getTotalOrdersThisMonth() {
        return this.totalOrdersThisMonth;
    }

    @Generated
    public Integer getTotalOrdersThisWeek() {
        return this.totalOrdersThisWeek;
    }

    @Generated
    public Integer getTotalOrdersToday() {
        return this.totalOrdersToday;
    }

    @Generated
    public Double getTotalRevenueThisMonth() {
        return this.totalRevenueThisMonth;
    }

    @Generated
    public Double getTotalRevenueThisWeek() {
        return this.totalRevenueThisWeek;
    }

    @Generated
    public Double getTotalRevenueToday() {
        return this.totalRevenueToday;
    }

    @Generated
    public Double getAverageOrderValue() {
        return this.averageOrderValue;
    }

    @Generated
    public Double getAverageRating() {
        return this.averageRating;
    }

    @Generated
    public Integer getTotalCustomers() {
        return this.totalCustomers;
    }

    @Generated
    public Integer getRepeatCustomers() {
        return this.repeatCustomers;
    }

    @Generated
    public Integer getOrdersCompleted() {
        return this.ordersCompleted;
    }

    @Generated
    public Integer getOrdersCancelled() {
        return this.ordersCancelled;
    }

    @Generated
    public Integer getOrdersRefunded() {
        return this.ordersRefunded;
    }

    @Generated
    public Double getCompletionRate() {
        return this.completionRate;
    }

    @Generated
    public Double getCancellationRate() {
        return this.cancellationRate;
    }

    @Generated
    public Integer getDishesListed() {
        return this.dishesListed;
    }

    @Generated
    public Integer getActiveDishes() {
        return this.activeDishes;
    }

    @Generated
    public Integer getTotalReviews() {
        return this.totalReviews;
    }

    @Generated
    public Integer getPositiveReviews() {
        return this.positiveReviews;
    }

    @Generated
    public Integer getNegativeReviews() {
        return this.negativeReviews;
    }

    @Generated
    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    @Generated
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setHomemakerId(Long homemakerId) {
        this.homemakerId = homemakerId;
    }

    @Generated
    public void setTotalOrdersThisMonth(Integer totalOrdersThisMonth) {
        this.totalOrdersThisMonth = totalOrdersThisMonth;
    }

    @Generated
    public void setTotalOrdersThisWeek(Integer totalOrdersThisWeek) {
        this.totalOrdersThisWeek = totalOrdersThisWeek;
    }

    @Generated
    public void setTotalOrdersToday(Integer totalOrdersToday) {
        this.totalOrdersToday = totalOrdersToday;
    }

    @Generated
    public void setTotalRevenueThisMonth(Double totalRevenueThisMonth) {
        this.totalRevenueThisMonth = totalRevenueThisMonth;
    }

    @Generated
    public void setTotalRevenueThisWeek(Double totalRevenueThisWeek) {
        this.totalRevenueThisWeek = totalRevenueThisWeek;
    }

    @Generated
    public void setTotalRevenueToday(Double totalRevenueToday) {
        this.totalRevenueToday = totalRevenueToday;
    }

    @Generated
    public void setAverageOrderValue(Double averageOrderValue) {
        this.averageOrderValue = averageOrderValue;
    }

    @Generated
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    @Generated
    public void setTotalCustomers(Integer totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    @Generated
    public void setRepeatCustomers(Integer repeatCustomers) {
        this.repeatCustomers = repeatCustomers;
    }

    @Generated
    public void setOrdersCompleted(Integer ordersCompleted) {
        this.ordersCompleted = ordersCompleted;
    }

    @Generated
    public void setOrdersCancelled(Integer ordersCancelled) {
        this.ordersCancelled = ordersCancelled;
    }

    @Generated
    public void setOrdersRefunded(Integer ordersRefunded) {
        this.ordersRefunded = ordersRefunded;
    }

    @Generated
    public void setCompletionRate(Double completionRate) {
        this.completionRate = completionRate;
    }

    @Generated
    public void setCancellationRate(Double cancellationRate) {
        this.cancellationRate = cancellationRate;
    }

    @Generated
    public void setDishesListed(Integer dishesListed) {
        this.dishesListed = dishesListed;
    }

    @Generated
    public void setActiveDishes(Integer activeDishes) {
        this.activeDishes = activeDishes;
    }

    @Generated
    public void setTotalReviews(Integer totalReviews) {
        this.totalReviews = totalReviews;
    }

    @Generated
    public void setPositiveReviews(Integer positiveReviews) {
        this.positiveReviews = positiveReviews;
    }

    @Generated
    public void setNegativeReviews(Integer negativeReviews) {
        this.negativeReviews = negativeReviews;
    }

    @Generated
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Generated
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomemakerAnalyticsDTO)) {
            return false;
        }
        HomemakerAnalyticsDTO other = (HomemakerAnalyticsDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$homemakerId = this.getHomemakerId();
        Long other$homemakerId = other.getHomemakerId();
        if (this$homemakerId == null ? other$homemakerId != null : !((Object)this$homemakerId).equals(other$homemakerId)) {
            return false;
        }
        Integer this$totalOrdersThisMonth = this.getTotalOrdersThisMonth();
        Integer other$totalOrdersThisMonth = other.getTotalOrdersThisMonth();
        if (this$totalOrdersThisMonth == null ? other$totalOrdersThisMonth != null : !((Object)this$totalOrdersThisMonth).equals(other$totalOrdersThisMonth)) {
            return false;
        }
        Integer this$totalOrdersThisWeek = this.getTotalOrdersThisWeek();
        Integer other$totalOrdersThisWeek = other.getTotalOrdersThisWeek();
        if (this$totalOrdersThisWeek == null ? other$totalOrdersThisWeek != null : !((Object)this$totalOrdersThisWeek).equals(other$totalOrdersThisWeek)) {
            return false;
        }
        Integer this$totalOrdersToday = this.getTotalOrdersToday();
        Integer other$totalOrdersToday = other.getTotalOrdersToday();
        if (this$totalOrdersToday == null ? other$totalOrdersToday != null : !((Object)this$totalOrdersToday).equals(other$totalOrdersToday)) {
            return false;
        }
        Double this$totalRevenueThisMonth = this.getTotalRevenueThisMonth();
        Double other$totalRevenueThisMonth = other.getTotalRevenueThisMonth();
        if (this$totalRevenueThisMonth == null ? other$totalRevenueThisMonth != null : !((Object)this$totalRevenueThisMonth).equals(other$totalRevenueThisMonth)) {
            return false;
        }
        Double this$totalRevenueThisWeek = this.getTotalRevenueThisWeek();
        Double other$totalRevenueThisWeek = other.getTotalRevenueThisWeek();
        if (this$totalRevenueThisWeek == null ? other$totalRevenueThisWeek != null : !((Object)this$totalRevenueThisWeek).equals(other$totalRevenueThisWeek)) {
            return false;
        }
        Double this$totalRevenueToday = this.getTotalRevenueToday();
        Double other$totalRevenueToday = other.getTotalRevenueToday();
        if (this$totalRevenueToday == null ? other$totalRevenueToday != null : !((Object)this$totalRevenueToday).equals(other$totalRevenueToday)) {
            return false;
        }
        Double this$averageOrderValue = this.getAverageOrderValue();
        Double other$averageOrderValue = other.getAverageOrderValue();
        if (this$averageOrderValue == null ? other$averageOrderValue != null : !((Object)this$averageOrderValue).equals(other$averageOrderValue)) {
            return false;
        }
        Double this$averageRating = this.getAverageRating();
        Double other$averageRating = other.getAverageRating();
        if (this$averageRating == null ? other$averageRating != null : !((Object)this$averageRating).equals(other$averageRating)) {
            return false;
        }
        Integer this$totalCustomers = this.getTotalCustomers();
        Integer other$totalCustomers = other.getTotalCustomers();
        if (this$totalCustomers == null ? other$totalCustomers != null : !((Object)this$totalCustomers).equals(other$totalCustomers)) {
            return false;
        }
        Integer this$repeatCustomers = this.getRepeatCustomers();
        Integer other$repeatCustomers = other.getRepeatCustomers();
        if (this$repeatCustomers == null ? other$repeatCustomers != null : !((Object)this$repeatCustomers).equals(other$repeatCustomers)) {
            return false;
        }
        Integer this$ordersCompleted = this.getOrdersCompleted();
        Integer other$ordersCompleted = other.getOrdersCompleted();
        if (this$ordersCompleted == null ? other$ordersCompleted != null : !((Object)this$ordersCompleted).equals(other$ordersCompleted)) {
            return false;
        }
        Integer this$ordersCancelled = this.getOrdersCancelled();
        Integer other$ordersCancelled = other.getOrdersCancelled();
        if (this$ordersCancelled == null ? other$ordersCancelled != null : !((Object)this$ordersCancelled).equals(other$ordersCancelled)) {
            return false;
        }
        Integer this$ordersRefunded = this.getOrdersRefunded();
        Integer other$ordersRefunded = other.getOrdersRefunded();
        if (this$ordersRefunded == null ? other$ordersRefunded != null : !((Object)this$ordersRefunded).equals(other$ordersRefunded)) {
            return false;
        }
        Double this$completionRate = this.getCompletionRate();
        Double other$completionRate = other.getCompletionRate();
        if (this$completionRate == null ? other$completionRate != null : !((Object)this$completionRate).equals(other$completionRate)) {
            return false;
        }
        Double this$cancellationRate = this.getCancellationRate();
        Double other$cancellationRate = other.getCancellationRate();
        if (this$cancellationRate == null ? other$cancellationRate != null : !((Object)this$cancellationRate).equals(other$cancellationRate)) {
            return false;
        }
        Integer this$dishesListed = this.getDishesListed();
        Integer other$dishesListed = other.getDishesListed();
        if (this$dishesListed == null ? other$dishesListed != null : !((Object)this$dishesListed).equals(other$dishesListed)) {
            return false;
        }
        Integer this$activeDishes = this.getActiveDishes();
        Integer other$activeDishes = other.getActiveDishes();
        if (this$activeDishes == null ? other$activeDishes != null : !((Object)this$activeDishes).equals(other$activeDishes)) {
            return false;
        }
        Integer this$totalReviews = this.getTotalReviews();
        Integer other$totalReviews = other.getTotalReviews();
        if (this$totalReviews == null ? other$totalReviews != null : !((Object)this$totalReviews).equals(other$totalReviews)) {
            return false;
        }
        Integer this$positiveReviews = this.getPositiveReviews();
        Integer other$positiveReviews = other.getPositiveReviews();
        if (this$positiveReviews == null ? other$positiveReviews != null : !((Object)this$positiveReviews).equals(other$positiveReviews)) {
            return false;
        }
        Integer this$negativeReviews = this.getNegativeReviews();
        Integer other$negativeReviews = other.getNegativeReviews();
        if (this$negativeReviews == null ? other$negativeReviews != null : !((Object)this$negativeReviews).equals(other$negativeReviews)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        return !(this$createdAt == null ? other$createdAt != null : !((Object)this$createdAt).equals(other$createdAt));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof HomemakerAnalyticsDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $homemakerId = this.getHomemakerId();
        result = result * 59 + ($homemakerId == null ? 43 : ((Object)$homemakerId).hashCode());
        Integer $totalOrdersThisMonth = this.getTotalOrdersThisMonth();
        result = result * 59 + ($totalOrdersThisMonth == null ? 43 : ((Object)$totalOrdersThisMonth).hashCode());
        Integer $totalOrdersThisWeek = this.getTotalOrdersThisWeek();
        result = result * 59 + ($totalOrdersThisWeek == null ? 43 : ((Object)$totalOrdersThisWeek).hashCode());
        Integer $totalOrdersToday = this.getTotalOrdersToday();
        result = result * 59 + ($totalOrdersToday == null ? 43 : ((Object)$totalOrdersToday).hashCode());
        Double $totalRevenueThisMonth = this.getTotalRevenueThisMonth();
        result = result * 59 + ($totalRevenueThisMonth == null ? 43 : ((Object)$totalRevenueThisMonth).hashCode());
        Double $totalRevenueThisWeek = this.getTotalRevenueThisWeek();
        result = result * 59 + ($totalRevenueThisWeek == null ? 43 : ((Object)$totalRevenueThisWeek).hashCode());
        Double $totalRevenueToday = this.getTotalRevenueToday();
        result = result * 59 + ($totalRevenueToday == null ? 43 : ((Object)$totalRevenueToday).hashCode());
        Double $averageOrderValue = this.getAverageOrderValue();
        result = result * 59 + ($averageOrderValue == null ? 43 : ((Object)$averageOrderValue).hashCode());
        Double $averageRating = this.getAverageRating();
        result = result * 59 + ($averageRating == null ? 43 : ((Object)$averageRating).hashCode());
        Integer $totalCustomers = this.getTotalCustomers();
        result = result * 59 + ($totalCustomers == null ? 43 : ((Object)$totalCustomers).hashCode());
        Integer $repeatCustomers = this.getRepeatCustomers();
        result = result * 59 + ($repeatCustomers == null ? 43 : ((Object)$repeatCustomers).hashCode());
        Integer $ordersCompleted = this.getOrdersCompleted();
        result = result * 59 + ($ordersCompleted == null ? 43 : ((Object)$ordersCompleted).hashCode());
        Integer $ordersCancelled = this.getOrdersCancelled();
        result = result * 59 + ($ordersCancelled == null ? 43 : ((Object)$ordersCancelled).hashCode());
        Integer $ordersRefunded = this.getOrdersRefunded();
        result = result * 59 + ($ordersRefunded == null ? 43 : ((Object)$ordersRefunded).hashCode());
        Double $completionRate = this.getCompletionRate();
        result = result * 59 + ($completionRate == null ? 43 : ((Object)$completionRate).hashCode());
        Double $cancellationRate = this.getCancellationRate();
        result = result * 59 + ($cancellationRate == null ? 43 : ((Object)$cancellationRate).hashCode());
        Integer $dishesListed = this.getDishesListed();
        result = result * 59 + ($dishesListed == null ? 43 : ((Object)$dishesListed).hashCode());
        Integer $activeDishes = this.getActiveDishes();
        result = result * 59 + ($activeDishes == null ? 43 : ((Object)$activeDishes).hashCode());
        Integer $totalReviews = this.getTotalReviews();
        result = result * 59 + ($totalReviews == null ? 43 : ((Object)$totalReviews).hashCode());
        Integer $positiveReviews = this.getPositiveReviews();
        result = result * 59 + ($positiveReviews == null ? 43 : ((Object)$positiveReviews).hashCode());
        Integer $negativeReviews = this.getNegativeReviews();
        result = result * 59 + ($negativeReviews == null ? 43 : ((Object)$negativeReviews).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "HomemakerAnalyticsDTO(id=" + this.getId() + ", homemakerId=" + this.getHomemakerId() + ", totalOrdersThisMonth=" + this.getTotalOrdersThisMonth() + ", totalOrdersThisWeek=" + this.getTotalOrdersThisWeek() + ", totalOrdersToday=" + this.getTotalOrdersToday() + ", totalRevenueThisMonth=" + this.getTotalRevenueThisMonth() + ", totalRevenueThisWeek=" + this.getTotalRevenueThisWeek() + ", totalRevenueToday=" + this.getTotalRevenueToday() + ", averageOrderValue=" + this.getAverageOrderValue() + ", averageRating=" + this.getAverageRating() + ", totalCustomers=" + this.getTotalCustomers() + ", repeatCustomers=" + this.getRepeatCustomers() + ", ordersCompleted=" + this.getOrdersCompleted() + ", ordersCancelled=" + this.getOrdersCancelled() + ", ordersRefunded=" + this.getOrdersRefunded() + ", completionRate=" + this.getCompletionRate() + ", cancellationRate=" + this.getCancellationRate() + ", dishesListed=" + this.getDishesListed() + ", activeDishes=" + this.getActiveDishes() + ", totalReviews=" + this.getTotalReviews() + ", positiveReviews=" + this.getPositiveReviews() + ", negativeReviews=" + this.getNegativeReviews() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ")";
    }

    @Generated
    public HomemakerAnalyticsDTO() {
    }

    @Generated
    public HomemakerAnalyticsDTO(Long id, Long homemakerId, Integer totalOrdersThisMonth, Integer totalOrdersThisWeek, Integer totalOrdersToday, Double totalRevenueThisMonth, Double totalRevenueThisWeek, Double totalRevenueToday, Double averageOrderValue, Double averageRating, Integer totalCustomers, Integer repeatCustomers, Integer ordersCompleted, Integer ordersCancelled, Integer ordersRefunded, Double completionRate, Double cancellationRate, Integer dishesListed, Integer activeDishes, Integer totalReviews, Integer positiveReviews, Integer negativeReviews, LocalDateTime lastUpdated, LocalDateTime createdAt) {
        this.id = id;
        this.homemakerId = homemakerId;
        this.totalOrdersThisMonth = totalOrdersThisMonth;
        this.totalOrdersThisWeek = totalOrdersThisWeek;
        this.totalOrdersToday = totalOrdersToday;
        this.totalRevenueThisMonth = totalRevenueThisMonth;
        this.totalRevenueThisWeek = totalRevenueThisWeek;
        this.totalRevenueToday = totalRevenueToday;
        this.averageOrderValue = averageOrderValue;
        this.averageRating = averageRating;
        this.totalCustomers = totalCustomers;
        this.repeatCustomers = repeatCustomers;
        this.ordersCompleted = ordersCompleted;
        this.ordersCancelled = ordersCancelled;
        this.ordersRefunded = ordersRefunded;
        this.completionRate = completionRate;
        this.cancellationRate = cancellationRate;
        this.dishesListed = dishesListed;
        this.activeDishes = activeDishes;
        this.totalReviews = totalReviews;
        this.positiveReviews = positiveReviews;
        this.negativeReviews = negativeReviews;
        this.lastUpdated = lastUpdated;
        this.createdAt = createdAt;
    }
}
