public class com.foodapp.deliveryexecutive.homemaker.repository.HomemakerWithdrawalRepository {
    public abstract java.util.List<com.foodapp.deliveryexecutive.homemaker.entity.HomemakerWithdrawal> findByHomemakerId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.homemaker.entity.HomemakerWithdrawal> findByHomemakerIdAndStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.homemaker.entity.HomemakerWithdrawal> findByStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.homemaker.entity.HomemakerWithdrawal> findRecentWithdrawals() { return null; }
    public abstract java.lang.Double getTotalCompletedWithdrawals() { return null; }
    public abstract java.lang.Double getTotalPendingWithdrawals() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.homemaker.entity.HomemakerWithdrawal> findByTransactionId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.homemaker.entity.HomemakerWithdrawal> findByRequestDateBetween() { return null; }
}
