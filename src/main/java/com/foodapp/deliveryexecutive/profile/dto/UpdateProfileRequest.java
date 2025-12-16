package com.foodapp.deliveryexecutive.profile.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class UpdateProfileRequest {
    private String name;
    @Email(message="Invalid email format")
    private @Email(message="Invalid email format") String email;
    private String vehicleType;
    @Pattern(regexp="^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$", message="Invalid vehicle number format")
    private @Pattern(regexp="^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$", message="Invalid vehicle number format") String vehicleNumber;
    private String profilePicture;
    private String address;
    private String city;
    private String state;
    @Pattern(regexp="^[0-9]{6}$", message="Invalid pincode")
    private @Pattern(regexp="^[0-9]{6}$", message="Invalid pincode") String pincode;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return this.vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getProfilePicture() {
        return this.profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return this.pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
