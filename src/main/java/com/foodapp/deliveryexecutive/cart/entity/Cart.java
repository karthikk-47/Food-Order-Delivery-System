package com.foodapp.deliveryexecutive.cart.entity;

import com.foodapp.deliveryexecutive.cart.entity.CartItem;
import com.foodapp.deliveryexecutive.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

@Entity
@Table(name="carts")
public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="user_id", unique=true)
    private User user;
    @OneToMany(mappedBy="cart", cascade={CascadeType.ALL}, orphanRemoval=true)
    private List<CartItem> items = new ArrayList<CartItem>();
    @Column(name="total_amount")
    private Double totalAmount = 0.0;
    @Column(name="item_count")
    private Integer itemCount = 0;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void recalculateTotals() {
        this.totalAmount = this.items.stream().mapToDouble(item -> item.getPrice() * (double)item.getQuantity().intValue()).sum();
        this.itemCount = this.items.stream().mapToInt(CartItem::getQuantity).sum();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public User getUser() {
        return this.user;
    }

    @Generated
    public List<CartItem> getItems() {
        return this.items;
    }

    @Generated
    public Double getTotalAmount() {
        return this.totalAmount;
    }

    @Generated
    public Integer getItemCount() {
        return this.itemCount;
    }

    @Generated
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setUser(User user) {
        this.user = user;
    }

    @Generated
    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    @Generated
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Generated
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    @Generated
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Cart)) {
            return false;
        }
        Cart other = (Cart)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !(this$id).equals(other$id)) {
            return false;
        }
        Double this$totalAmount = this.getTotalAmount();
        Double other$totalAmount = other.getTotalAmount();
        if (this$totalAmount == null ? other$totalAmount != null : !(this$totalAmount).equals(other$totalAmount)) {
            return false;
        }
        Integer this$itemCount = this.getItemCount();
        Integer other$itemCount = other.getItemCount();
        if (this$itemCount == null ? other$itemCount != null : !(this$itemCount).equals(other$itemCount)) {
            return false;
        }
        User this$user = this.getUser();
        User other$user = other.getUser();
        if (this$user == null ? other$user != null : !(this$user).equals(other$user)) {
            return false;
        }
        List<CartItem> this$items = this.getItems();
        List<CartItem> other$items = other.getItems();
        if (this$items == null ? other$items != null : !(this$items).equals(other$items)) {
            return false;
        }
        LocalDateTime this$createdAt = this.getCreatedAt();
        LocalDateTime other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !(this$createdAt).equals(other$createdAt)) {
            return false;
        }
        LocalDateTime this$updatedAt = this.getUpdatedAt();
        LocalDateTime other$updatedAt = other.getUpdatedAt();
        return !(this$updatedAt == null ? other$updatedAt != null : !(this$updatedAt).equals(other$updatedAt));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Cart;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Double $totalAmount = this.getTotalAmount();
        result = result * 59 + ($totalAmount == null ? 43 : ((Object)$totalAmount).hashCode());
        Integer $itemCount = this.getItemCount();
        result = result * 59 + ($itemCount == null ? 43 : ((Object)$itemCount).hashCode());
        User $user = this.getUser();
        result = result * 59 + ($user == null ? 43 : ((Object)$user).hashCode());
        List<CartItem> $items = this.getItems();
        result = result * 59 + ($items == null ? 43 : ((Object)$items).hashCode());
        LocalDateTime $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : ((Object)$createdAt).hashCode());
        LocalDateTime $updatedAt = this.getUpdatedAt();
        result = result * 59 + ($updatedAt == null ? 43 : ((Object)$updatedAt).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Cart(id=" + this.getId() + ", user=" + String.valueOf(this.getUser()) + ", items=" + String.valueOf(this.getItems()) + ", totalAmount=" + this.getTotalAmount() + ", itemCount=" + this.getItemCount() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", updatedAt=" + String.valueOf(this.getUpdatedAt()) + ")";
    }

    @Generated
    public Cart() {
    }

    @Generated
    public Cart(Long id, User user, List<CartItem> items, Double totalAmount, Integer itemCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.items = items;
        this.totalAmount = totalAmount;
        this.itemCount = itemCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
