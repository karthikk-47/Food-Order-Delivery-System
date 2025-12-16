package com.foodapp.deliveryexecutive.homemaker.entity;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.order.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.List;

@Entity
@Table(name = "homemakers")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class HomeMaker extends Actor {
    private String name;
    private String mobile;
    private String password;
    private String address;
    
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;
    
    private String rejectionReason;
    private Point location;
    
    // Profile image URL stored from FTP
    @Column(name = "profile_image_url")
    private String profileImageUrl;
    
    // Kitchen/cooking area image URL
    @Column(name = "kitchen_image_url")
    private String kitchenImageUrl;
    
    // Description about the homemaker
    @Column(columnDefinition = "TEXT")
    private String description;
    
    // Cuisine specialties
    private String cuisineTypes;
    
    // Rating
    private Double rating;
    
    @OneToMany
    private List<Order> orders;

    public enum ApprovalStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
}
