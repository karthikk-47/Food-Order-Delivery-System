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
 *  org.springframework.web.multipart.MultipartFile
 */
package com.foodapp.deliveryexecutive.homemaker.controller;

import com.foodapp.deliveryexecutive.homemaker.dto.MenuItemDTO;
import com.foodapp.deliveryexecutive.homemaker.service.MenuItemService;
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
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value={"/api"})
public class MenuItemController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(MenuItemController.class);
    @Autowired
    private MenuItemService menuItemService;

    @PostMapping(value={"/menu-item/create", "/menu-items"}, consumes={"application/json"})
    public ResponseEntity<MenuItemDTO> createMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        log.info("Creating menu item: {}", menuItemDTO.getItemName());
        try {
            MenuItemDTO createdItem = this.menuItemService.createMenuItem(menuItemDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
        }
        catch (Exception e) {
            log.error("Error creating menu item", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping(value={"/menu-item/create", "/menu-items"}, consumes={"multipart/form-data"})
    public ResponseEntity<MenuItemDTO> createMenuItemWithPhoto(@RequestParam String itemName, @RequestParam String description, @RequestParam Double price, @RequestParam Double estimatedPrepTime, @RequestParam Boolean isAvailable, @RequestParam(required=false) MultipartFile photo) {
        log.info("Creating menu item with photo: {}", itemName);
        try {
            MenuItemDTO createdItem = this.menuItemService.createMenuItemWithPhoto(itemName, description, price, estimatedPrepTime, isAvailable, photo);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
        }
        catch (Exception e) {
            log.error("Error creating menu item with photo", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value={"/menu-items"})
    public ResponseEntity<List<MenuItemDTO>> getMenuItems() {
        log.info("Fetching all menu items");
        try {
            List<MenuItemDTO> items = this.menuItemService.getAllMenuItems();
            return ResponseEntity.ok(items);
        }
        catch (Exception e) {
            log.error("Error fetching menu items", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/menu-item/{id}", "/menu-items/{id}"})
    public ResponseEntity<MenuItemDTO> getMenuItem(@PathVariable Long id) {
        log.info("Fetching menu item with ID: {}", id);
        try {
            MenuItemDTO item = this.menuItemService.getMenuItemById(id);
            return ResponseEntity.ok(item);
        }
        catch (Exception e) {
            log.error("Error fetching menu item", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value={"/menu-item/{id}", "/menu-items/{id}"}, consumes={"application/json"})
    public ResponseEntity<MenuItemDTO> updateMenuItem(@PathVariable Long id, @RequestBody MenuItemDTO menuItemDTO) {
        log.info("Updating menu item with ID: {}", id);
        try {
            MenuItemDTO updatedItem = this.menuItemService.updateMenuItem(id, menuItemDTO);
            return ResponseEntity.ok(updatedItem);
        }
        catch (Exception e) {
            log.error("Error updating menu item", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value={"/menu-item/{id}", "/menu-items/{id}"}, consumes={"multipart/form-data"})
    public ResponseEntity<MenuItemDTO> updateMenuItemWithPhoto(@PathVariable Long id, @RequestParam String itemName, @RequestParam String description, @RequestParam Double price, @RequestParam Double estimatedPrepTime, @RequestParam Boolean isAvailable, @RequestParam(required=false) MultipartFile photo) {
        log.info("Updating menu item with photo: {}", id);
        try {
            MenuItemDTO updatedItem = this.menuItemService.updateMenuItemWithPhoto(id, itemName, description, price, estimatedPrepTime, isAvailable, photo);
            return ResponseEntity.ok(updatedItem);
        }
        catch (Exception e) {
            log.error("Error updating menu item with photo", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/menu-item/{id}/toggle-availability", "/menu-items/{id}/toggle-availability"})
    public ResponseEntity<MenuItemDTO> toggleAvailability(@PathVariable Long id) {
        log.info("Toggling availability for menu item: {}", id);
        try {
            MenuItemDTO item = this.menuItemService.toggleAvailability(id);
            return ResponseEntity.ok(item);
        }
        catch (Exception e) {
            log.error("Error toggling availability", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/menu/{menuId}/items"})
    public ResponseEntity<List<MenuItemDTO>> getMenuItems(@PathVariable Long menuId) {
        log.info("Fetching items for menu: {}", menuId);
        try {
            List<MenuItemDTO> items = this.menuItemService.getMenuItems(menuId);
            return ResponseEntity.ok(items);
        }
        catch (Exception e) {
            log.error("Error fetching menu items", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value={"/menu-item/{id}", "/menu-items/{id}"})
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        log.info("Deleting menu item with ID: {}", id);
        try {
            this.menuItemService.deleteMenuItem(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            log.error("Error deleting menu item", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
