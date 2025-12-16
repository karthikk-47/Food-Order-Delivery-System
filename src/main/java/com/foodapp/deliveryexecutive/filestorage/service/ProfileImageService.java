package com.foodapp.deliveryexecutive.filestorage.service;

import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.filestorage.model.FileInfo;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.entity.MenuItem;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.homemaker.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileImageService {

    private final FileStorageService fileStorageService;
    private final HomeMakerRepository homeMakerRepository;
    private final DeliveryExecutiveRepository deliveryExecutiveRepository;
    private final MenuItemRepository menuItemRepository;

    private static final List<String> ALLOWED_IMAGE_TYPES = Arrays.asList(
            "image/jpeg", "image/png", "image/gif", "image/webp"
    );
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB

    // ============ HOMEMAKER PROFILE IMAGE ============

    @Transactional
    public String uploadHomemakerProfileImage(Long homemakerId, MultipartFile file) throws IOException {
        validateImageFile(file);
        
        HomeMaker homeMaker = homeMakerRepository.findById(homemakerId)
                .orElseThrow(() -> new RuntimeException("Homemaker not found"));
        
        // Delete old profile image if exists
        if (homeMaker.getProfileImageUrl() != null) {
            deleteOldImage("HOMEMAKER_PROFILE", homemakerId.toString());
        }
        
        FileInfo fileInfo = fileStorageService.storeFile(file, "HOMEMAKER_PROFILE", homemakerId.toString(), true);
        
        homeMaker.setProfileImageUrl(fileInfo.getFileUrl());
        homeMakerRepository.save(homeMaker);
        
        log.info("Uploaded profile image for homemaker {}: {}", homemakerId, fileInfo.getFileUrl());
        return fileInfo.getFileUrl();
    }

    @Transactional
    public String uploadHomemakerKitchenImage(Long homemakerId, MultipartFile file) throws IOException {
        validateImageFile(file);
        
        HomeMaker homeMaker = homeMakerRepository.findById(homemakerId)
                .orElseThrow(() -> new RuntimeException("Homemaker not found"));
        
        // Delete old kitchen image if exists
        if (homeMaker.getKitchenImageUrl() != null) {
            deleteOldImage("HOMEMAKER_KITCHEN", homemakerId.toString());
        }
        
        FileInfo fileInfo = fileStorageService.storeFile(file, "HOMEMAKER_KITCHEN", homemakerId.toString(), true);
        
        homeMaker.setKitchenImageUrl(fileInfo.getFileUrl());
        homeMakerRepository.save(homeMaker);
        
        log.info("Uploaded kitchen image for homemaker {}: {}", homemakerId, fileInfo.getFileUrl());
        return fileInfo.getFileUrl();
    }

    // ============ DELIVERY EXECUTIVE PROFILE IMAGE ============

    @Transactional
    public String uploadExecutiveProfileImage(Long executiveId, MultipartFile file) throws IOException {
        validateImageFile(file);
        
        DeliveryExecutive executive = deliveryExecutiveRepository.findById(executiveId)
                .orElseThrow(() -> new RuntimeException("Delivery executive not found"));
        
        // Delete old profile image if exists
        if (executive.getProfileImageUrl() != null) {
            deleteOldImage("EXECUTIVE_PROFILE", executiveId.toString());
        }
        
        FileInfo fileInfo = fileStorageService.storeFile(file, "EXECUTIVE_PROFILE", executiveId.toString(), true);
        
        executive.setProfileImageUrl(fileInfo.getFileUrl());
        deliveryExecutiveRepository.save(executive);
        
        log.info("Uploaded profile image for executive {}: {}", executiveId, fileInfo.getFileUrl());
        return fileInfo.getFileUrl();
    }

    @Transactional
    public String uploadExecutiveVehicleImage(Long executiveId, MultipartFile file) throws IOException {
        validateImageFile(file);
        
        DeliveryExecutive executive = deliveryExecutiveRepository.findById(executiveId)
                .orElseThrow(() -> new RuntimeException("Delivery executive not found"));
        
        // Delete old vehicle image if exists
        if (executive.getVehicleImageUrl() != null) {
            deleteOldImage("EXECUTIVE_VEHICLE", executiveId.toString());
        }
        
        FileInfo fileInfo = fileStorageService.storeFile(file, "EXECUTIVE_VEHICLE", executiveId.toString(), true);
        
        executive.setVehicleImageUrl(fileInfo.getFileUrl());
        deliveryExecutiveRepository.save(executive);
        
        log.info("Uploaded vehicle image for executive {}: {}", executiveId, fileInfo.getFileUrl());
        return fileInfo.getFileUrl();
    }

    // ============ MENU ITEM IMAGE ============

    @Transactional
    public String uploadMenuItemImage(Long menuItemId, MultipartFile file) throws IOException {
        validateImageFile(file);
        
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
        
        // Delete old image if exists
        if (menuItem.getPrimaryPhotoUrl() != null) {
            deleteOldImage("MENU_ITEM", menuItemId.toString());
        }
        
        FileInfo fileInfo = fileStorageService.storeFile(file, "MENU_ITEM", menuItemId.toString(), true);
        
        menuItem.setPrimaryPhotoUrl(fileInfo.getFileUrl());
        menuItemRepository.save(menuItem);
        
        log.info("Uploaded image for menu item {}: {}", menuItemId, fileInfo.getFileUrl());
        return fileInfo.getFileUrl();
    }

    // ============ HELPER METHODS ============

    private void validateImageFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        
        if (!ALLOWED_IMAGE_TYPES.contains(file.getContentType())) {
            throw new IllegalArgumentException("Invalid file type. Allowed: JPEG, PNG, GIF, WebP");
        }
        
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("File size exceeds maximum limit of 5MB");
        }
    }

    private void deleteOldImage(String entityType, String entityId) {
        try {
            fileStorageService.deleteFilesByEntity(entityType, entityId);
        } catch (Exception e) {
            log.warn("Failed to delete old image for {} {}: {}", entityType, entityId, e.getMessage());
        }
    }

    public String getHomemakerProfileImage(Long homemakerId) {
        return homeMakerRepository.findById(homemakerId)
                .map(HomeMaker::getProfileImageUrl)
                .orElse(null);
    }

    public String getExecutiveProfileImage(Long executiveId) {
        return deliveryExecutiveRepository.findById(executiveId)
                .map(DeliveryExecutive::getProfileImageUrl)
                .orElse(null);
    }
}
