package com.foodapp.deliveryexecutive.cart.service;

import com.foodapp.deliveryexecutive.cart.dto.AddToCartRequest;
import com.foodapp.deliveryexecutive.cart.dto.CartDTO;
import com.foodapp.deliveryexecutive.cart.dto.CartItemDTO;
import com.foodapp.deliveryexecutive.cart.dto.UpdateCartItemRequest;
import com.foodapp.deliveryexecutive.cart.entity.Cart;
import com.foodapp.deliveryexecutive.cart.entity.CartItem;
import com.foodapp.deliveryexecutive.cart.repository.CartItemRepository;
import com.foodapp.deliveryexecutive.cart.repository.CartRepository;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.entity.MenuItem;
import com.foodapp.deliveryexecutive.homemaker.repository.HomeMakerRepository;
import com.foodapp.deliveryexecutive.homemaker.repository.MenuItemRepository;
import com.foodapp.deliveryexecutive.user.entity.User;
import com.foodapp.deliveryexecutive.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;
    private final HomeMakerRepository homeMakerRepository;
    private static final Double DELIVERY_FEE = 30.0;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, UserRepository userRepository, MenuItemRepository menuItemRepository, HomeMakerRepository homeMakerRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
        this.homeMakerRepository = homeMakerRepository;
    }

    @Transactional(readOnly=true)
    public CartDTO getCart(Long userId) {
        Cart cart = this.getOrCreateCart(userId);
        return this.mapToDTO(cart);
    }

    @Transactional
    public CartDTO addToCart(Long userId, AddToCartRequest request) {
        Cart cart = this.getOrCreateCart(userId);
        MenuItem menuItem = (MenuItem)this.menuItemRepository.findById(request.getMenuItemId()).orElseThrow(() -> new RuntimeException("Menu item not found"));
        HomeMaker homeMaker = (HomeMaker)this.homeMakerRepository.findById(request.getHomemakerId()).orElseThrow(() -> new RuntimeException("Homemaker not found"));
        Optional<CartItem> existingItem = this.cartItemRepository.findByCartAndMenuItem(cart, menuItem);
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            if (request.getSpecialInstructions() != null) {
                item.setSpecialInstructions(request.getSpecialInstructions());
            }
            this.cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setMenuItem(menuItem);
            newItem.setHomeMaker(homeMaker);
            newItem.setQuantity(request.getQuantity());
            newItem.setPrice(menuItem.getDiscountedPrice() != null ? menuItem.getDiscountedPrice() : menuItem.getPrice());
            newItem.setSpecialInstructions(request.getSpecialInstructions());
            cart.getItems().add(newItem);
            this.cartItemRepository.save(newItem);
        }
        cart.recalculateTotals();
        this.cartRepository.save(cart);
        return this.mapToDTO(cart);
    }

    @Transactional
    public CartDTO updateCartItem(Long userId, Long itemId, UpdateCartItemRequest request) {
        Cart cart = this.getOrCreateCart(userId);
        CartItem item = (CartItem)this.cartItemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Cart item not found"));
        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Item does not belong to user's cart");
        }
        if (request.getQuantity() != null && request.getQuantity() > 0) {
            item.setQuantity(request.getQuantity());
        }
        if (request.getSpecialInstructions() != null) {
            item.setSpecialInstructions(request.getSpecialInstructions());
        }
        this.cartItemRepository.save(item);
        cart.recalculateTotals();
        this.cartRepository.save(cart);
        return this.mapToDTO(cart);
    }

    @Transactional
    public CartDTO removeFromCart(Long userId, Long itemId) {
        Cart cart = this.getOrCreateCart(userId);
        CartItem item = (CartItem)this.cartItemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Cart item not found"));
        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Item does not belong to user's cart");
        }
        cart.getItems().remove(item);
        this.cartItemRepository.delete(item);
        cart.recalculateTotals();
        this.cartRepository.save(cart);
        return this.mapToDTO(cart);
    }

    @Transactional
    public void clearCart(Long userId) {
        Cart cart = this.getOrCreateCart(userId);
        this.cartItemRepository.deleteByCart(cart);
        cart.getItems().clear();
        cart.setTotalAmount(0.0);
        cart.setItemCount(0);
        this.cartRepository.save(cart);
    }

    private Cart getOrCreateCart(Long userId) {
        return this.cartRepository.findByUserId(userId).orElseGet(() -> {
            User user = (User)this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            Cart newCart = new Cart();
            newCart.setUser(user);
            return (Cart)this.cartRepository.save(newCart);
        });
    }

    private CartDTO mapToDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());
        dto.setTotalAmount(cart.getTotalAmount());
        dto.setItemCount(cart.getItemCount());
        dto.setDeliveryFee(cart.getItemCount() > 0 ? DELIVERY_FEE : 0.0);
        dto.setGrandTotal(cart.getTotalAmount() + (cart.getItemCount() > 0 ? DELIVERY_FEE : 0.0));
        List<CartItemDTO> items = cart.getItems().stream().map(this::mapItemToDTO).collect(Collectors.toList());
        dto.setItems(items);
        return dto;
    }

    private CartItemDTO mapItemToDTO(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setMenuItemId(item.getMenuItem().getId());
        dto.setItemName(item.getMenuItem().getItemName());
        dto.setDescription(item.getMenuItem().getDescription());
        dto.setImageUrl(item.getMenuItem().getPrimaryPhotoUrl());
        dto.setPrice(item.getPrice());
        dto.setQuantity(item.getQuantity());
        dto.setSubtotal(item.getPrice() * (double)item.getQuantity().intValue());
        dto.setHomemakerId(item.getHomeMaker().getId());
        dto.setHomemakerName(item.getHomeMaker().getName());
        dto.setSpecialInstructions(item.getSpecialInstructions());
        dto.setIsVegetarian(item.getMenuItem().getIsVegetarian());
        return dto;
    }
}
