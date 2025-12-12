public class com.foodapp.deliveryexecutive.payments.repository.PayoutRepository {
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.Payout> findByReferenceId() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.Payout> findByUtr() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.Payout> findByFundAccountId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.Payout> findByStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.Payout> findByMode() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.Payout> findByPurpose() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.Payout> findByBatchId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.Payout> findByFundAccountIdAndStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.Payout> findByStatusAndDateRange() { return null; }
    public abstract java.lang.Long getTotalProcessedAmount() { return null; }
    public abstract java.lang.Long countByStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.Payout> findPendingPayouts() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.Payout> findFailedPayouts() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.Payout> findByFundAccountIdOrderByCreatedAtDesc() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.Payout> findByDateRange() { return null; }
    public abstract boolean existsByReferenceId() { return null; }
    public abstract boolean existsByUtr() { return null; }
}
