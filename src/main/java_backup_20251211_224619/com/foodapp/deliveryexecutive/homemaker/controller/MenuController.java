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
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.homemaker.controller;

import com.foodapp.deliveryexecutive.homemaker.dto.MenuDTO;
import com.foodapp.deliveryexecutive.homemaker.service.MenuService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/menu"})
public class MenuController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private MenuService menuService;

    @PostMapping(value={"/create"})
    public ResponseEntity<MenuDTO> createMenu(@RequestBody MenuDTO menuDTO) {
        log.info("Creating new menu for homemaker: {}", (Object)menuDTO.getHomemakerId());
        try {
            MenuDTO createdMenu = this.menuService.createMenu(menuDTO);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body((Object)createdMenu);
        }
        catch (Exception e) {
            log.error("Error creating menu", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/{id}"})
    public ResponseEntity<MenuDTO> getMenu(@PathVariable Long id) {
        log.info("Fetching menu with ID: {}", (Object)id);
        try {
            MenuDTO menu = this.menuService.getMenuById(id);
            return ResponseEntity.ok((Object)menu);
        }
        catch (Exception e) {
            log.error("Error fetching menu", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<MenuDTO> updateMenu(@PathVariable Long id, @RequestBody MenuDTO menuDTO) {
        log.info("Updating menu with ID: {}", (Object)id);
        try {
            MenuDTO updatedMenu = this.menuService.updateMenu(id, menuDTO);
            return ResponseEntity.ok((Object)updatedMenu);
        }
        catch (Exception e) {
            log.error("Error updating menu", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/publish"})
    public ResponseEntity<MenuDTO> publishMenu(@PathVariable Long id) {
        log.info("Publishing menu with ID: {}", (Object)id);
        try {
            MenuDTO publishedMenu = this.menuService.publishMenu(id);
            return ResponseEntity.ok((Object)publishedMenu);
        }
        catch (Exception e) {
            log.error("Error publishing menu", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value={"/{id}/archive"})
    public ResponseEntity<MenuDTO> archiveMenu(@PathVariable Long id) {
        log.info("Archiving menu with ID: {}", (Object)id);
        try {
            MenuDTO archivedMenu = this.menuService.archiveMenu(id);
            return ResponseEntity.ok((Object)archivedMenu);
        }
        catch (Exception e) {
            log.error("Error archiving menu", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/homemaker/{homemakerId}/all"})
    public ResponseEntity<List<MenuDTO>> getHomemakerMenus(@PathVariable Long homemakerId) {
        log.info("Fetching all menus for homemaker: {}", (Object)homemakerId);
        try {
            List<MenuDTO> menus = this.menuService.getHomemakerMenus(homemakerId);
            return ResponseEntity.ok(menus);
        }
        catch (Exception e) {
            log.error("Error fetching homemaker menus", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value={"/homemaker/{homemakerId}/active"})
    public ResponseEntity<List<MenuDTO>> getActiveMenus(@PathVariable Long homemakerId) {
        log.info("Fetching active menus for homemaker: {}", (Object)homemakerId);
        try {
            List<MenuDTO> menus = this.menuService.getActiveMenus(homemakerId);
            return ResponseEntity.ok(menus);
        }
        catch (Exception e) {
            log.error("Error fetching active menus", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value={"/{id}"})
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        log.info("Deleting menu with ID: {}", (Object)id);
        try {
            this.menuService.deleteMenu(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            log.error("Error deleting menu", (Throwable)e);
            return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).build();
        }
    }
}
