package com.foodapp.deliveryexecutive.user.entity;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.order.entity.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends Actor {
    private String name;
    private String mobile;
    private String password;
    private String email;
    private String address;
    private Double latitude;
    private Double longitude;

    @OneToMany
    private List<Order> orders;
}
