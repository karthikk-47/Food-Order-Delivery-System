package com.foodapp.deliveryexecutive.homemaker.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.foodapp.deliveryexecutive.homemaker.entity.MenuItem;
import com.foodapp.deliveryexecutive.homemaker.repository.MenuItemRepository;
import com.foodapp.deliveryexecutive.homemaker.repository.MenuRepository;

@RestController
@RequestMapping("/api")
public class MenuItemController {
    private static final Logger log = LoggerFactory.getLogger(MenuItemController.class);

    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private MenuRepository menuRepository;

    // GET /api/menu-items - Get all menu items
    @GetMapping("/menu-items")
    public ResponseEntity<?> getAllMenuItems() {
        log.info("Fetching all menu items");
        try {
            List<MenuItem> items = menuItemRepository.findAll();
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            log.error("Error fetching menu items", e);
            return ResponseEntity.ok(List.of());
        }
    }

    // GET /api/menu-items/top - Get top selling menu items
    @GetMapping("/menu-items/top")
    public ResponseEntity<?> getTopMenuItems(@RequestParam(defaultValue = "10") int limit) {
        log.info("Fetching top {} menu items", limit);
        try {
            List<MenuItem> items = menuItemRepository.findAll();
            // Sort by soldCount descending and limit
            List<MenuItem> topItems = items.stream()
                .filter(item -> item.getIsAvailable() != null && item.getIsAvailable())
                .sorted((a, b) -> {
                    int soldA = a.getSoldCount() != null ? a.getSoldCount() : 0;
                    int soldB = b.getSoldCount() != null ? b.getSoldCount() : 0;
                    return Integer.compare(soldB, soldA);
                })
                .limit(limit)
                .collect(Collectors.toList());
            return ResponseEntity.ok(topItems);
        } catch (Exception e) {
            log.error("Error fetching top menu items", e);
            return ResponseEntity.ok(List.of());
        }
    }

    // GET /api/menus/{menuId}/items - Get items for a specific menu
    @GetMapping("/menus/{menuId}/items")
    public ResponseEntity<?> getMenuItems(@PathVariable Long menuId) {
        log.info("Fetching items for menu: {}", menuId);
        try {
            List<MenuItem> items = menuItemRepository.findByMenuId(menuId);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            log.error("Error fetching menu items", e);
            return ResponseEntity.ok(List.of());
        }
    }

    // POST /api/menu-items - Create a new menu item
    @PostMapping("/menu-items")
    public ResponseEntity<?> createMenuItem(@RequestBody MenuItem item) {
        log.info("Creating menu item: {}", item.getItemName());
        try {
            item.setIsAvailable(true);
            item.setSoldCount(0);
            MenuItem saved = menuItemRepository.save(item);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            log.error("Error creating menu item", e);
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to create menu item"));
        }
    }

    // PUT /api/menu-items/{itemId} - Update a menu item
    @PutMapping("/menu-items/{itemId}")
    public ResponseEntity<?> updateMenuItem(@PathVariable Long itemId, @RequestBody Map<String, Object> updates) {
        log.info("Updating menu item: {}", itemId);
        try {
            MenuItem item = menuItemRepository.findById(itemId).orElse(null);
            if (item == null) {
                return ResponseEntity.notFound().build();
            }
            
            if (updates.containsKey("itemName")) {
                item.setItemName((String) updates.get("itemName"));
            }
            if (updates.containsKey("description")) {
                item.setDescription((String) updates.get("description"));
            }
            if (updates.containsKey("price")) {
                item.setPrice(((Number) updates.get("price")).doubleValue());
            }
            if (updates.containsKey("isAvailable")) {
                item.setIsAvailable((Boolean) updates.get("isAvailable"));
            }
            if (updates.containsKey("isVegetarian")) {
                item.setIsVegetarian((Boolean) updates.get("isVegetarian"));
            }
            
            MenuItem saved = menuItemRepository.save(item);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            log.error("Error updating menu item", e);
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to update menu item"));
        }
    }

    // DELETE /api/menu-items/{itemId} - Delete a menu item
    @DeleteMapping("/menu-items/{itemId}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable Long itemId) {
        log.info("Deleting menu item: {}", itemId);
        try {
            if (!menuItemRepository.existsById(itemId)) {
                return ResponseEntity.notFound().build();
            }
            menuItemRepository.deleteById(itemId);
            return ResponseEntity.ok(Map.of("success", true, "message", "Menu item deleted"));
        } catch (Exception e) {
            log.error("Error deleting menu item", e);
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to delete menu item"));
        }
    }
}
