/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Entity
 *  jakarta.persistence.OneToMany
 *  lombok.Generated
 *  org.springframework.data.geo.Point
 */
package com.foodapp.deliveryexecutive.user.entity;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.order.entity.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Generated;
import org.springframework.data.geo.Point;

@Entity
public class User
extends Actor {
    private String name;
    private String mobile;
    private String password;
    private String email;
    private String address;
    private Point location;
    @OneToMany
    private List<Order> orders;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Point getLocation() {
        return this.location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getMobile() {
        return this.mobile;
    }

    @Generated
    public String getPassword() {
        return this.password;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public void setName(String name) {
        this.name = name;
    }

    @Generated
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Generated
    public void setPassword(String password) {
        this.password = password;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User other = (User)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$mobile = this.getMobile();
        String other$mobile = other.getMobile();
        if (this$mobile == null ? other$mobile != null : !this$mobile.equals(other$mobile)) {
            return false;
        }
        String this$password = this.getPassword();
        String other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$address = this.getAddress();
        String other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) {
            return false;
        }
        Point this$location = this.getLocation();
        Point other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location)) {
            return false;
        }
        List<Order> this$orders = this.getOrders();
        List<Order> other$orders = other.getOrders();
        return !(this$orders == null ? other$orders != null : !((Object)this$orders).equals(other$orders));
    }

    @Override
    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof User;
    }

    @Override
    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $mobile = this.getMobile();
        result = result * 59 + ($mobile == null ? 43 : $mobile.hashCode());
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $address = this.getAddress();
        result = result * 59 + ($address == null ? 43 : $address.hashCode());
        Point $location = this.getLocation();
        result = result * 59 + ($location == null ? 43 : $location.hashCode());
        List<Order> $orders = this.getOrders();
        result = result * 59 + ($orders == null ? 43 : ((Object)$orders).hashCode());
        return result;
    }

    @Override
    @Generated
    public String toString() {
        return "User(name=" + this.getName() + ", mobile=" + this.getMobile() + ", password=" + this.getPassword() + ", email=" + this.getEmail() + ", address=" + this.getAddress() + ", location=" + String.valueOf(this.getLocation()) + ", orders=" + String.valueOf(this.getOrders()) + ")";
    }

    @Generated
    public User(String name, String mobile, String password, String email, String address, Point location, List<Order> orders) {
        this.name = name;
        this.mobile = mobile;
        this.password = password;
        this.email = email;
        this.address = address;
        this.location = location;
        this.orders = orders;
    }

    @Generated
    public User() {
    }
}
