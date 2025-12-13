/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.foodapp.deliveryexecutive.user.service;

import com.foodapp.deliveryexecutive.user.dto.UserAddressDTO;
import com.foodapp.deliveryexecutive.user.entity.UserAddress;
import com.foodapp.deliveryexecutive.user.repository.UserAddressRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAddressService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(UserAddressService.class);
    @Autowired
    private UserAddressRepository userAddressRepository;

    public UserAddressDTO addAddress(UserAddressDTO addressDTO) {
        log.info("Adding address for user: {}", (Object)addressDTO.getUserId());
        List<UserAddress> existingAddresses = this.userAddressRepository.findByUserId(addressDTO.getUserId());
        UserAddress address = new UserAddress();
        address.setUserId(addressDTO.getUserId());
        address.setAddressLabel(addressDTO.getAddressLabel());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());
        address.setPhoneNumber(addressDTO.getPhoneNumber());
        address.setLatitude(addressDTO.getLatitude());
        address.setLongitude(addressDTO.getLongitude());
        address.setDeliveryInstructions(addressDTO.getDeliveryInstructions());
        address.setIsDefault(existingAddresses.isEmpty() ? true : addressDTO.getIsDefault());
        UserAddress savedAddress = (UserAddress)this.userAddressRepository.save(address);
        log.info("Address added successfully with ID: {}", (Object)savedAddress.getId());
        return this.convertToDTO(savedAddress);
    }

    public UserAddressDTO getAddressById(Long id) {
        log.debug("Fetching address with ID: {}", (Object)id);
        UserAddress address = (UserAddress)this.userAddressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Address not found"));
        return this.convertToDTO(address);
    }

    public UserAddressDTO updateAddress(Long id, UserAddressDTO addressDTO) {
        log.info("Updating address with ID: {}", (Object)id);
        UserAddress address = (UserAddress)this.userAddressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Address not found"));
        if (addressDTO.getAddressLabel() != null) {
            address.setAddressLabel(addressDTO.getAddressLabel());
        }
        if (addressDTO.getStreet() != null) {
            address.setStreet(addressDTO.getStreet());
        }
        if (addressDTO.getDeliveryInstructions() != null) {
            address.setDeliveryInstructions(addressDTO.getDeliveryInstructions());
        }
        UserAddress updatedAddress = (UserAddress)this.userAddressRepository.save(address);
        log.info("Address updated successfully");
        return this.convertToDTO(updatedAddress);
    }

    public void setAsDefault(Long userId, Long addressId) {
        log.info("Setting address {} as default for user: {}", (Object)addressId, (Object)userId);
        List<UserAddress> userAddresses = this.userAddressRepository.findByUserId(userId);
        userAddresses.forEach(addr -> addr.setIsDefault(false));
        this.userAddressRepository.saveAll(userAddresses);
        UserAddress address = (UserAddress)this.userAddressRepository.findById(addressId).orElseThrow(() -> new IllegalArgumentException("Address not found"));
        address.setIsDefault(true);
        this.userAddressRepository.save(address);
        log.info("Default address set successfully");
    }

    public List<UserAddressDTO> getUserAddresses(Long userId) {
        log.debug("Fetching all addresses for user: {}", (Object)userId);
        return this.userAddressRepository.findByUserIdAndIsActive(userId, true).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteAddress(Long id) {
        log.info("Deleting address with ID: {}", (Object)id);
        UserAddress address = (UserAddress)this.userAddressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Address not found"));
        address.setIsActive(false);
        this.userAddressRepository.save(address);
        log.info("Address deleted successfully");
    }

    private UserAddressDTO convertToDTO(UserAddress address) {
        UserAddressDTO dto = new UserAddressDTO();
        dto.setId(address.getId());
        dto.setUserId(address.getUserId());
        dto.setAddressLabel(address.getAddressLabel());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setZipCode(address.getZipCode());
        dto.setPhoneNumber(address.getPhoneNumber());
        dto.setLatitude(address.getLatitude());
        dto.setLongitude(address.getLongitude());
        dto.setIsDefault(address.getIsDefault());
        dto.setIsActive(address.getIsActive());
        dto.setDeliveryInstructions(address.getDeliveryInstructions());
        dto.setCreatedAt(address.getCreatedAt());
        dto.setUpdatedAt(address.getUpdatedAt());
        return dto;
    }
}
