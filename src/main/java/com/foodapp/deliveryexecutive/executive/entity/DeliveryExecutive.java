package com.foodapp.deliveryexecutive.executive.entity;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.order.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.geo.Point;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "delivery_executives")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryExecutive extends Actor {
    private String name;
    private String mobile;
    private String password;
    private String aadharNo;
    private String licenseNo;
    
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;
    
    private String rejectionReason;
    
    @ElementCollection
    @MapKeyColumn(name = "timestamp")
    @Column(name = "location")
    private Map<LocalTime, Point> location;
    
    private boolean online = false;
    
    // Profile image URL stored from FTP
    @Column(name = "profile_image_url")
    private String profileImageUrl;
    
    // Vehicle image URL
    @Column(name = "vehicle_image_url")
    private String vehicleImageUrl;
    
    // Vehicle details
    private String vehicleType;
    private String vehicleNumber;
    
    // Rating
    private Double rating;
    
    @OneToMany
    private List<Order> orders;

    public void addLocation(LocalTime time, Point location) {
        this.location.put(time, location);
    }

    public enum ApprovalStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
}
