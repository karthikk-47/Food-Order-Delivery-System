public class com.foodapp.deliveryexecutive.payments.repository.FundAccountRepository {
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.FundAccountEntity> findByStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.FundAccountEntity> findByActive() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.FundAccountEntity> findByBatchId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.FundAccountEntity> findActiveByStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.FundAccountEntity> findAllActive() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.FundAccountEntity> findByUtr() { return null; }
    public abstract java.lang.Long countByStatus() { return null; }
    public abstract java.lang.Long countActive() { return null; }
}
