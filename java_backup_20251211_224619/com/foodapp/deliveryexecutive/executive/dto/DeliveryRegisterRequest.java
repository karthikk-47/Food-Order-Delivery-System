/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.constraints.Email
 *  jakarta.validation.constraints.NotBlank
 *  jakarta.validation.constraints.Pattern
 *  jakarta.validation.constraints.Size
 */
package com.foodapp.deliveryexecutive.executive.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DeliveryRegisterRequest {
    @NotBlank(message="Name is required")
    private @NotBlank(message="Name is required") String name;
    @NotBlank(message="Email is required")
    @Email(message="Invalid email format")
    private @NotBlank(message="Email is required") @Email(message="Invalid email format") String email;
    @NotBlank(message="Mobile number is required")
    @Pattern(regexp="^[0-9]{10}$", message="Mobile number must be 10 digits")
    private @NotBlank(message="Mobile number is required") @Pattern(regexp="^[0-9]{10}$", message="Mobile number must be 10 digits") String mobile;
    @NotBlank(message="Password is required")
    @Size(min=6, message="Password must be at least 6 characters long")
    private @NotBlank(message="Password is required") @Size(min=6, message="Password must be at least 6 characters long") String password;
    @NotBlank(message="Aadhar number is required")
    @Pattern(regexp="^[0-9]{12}$", message="Aadhar number must be 12 digits")
    private @NotBlank(message="Aadhar number is required") @Pattern(regexp="^[0-9]{12}$", message="Aadhar number must be 12 digits") String aadharNumber;
    @NotBlank(message="PAN number is required")
    @Pattern(regexp="[A-Z]{5}[0-9]{4}[A-Z]{1}", message="Invalid PAN number format")
    private @NotBlank(message="PAN number is required") @Pattern(regexp="[A-Z]{5}[0-9]{4}[A-Z]{1}", message="Invalid PAN number format") String panNumber;
    @NotBlank(message="License number is required")
    private @NotBlank(message="License number is required") String licenseNumber;

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

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAadharNumber() {
        return this.aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getPanNumber() {
        return this.panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getLicenseNumber() {
        return this.licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
