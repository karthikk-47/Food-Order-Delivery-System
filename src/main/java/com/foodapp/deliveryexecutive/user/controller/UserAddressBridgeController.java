package com.foodapp.deliveryexecutive.user.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.deliveryexecutive.user.dto.UserAddressDTO;
import com.foodapp.deliveryexecutive.user.service.UserAddressService;


@RestController
@RequestMapping("/api")
public class UserAddressBridgeController {
    private static final Logger log = LoggerFactory.getLogger(UserAddressBridgeController.class);

    @Autowired
    private UserAddressService userAddressService;

    // GET /api/users/{userId}/addresses - Get all addresses for a user
    @GetMapping("/users/{userId}/addresses")
    public ResponseEntity<List<UserAddressDTO>> getUserAddresses(@PathVariable Long userId) {
        log.info("Fetching addresses for user: {}", userId);
        try {
            List<UserAddressDTO> addresses = userAddressService.getUserAddresses(userId);
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            log.error("Error fetching user addresses", e);
            return ResponseEntity.ok(List.of());
        }
    }

    // POST /api/users/{userId}/addresses - Add a new address for a user
    @PostMapping("/users/{userId}/addresses")
    public ResponseEntity<?> addUserAddress(@PathVariable Long userId, @RequestBody UserAddressDTO addressDTO) {
        log.info("Adding address for user: {}", userId);
        try {
            addressDTO.setUserId(userId);
            UserAddressDTO createdAddress = userAddressService.addAddress(addressDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
        } catch (Exception e) {
            log.error("Error adding address", e);
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to add address"));
        }
    }

    // PUT /api/addresses/{addressId} - Update an address
    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<?> updateAddress(@PathVariable Long addressId, @RequestBody UserAddressDTO addressDTO) {
        log.info("Updating address: {}", addressId);
        try {
            UserAddressDTO updatedAddress = userAddressService.updateAddress(addressId, addressDTO);
            return ResponseEntity.ok(updatedAddress);
        } catch (Exception e) {
            log.error("Error updating address", e);
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to update address"));
        }
    }

    // DELETE /api/addresses/{addressId} - Delete an address
    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long addressId) {
        log.info("Deleting address: {}", addressId);
        try {
            userAddressService.deleteAddress(addressId);
            return ResponseEntity.ok(Map.of("success", true, "message", "Address deleted"));
        } catch (Exception e) {
            log.error("Error deleting address", e);
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to delete address"));
        }
    }
}
