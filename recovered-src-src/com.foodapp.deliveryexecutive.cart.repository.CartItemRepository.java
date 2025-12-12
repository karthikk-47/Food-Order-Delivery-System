public class com.foodapp.deliveryexecutive.cart.repository.CartItemRepository {
    public abstract java.util.List<com.foodapp.deliveryexecutive.cart.entity.CartItem> findByCart() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.cart.entity.CartItem> findByCartAndMenuItem() { return null; }
    public abstract void deleteByCart() { return null; }
}
