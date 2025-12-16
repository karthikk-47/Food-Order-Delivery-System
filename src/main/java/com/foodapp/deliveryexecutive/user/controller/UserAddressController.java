/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.user.controller;

import com.foodapp.deliveryexecutive.user.dto.UserAddressDTO;
import com.foodapp.deliveryexecutive.user.service.UserAddressService;
import java.util.List;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/user-address"})
public class UserAddressController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(UserAddressController.class);
    @Autowired
    private UserAddressService userAddressService;

    @PostMapping(value={"/add"})
    public ResponseEntity<UserAddressDTO> addAddress(@RequestBody UserAddressDTO addressDTO) {
        log.info("Adding address for user: {}", addressDTO.getUserId());
        try {
            UserAddressDTO createdAddress = this.userAddressService.addAddress(addressDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
        }
        catch (Exception e) {
            log.error("Error adding address", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{id}"})
    public ResponseEntity<UserAddressDTO> getAddress(@PathVariable Long id) {
        log.info("Fetching address with ID: {}", id);
        try {
            UserAddressDTO address = this.userAddressService.getAddressById(id);
            return ResponseEntity.ok(address);
        }
        catch (Exception e) {
            log.error("Error fetching address", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<UserAddressDTO> updateAddress(@PathVariable Long id, @RequestBody UserAddressDTO addressDTO) {
        log.info("Updating address with ID: {}", id);
        try {
            UserAddressDTO updatedAddress = this.userAddressService.updateAddress(id, addressDTO);
            return ResponseEntity.ok(updatedAddress);
        }
        catch (Exception e) {
            log.error("Error updating address", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{addressId}/set-default"})
    public ResponseEntity<Void> setAsDefault(@PathVariable Long addressId, @RequestParam Long userId) {
        log.info("Setting address {} as default for user: {}", addressId, userId);
        try {
            this.userAddressService.setAsDefault(userId, addressId);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            log.error("Error setting default address", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/user/{userId}/all"})
    public ResponseEntity<List<UserAddressDTO>> getUserAddresses(@PathVariable Long userId) {
        log.info("Fetching all addresses for user: {}", userId);
        try {
            List<UserAddressDTO> addresses = this.userAddressService.getUserAddresses(userId);
            return ResponseEntity.ok(addresses);
        }
        catch (Exception e) {
            log.error("Error fetching user addresses", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value={"/{id}"})
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        log.info("Deleting address with ID: {}", id);
        try {
            this.userAddressService.deleteAddress(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            log.error("Error deleting address", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
