public class com.foodapp.deliveryexecutive.order.repository.OrderRepository {
    public abstract java.util.List<com.foodapp.deliveryexecutive.order.entity.Order> findByExecutiveId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.order.entity.Order> findByExecutiveIdAndOrderStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.order.entity.Order> findByOrderStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.order.entity.Order> findByOrderStatusOrOrderStatus() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.order.entity.Order> findByIdAndExecutiveId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.order.entity.Order> findByUserId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.order.entity.Order> findByHomeMakerId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.order.entity.Order> findByOrderStatusIn() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.order.entity.Order> findByExecutiveIdOrderByIdDesc() { return null; }
    public abstract java.lang.Long countByExecutiveIdAndStatus() { return null; }
    public abstract java.lang.Double getTotalEarningsByExecutiveId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.order.entity.Order> findAvailableOrders() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.order.entity.Order> findActiveOrdersByExecutiveId() { return null; }
    public abstract boolean existsByIdAndExecutiveId() { return null; }
}
