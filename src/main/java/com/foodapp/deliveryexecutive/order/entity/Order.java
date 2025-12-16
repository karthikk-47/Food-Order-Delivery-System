package com.foodapp.deliveryexecutive.order.entity;

import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.springframework.data.geo.Point;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @ManyToOne
    @JoinColumn(name="homemaker_id")
    private HomeMaker homeMaker;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="executiveid")
    private DeliveryExecutive executive;
    @Column(name="pickup_location")
    private Point pickupLocation;
    @Column(name="delivery_location")
    private Point deliveryLocation;
    @Column(name="distance")
    private double distance;
    @Column(name="amount", nullable=false)
    private double amount;
    @Column(name="delivery_fee")
    private double deliveryFee;
    @Column(name="commission_rate")
    private double commissionRate;
    @Column(name="commission_amount")
    private double commissionAmount;
    @Column(name="executive_earning")
    private double executiveEarning;
    @Enumerated(value=EnumType.STRING)
    @Column(name="order_status", nullable=false)
    private OrderStatus orderStatus;
    @Enumerated(value=EnumType.STRING)
    @Column(name="priority")
    private OrderPriority priority;
    @Column(name="customer_otp")
    private String customerOtp;
    @Column(name="payment")
    private double payment;
    @Column(name="payment_method")
    private String paymentMethod;
    @Column(name="is_surge_pricing")
    private boolean isSurgePricing;
    @Column(name="surge_multiplier")
    private double surgeMultiplier;
    @Column(name="estimated_prep_time")
    private Integer estimatedPrepTime;
    @Column(name="estimated_delivery_time")
    private Integer estimatedDeliveryTime;
    @Column(name="order_value_category")
    private String orderValueCategory;
    @Column(name="customer_rating")
    private Double customerRating;
    @Column(name="special_instructions")
    private String specialInstructions;
    @Column(name="created_at", nullable=false, updatable=false)
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @Column(name="accepted_at")
    private LocalDateTime acceptedAt;
    @Column(name="picked_up_at")
    private LocalDateTime pickedUpAt;
    @Column(name="delivered_at")
    private LocalDateTime deliveredAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.priority == null) {
            this.priority = OrderPriority.MEDIUM;
        }
        if (this.commissionRate == 0.0) {
            this.commissionRate = 20.0;
        }
        this.calculateCommissionAndEarnings();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.calculateCommissionAndEarnings();
    }

    private void calculateCommissionAndEarnings() {
        this.commissionAmount = this.deliveryFee * this.commissionRate / 100.0;
        this.executiveEarning = this.deliveryFee - this.commissionAmount;
        if (this.isSurgePricing && this.surgeMultiplier > 1.0) {
            this.executiveEarning *= this.surgeMultiplier;
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HomeMaker getHomeMaker() {
        return this.homeMaker;
    }

    public void setHomeMaker(HomeMaker homeMaker) {
        this.homeMaker = homeMaker;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DeliveryExecutive getExecutive() {
        return this.executive;
    }

    public void setExecutive(DeliveryExecutive executive) {
        this.executive = executive;
    }

    public Point getPickupLocation() {
        return this.pickupLocation;
    }

    public void setPickupLocation(Point pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Point getDeliveryLocation() {
        return this.deliveryLocation;
    }

    public void setDeliveryLocation(Point deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDeliveryFee() {
        return this.deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
        this.calculateCommissionAndEarnings();
    }

    public double getCommissionRate() {
        return this.commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
        this.calculateCommissionAndEarnings();
    }

    public double getCommissionAmount() {
        return this.commissionAmount;
    }

    public void setCommissionAmount(double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public double getExecutiveEarning() {
        return this.executiveEarning;
    }

    public void setExecutiveEarning(double executiveEarning) {
        this.executiveEarning = executiveEarning;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderPriority getPriority() {
        return this.priority;
    }

    public void setPriority(OrderPriority priority) {
        this.priority = priority;
    }

    public String getCustomerOtp() {
        return this.customerOtp;
    }

    public void setCustomerOtp(String customerOtp) {
        this.customerOtp = customerOtp;
    }

    public double getPayment() {
        return this.payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isSurgePricing() {
        return this.isSurgePricing;
    }

    public void setSurgePricing(boolean surgePricing) {
        this.isSurgePricing = surgePricing;
        this.calculateCommissionAndEarnings();
    }

    public double getSurgeMultiplier() {
        return this.surgeMultiplier;
    }

    public void setSurgeMultiplier(double surgeMultiplier) {
        this.surgeMultiplier = surgeMultiplier;
        this.calculateCommissionAndEarnings();
    }

    public Integer getEstimatedPrepTime() {
        return this.estimatedPrepTime;
    }

    public void setEstimatedPrepTime(Integer estimatedPrepTime) {
        this.estimatedPrepTime = estimatedPrepTime;
    }

    public Integer getEstimatedDeliveryTime() {
        return this.estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(Integer estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public String getOrderValueCategory() {
        return this.orderValueCategory;
    }

    public void setOrderValueCategory(String orderValueCategory) {
        this.orderValueCategory = orderValueCategory;
    }

    public Double getCustomerRating() {
        return this.customerRating;
    }

    public void setCustomerRating(Double customerRating) {
        this.customerRating = customerRating;
    }

    public String getSpecialInstructions() {
        return this.specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getAcceptedAt() {
        return this.acceptedAt;
    }

    public void setAcceptedAt(LocalDateTime acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public LocalDateTime getPickedUpAt() {
        return this.pickedUpAt;
    }

    public void setPickedUpAt(LocalDateTime pickedUpAt) {
        this.pickedUpAt = pickedUpAt;
    }

    public LocalDateTime getDeliveredAt() {
        return this.deliveredAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public static enum OrderPriority {
        HIGH,
        MEDIUM,
        LOW;

    }

    public static enum OrderStatus {
        PENDING,
        PREPARING,
        PREPARED,
        ACCEPTED,
        OUTFORDELIVERY,
        DELIVERED,
        CANCELLED;

    }
}
