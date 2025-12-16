/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 *  org.springframework.web.multipart.MultipartFile
 */
package com.foodapp.deliveryexecutive.homemaker.service;

import com.foodapp.deliveryexecutive.homemaker.dto.MenuItemDTO;
import com.foodapp.deliveryexecutive.homemaker.entity.MenuItem;
import com.foodapp.deliveryexecutive.homemaker.repository.MenuItemRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class MenuItemService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(MenuItemService.class);
    private final MenuItemRepository menuItemRepository;
    private static final String UPLOAD_DIR = "uploads/menu-items";

    public MenuItemDTO createMenuItem(MenuItemDTO menuItemDTO) {
        log.info("Creating menu item: {}", menuItemDTO.getItemName());
        MenuItem item = new MenuItem(menuItemDTO.getItemName(), menuItemDTO.getPrice());
        item.setDescription(menuItemDTO.getDescription());
        item.setPrimaryPhotoUrl(menuItemDTO.getItemImage());
        item.setDiscountedPrice(menuItemDTO.getDiscountedPrice());
        item.setQuantity(menuItemDTO.getQuantity());
        item.setIsVegetarian(menuItemDTO.getIsVegetarian());
        item.setIsSpicy(menuItemDTO.getIsSpicy());
        item.setContainsNuts(menuItemDTO.getContainsNuts());
        item.setContainsDairy(menuItemDTO.getContainsDairy());
        item.setIngredients(menuItemDTO.getIngredients());
        item.setPreparationNotes(menuItemDTO.getPreparationNotes());
        item.setEstimatedPrepTime(menuItemDTO.getEstimatedPrepTime());
        item.setCalories(menuItemDTO.getCalories());
        item.setServingSize(menuItemDTO.getServingSize());
        item.setIsAvailable(menuItemDTO.getIsAvailable() != null ? menuItemDTO.getIsAvailable() : true);
        item.setSoldCount(menuItemDTO.getSoldCount() != null ? menuItemDTO.getSoldCount() : 0);
        MenuItem savedItem = (MenuItem)this.menuItemRepository.save(item);
        log.info("Menu item created successfully with ID: {}", savedItem.getId());
        return this.convertToDTO(savedItem);
    }

    public MenuItemDTO createMenuItemWithPhoto(String itemName, String description, Double price, Double estimatedPrepTime, Boolean isAvailable, MultipartFile photo) {
        log.info("Creating menu item with photo: {}", itemName);
        MenuItem item = new MenuItem(itemName, price);
        item.setDescription(description);
        item.setEstimatedPrepTime(estimatedPrepTime);
        item.setIsAvailable(isAvailable != null ? isAvailable : true);
        item.setSoldCount(0);
        if (photo != null && !photo.isEmpty()) {
            try {
                String photoUrl = this.savePhoto(photo);
                item.setPrimaryPhotoUrl(photoUrl);
            }
            catch (IOException e) {
                log.error("Error saving photo", e);
                throw new RuntimeException("Failed to save photo", e);
            }
        }
        MenuItem savedItem = (MenuItem)this.menuItemRepository.save(item);
        log.info("Menu item with photo created successfully with ID: {}", savedItem.getId());
        return this.convertToDTO(savedItem);
    }

    public MenuItemDTO getMenuItemById(Long id) {
        log.debug("Fetching menu item with ID: {}", id);
        MenuItem item = (MenuItem)this.menuItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Menu item not found"));
        return this.convertToDTO(item);
    }

    public MenuItemDTO updateMenuItem(Long id, MenuItemDTO menuItemDTO) {
        log.info("Updating menu item with ID: {}", id);
        MenuItem item = (MenuItem)this.menuItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Menu item not found"));
        if (menuItemDTO.getItemName() != null) {
            item.setItemName(menuItemDTO.getItemName());
        }
        if (menuItemDTO.getDescription() != null) {
            item.setDescription(menuItemDTO.getDescription());
        }
        if (menuItemDTO.getPrice() != null) {
            item.setPrice(menuItemDTO.getPrice());
        }
        if (menuItemDTO.getQuantity() != null) {
            item.setQuantity(menuItemDTO.getQuantity());
        }
        if (menuItemDTO.getEstimatedPrepTime() != null) {
            item.setEstimatedPrepTime(menuItemDTO.getEstimatedPrepTime());
        }
        if (menuItemDTO.getIsVegetarian() != null) {
            item.setIsVegetarian(menuItemDTO.getIsVegetarian());
        }
        if (menuItemDTO.getIsSpicy() != null) {
            item.setIsSpicy(menuItemDTO.getIsSpicy());
        }
        if (menuItemDTO.getIsAvailable() != null) {
            item.setIsAvailable(menuItemDTO.getIsAvailable());
        }
        MenuItem updatedItem = (MenuItem)this.menuItemRepository.save(item);
        log.info("Menu item updated successfully");
        return this.convertToDTO(updatedItem);
    }

    public MenuItemDTO updateMenuItemWithPhoto(Long id, String itemName, String description, Double price, Double estimatedPrepTime, Boolean isAvailable, MultipartFile photo) {
        log.info("Updating menu item with photo: {}", id);
        MenuItem item = (MenuItem)this.menuItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Menu item not found"));
        item.setItemName(itemName);
        item.setDescription(description);
        item.setPrice(price);
        item.setEstimatedPrepTime(estimatedPrepTime);
        item.setIsAvailable(isAvailable != null ? isAvailable : true);
        if (photo != null && !photo.isEmpty()) {
            try {
                if (item.getPrimaryPhotoUrl() != null) {
                    this.deletePhoto(item.getPrimaryPhotoUrl());
                }
                String photoUrl = this.savePhoto(photo);
                item.setPrimaryPhotoUrl(photoUrl);
            }
            catch (IOException e) {
                log.error("Error saving photo", e);
                throw new RuntimeException("Failed to save photo", e);
            }
        }
        MenuItem updatedItem = (MenuItem)this.menuItemRepository.save(item);
        log.info("Menu item with photo updated successfully");
        return this.convertToDTO(updatedItem);
    }

    public MenuItemDTO toggleAvailability(Long id) {
        log.info("Toggling availability for menu item: {}", id);
        MenuItem item = (MenuItem)this.menuItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Menu item not found"));
        item.setIsAvailable(item.getIsAvailable() == false);
        MenuItem updatedItem = (MenuItem)this.menuItemRepository.save(item);
        log.info("Availability toggled successfully");
        return this.convertToDTO(updatedItem);
    }

    public List<MenuItemDTO> getMenuItems(Long menuId) {
        log.debug("Fetching items for menu: {}", menuId);
        return this.menuItemRepository.findByMenuId(menuId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<MenuItemDTO> getAllMenuItems() {
        log.debug("Fetching all menu items");
        return this.menuItemRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteMenuItem(Long id) {
        log.info("Deleting menu item with ID: {}", id);
        MenuItem item = this.menuItemRepository.findById(id).orElse(null);
        if (item != null && item.getPrimaryPhotoUrl() != null) {
            try {
                this.deletePhoto(item.getPrimaryPhotoUrl());
            }
            catch (IOException e) {
                log.error("Error deleting photo", e);
            }
        }
        this.menuItemRepository.deleteById(id);
        log.info("Menu item deleted successfully");
    }

    private String savePhoto(MultipartFile photo) throws IOException {
        if (photo.isEmpty()) {
            return null;
        }
        Path uploadPath = Paths.get(UPLOAD_DIR, new String[0]);
        if (!Files.exists(uploadPath, new LinkOption[0])) {
            Files.createDirectories(uploadPath, new FileAttribute[0]);
        }
        String filename = UUID.randomUUID().toString() + "_" + photo.getOriginalFilename();
        Path filePath = uploadPath.resolve(filename);
        Files.write(filePath, photo.getBytes(), new OpenOption[0]);
        log.info("Photo saved: {}", filePath);
        return filePath.toString();
    }

    private void deletePhoto(String photoPath) throws IOException {
        Path path;
        if (photoPath != null && !photoPath.isEmpty() && Files.exists(path = Paths.get(photoPath, new String[0]), new LinkOption[0])) {
            Files.delete(path);
            log.info("Photo deleted: {}", photoPath);
        }
    }

    private MenuItemDTO convertToDTO(MenuItem menuItem) {
        if (menuItem == null) {
            return null;
        }
        return MenuItemDTO.builder().id(menuItem.getId()).itemName(menuItem.getItemName()).description(menuItem.getDescription()).itemImage(menuItem.getPrimaryPhotoUrl()).price(menuItem.getPrice()).discountedPrice(menuItem.getDiscountedPrice()).quantity(menuItem.getQuantity()).isVegetarian(menuItem.getIsVegetarian()).isSpicy(menuItem.getIsSpicy()).containsNuts(menuItem.getContainsNuts()).containsDairy(menuItem.getContainsDairy()).ingredients(menuItem.getIngredients()).preparationNotes(menuItem.getPreparationNotes()).estimatedPrepTime(menuItem.getEstimatedPrepTime()).calories(menuItem.getCalories()).servingSize(menuItem.getServingSize()).isAvailable(menuItem.getIsAvailable()).soldCount(menuItem.getSoldCount()).build();
    }

    @Generated
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }
}
