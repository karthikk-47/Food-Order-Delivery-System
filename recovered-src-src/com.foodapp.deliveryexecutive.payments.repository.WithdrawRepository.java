public class com.foodapp.deliveryexecutive.payments.repository.WithdrawRepository {
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.WithdrawTransaction> findByCustomerIdOrderByCreatedAtDesc() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.WithdrawTransaction> findByCustomerIdAndStatusOrderByCreatedAtDesc() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.WithdrawTransaction> findByPayoutId() { return null; }
    public abstract java.lang.Double getTotalWithdrawnAmount() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.WithdrawTransaction> findByCustomerIdAndDateRange() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.WithdrawTransaction> findByStatus() { return null; }
    public abstract java.lang.Long countByCustomerIdAndStatus() { return null; }
}
