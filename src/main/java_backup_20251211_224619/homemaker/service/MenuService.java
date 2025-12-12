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
package com.foodapp.deliveryexecutive.homemaker.service;

import com.foodapp.deliveryexecutive.homemaker.dto.MenuDTO;
import com.foodapp.deliveryexecutive.homemaker.entity.Menu;
import com.foodapp.deliveryexecutive.homemaker.repository.MenuItemRepository;
import com.foodapp.deliveryexecutive.homemaker.repository.MenuRepository;
import java.time.LocalDateTime;
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
public class MenuService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(MenuService.class);
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuDTO createMenu(MenuDTO menuDTO) {
        log.info("Creating new menu for homemaker: {}", (Object)menuDTO.getHomemakerId());
        Menu menu = new Menu();
        menu.setHomemakerId(menuDTO.getHomemakerId());
        menu.setMenuType(menuDTO.getMenuType());
        menu.setDescription(menuDTO.getDescription());
        menu.setCuisineTypes(menuDTO.getCuisineTypes());
        menu.setEstimatedPrepTime(menuDTO.getEstimatedPrepTime());
        menu.setValidFrom(menuDTO.getValidFrom());
        menu.setValidUntil(menuDTO.getValidUntil());
        menu.setIsRecurring(menuDTO.getIsRecurring());
        menu.setRecurrencePattern(menuDTO.getRecurrencePattern());
        menu.setMinOrderQuantity(menuDTO.getMinOrderQuantity());
        menu.setMaxOrderQuantity(menuDTO.getMaxOrderQuantity());
        Menu savedMenu = (Menu)this.menuRepository.save(menu);
        log.info("Menu created successfully with ID: {}", (Object)savedMenu.getId());
        return this.convertToDTO(savedMenu);
    }

    public MenuDTO getMenuById(Long id) {
        log.debug("Fetching menu with ID: {}", (Object)id);
        Menu menu = (Menu)this.menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Menu not found"));
        return this.convertToDTO(menu);
    }

    public MenuDTO updateMenu(Long id, MenuDTO menuDTO) {
        log.info("Updating menu with ID: {}", (Object)id);
        Menu menu = (Menu)this.menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Menu not found"));
        if (menuDTO.getDescription() != null) {
            menu.setDescription(menuDTO.getDescription());
        }
        if (menuDTO.getCuisineTypes() != null) {
            menu.setCuisineTypes(menuDTO.getCuisineTypes());
        }
        if (menuDTO.getEstimatedPrepTime() != null) {
            menu.setEstimatedPrepTime(menuDTO.getEstimatedPrepTime());
        }
        if (menuDTO.getValidFrom() != null) {
            menu.setValidFrom(menuDTO.getValidFrom());
        }
        if (menuDTO.getValidUntil() != null) {
            menu.setValidUntil(menuDTO.getValidUntil());
        }
        if (menuDTO.getMinOrderQuantity() != null) {
            menu.setMinOrderQuantity(menuDTO.getMinOrderQuantity());
        }
        if (menuDTO.getMaxOrderQuantity() != null) {
            menu.setMaxOrderQuantity(menuDTO.getMaxOrderQuantity());
        }
        Menu updatedMenu = (Menu)this.menuRepository.save(menu);
        log.info("Menu updated successfully");
        return this.convertToDTO(updatedMenu);
    }

    public MenuDTO publishMenu(Long id) {
        log.info("Publishing menu with ID: {}", (Object)id);
        Menu menu = (Menu)this.menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Menu not found"));
        menu.setStatus(Menu.MenuStatus.ACTIVE);
        menu.setPublishedDate(LocalDateTime.now());
        Menu publishedMenu = (Menu)this.menuRepository.save(menu);
        log.info("Menu published successfully");
        return this.convertToDTO(publishedMenu);
    }

    public MenuDTO archiveMenu(Long id) {
        log.info("Archiving menu with ID: {}", (Object)id);
        Menu menu = (Menu)this.menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Menu not found"));
        menu.setStatus(Menu.MenuStatus.ARCHIVED);
        Menu archivedMenu = (Menu)this.menuRepository.save(menu);
        log.info("Menu archived successfully");
        return this.convertToDTO(archivedMenu);
    }

    public List<MenuDTO> getHomemakerMenus(Long homemakerId) {
        log.debug("Fetching all menus for homemaker: {}", (Object)homemakerId);
        return this.menuRepository.findByHomemakerId(homemakerId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<MenuDTO> getActiveMenus(Long homemakerId) {
        log.debug("Fetching active menus for homemaker: {}", (Object)homemakerId);
        return this.menuRepository.findActiveMenusForHomemaker(homemakerId, LocalDateTime.now()).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteMenu(Long id) {
        log.info("Deleting menu with ID: {}", (Object)id);
        this.menuRepository.deleteById(id);
        log.info("Menu deleted successfully");
    }

    private MenuDTO convertToDTO(Menu menu) {
        MenuDTO dto = new MenuDTO();
        dto.setId(menu.getId());
        dto.setHomemakerId(menu.getHomemakerId());
        dto.setStatus(menu.getStatus());
        dto.setPublishedDate(menu.getPublishedDate());
        dto.setValidFrom(menu.getValidFrom());
        dto.setValidUntil(menu.getValidUntil());
        dto.setIsRecurring(menu.getIsRecurring());
        dto.setRecurrencePattern(menu.getRecurrencePattern());
        dto.setCuisineTypes(menu.getCuisineTypes());
        dto.setDescription(menu.getDescription());
        dto.setEstimatedPrepTime(menu.getEstimatedPrepTime());
        dto.setMenuType(menu.getMenuType());
        dto.setMinOrderQuantity(menu.getMinOrderQuantity());
        dto.setMaxOrderQuantity(menu.getMaxOrderQuantity());
        dto.setAverageRating(menu.getAverageRating());
        dto.setTotalOrders(menu.getTotalOrders());
        dto.setTotalReviews(menu.getTotalReviews());
        dto.setCreatedAt(menu.getCreatedAt());
        dto.setUpdatedAt(menu.getUpdatedAt());
        return dto;
    }
}
