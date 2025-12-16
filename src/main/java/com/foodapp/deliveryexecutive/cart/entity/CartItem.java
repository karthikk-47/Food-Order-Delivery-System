package com.foodapp.deliveryexecutive.cart.entity;

import com.foodapp.deliveryexecutive.cart.entity.Cart;
import com.foodapp.deliveryexecutive.homemaker.entity.HomeMaker;
import com.foodapp.deliveryexecutive.homemaker.entity.MenuItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Generated;

@Entity
@Table(name="cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name="menu_item_id")
    private MenuItem menuItem;
    @ManyToOne
    @JoinColumn(name="homemaker_id")
    private HomeMaker homeMaker;
    @Column(nullable=false)
    private Integer quantity = 1;
    @Column(nullable=false)
    private Double price;
    @Column(name="special_instructions")
    private String specialInstructions;
    @Column(name="added_at")
    private LocalDateTime addedAt;

    @PrePersist
    protected void onCreate() {
        this.addedAt = LocalDateTime.now();
        if (this.menuItem != null && this.price == null) {
            this.price = this.menuItem.getDiscountedPrice() != null ? this.menuItem.getDiscountedPrice() : this.menuItem.getPrice();
        }
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Cart getCart() {
        return this.cart;
    }

    @Generated
    public MenuItem getMenuItem() {
        return this.menuItem;
    }

    @Generated
    public HomeMaker getHomeMaker() {
        return this.homeMaker;
    }

    @Generated
    public Integer getQuantity() {
        return this.quantity;
    }

    @Generated
    public Double getPrice() {
        return this.price;
    }

    @Generated
    public String getSpecialInstructions() {
        return this.specialInstructions;
    }

    @Generated
    public LocalDateTime getAddedAt() {
        return this.addedAt;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Generated
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    @Generated
    public void setHomeMaker(HomeMaker homeMaker) {
        this.homeMaker = homeMaker;
    }

    @Generated
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Generated
    public void setPrice(Double price) {
        this.price = price;
    }

    @Generated
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    @Generated
    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CartItem)) {
            return false;
        }
        CartItem other = (CartItem)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !(this$id).equals(other$id)) {
            return false;
        }
        Integer this$quantity = this.getQuantity();
        Integer other$quantity = other.getQuantity();
        if (this$quantity == null ? other$quantity != null : !(this$quantity).equals(other$quantity)) {
            return false;
        }
        Double this$price = this.getPrice();
        Double other$price = other.getPrice();
        if (this$price == null ? other$price != null : !(this$price).equals(other$price)) {
            return false;
        }
        Cart this$cart = this.getCart();
        Cart other$cart = other.getCart();
        if (this$cart == null ? other$cart != null : !(this$cart).equals(other$cart)) {
            return false;
        }
        MenuItem this$menuItem = this.getMenuItem();
        MenuItem other$menuItem = other.getMenuItem();
        if (this$menuItem == null ? other$menuItem != null : !(this$menuItem).equals(other$menuItem)) {
            return false;
        }
        HomeMaker this$homeMaker = this.getHomeMaker();
        HomeMaker other$homeMaker = other.getHomeMaker();
        if (this$homeMaker == null ? other$homeMaker != null : !(this$homeMaker).equals(other$homeMaker)) {
            return false;
        }
        String this$specialInstructions = this.getSpecialInstructions();
        String other$specialInstructions = other.getSpecialInstructions();
        if (this$specialInstructions == null ? other$specialInstructions != null : !this$specialInstructions.equals(other$specialInstructions)) {
            return false;
        }
        LocalDateTime this$addedAt = this.getAddedAt();
        LocalDateTime other$addedAt = other.getAddedAt();
        return !(this$addedAt == null ? other$addedAt != null : !(this$addedAt).equals(other$addedAt));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof CartItem;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Integer $quantity = this.getQuantity();
        result = result * 59 + ($quantity == null ? 43 : ((Object)$quantity).hashCode());
        Double $price = this.getPrice();
        result = result * 59 + ($price == null ? 43 : ((Object)$price).hashCode());
        Cart $cart = this.getCart();
        result = result * 59 + ($cart == null ? 43 : ((Object)$cart).hashCode());
        MenuItem $menuItem = this.getMenuItem();
        result = result * 59 + ($menuItem == null ? 43 : ((Object)$menuItem).hashCode());
        HomeMaker $homeMaker = this.getHomeMaker();
        result = result * 59 + ($homeMaker == null ? 43 : ((Object)$homeMaker).hashCode());
        String $specialInstructions = this.getSpecialInstructions();
        result = result * 59 + ($specialInstructions == null ? 43 : $specialInstructions.hashCode());
        LocalDateTime $addedAt = this.getAddedAt();
        result = result * 59 + ($addedAt == null ? 43 : ((Object)$addedAt).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "CartItem(id=" + this.getId() + ", cart=" + String.valueOf(this.getCart()) + ", menuItem=" + String.valueOf(this.getMenuItem()) + ", homeMaker=" + String.valueOf(this.getHomeMaker()) + ", quantity=" + this.getQuantity() + ", price=" + this.getPrice() + ", specialInstructions=" + this.getSpecialInstructions() + ", addedAt=" + String.valueOf(this.getAddedAt()) + ")";
    }

    @Generated
    public CartItem() {
    }

    @Generated
    public CartItem(Long id, Cart cart, MenuItem menuItem, HomeMaker homeMaker, Integer quantity, Double price, String specialInstructions, LocalDateTime addedAt) {
        this.id = id;
        this.cart = cart;
        this.menuItem = menuItem;
        this.homeMaker = homeMaker;
        this.quantity = quantity;
        this.price = price;
        this.specialInstructions = specialInstructions;
        this.addedAt = addedAt;
    }
}
