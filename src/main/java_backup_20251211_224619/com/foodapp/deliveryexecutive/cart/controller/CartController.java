/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.http.ResponseEntity
 *  org.springframework.security.core.annotation.AuthenticationPrincipal
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.foodapp.deliveryexecutive.cart.controller;

import com.foodapp.deliveryexecutive.cart.dto.AddToCartRequest;
import com.foodapp.deliveryexecutive.cart.dto.CartDTO;
import com.foodapp.deliveryexecutive.cart.dto.UpdateCartItemRequest;
import com.foodapp.deliveryexecutive.cart.service.CartService;
import com.foodapp.deliveryexecutive.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/cart"})
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<CartDTO> getCart(@AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok((Object)this.cartService.getCart(principal.getId()));
    }

    @PostMapping(value={"/items"})
    public ResponseEntity<CartDTO> addToCart(@AuthenticationPrincipal UserPrincipal principal, @RequestBody AddToCartRequest request) {
        return ResponseEntity.ok((Object)this.cartService.addToCart(principal.getId(), request));
    }

    @PutMapping(value={"/items/{itemId}"})
    public ResponseEntity<CartDTO> updateCartItem(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Long itemId, @RequestBody UpdateCartItemRequest request) {
        return ResponseEntity.ok((Object)this.cartService.updateCartItem(principal.getId(), itemId, request));
    }

    @DeleteMapping(value={"/items/{itemId}"})
    public ResponseEntity<CartDTO> removeFromCart(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Long itemId) {
        return ResponseEntity.ok((Object)this.cartService.removeFromCart(principal.getId(), itemId));
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal UserPrincipal principal) {
        this.cartService.clearCart(principal.getId());
        return ResponseEntity.noContent().build();
    }
}
