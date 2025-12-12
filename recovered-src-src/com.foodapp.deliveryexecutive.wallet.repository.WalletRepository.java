public class com.foodapp.deliveryexecutive.wallet.repository.WalletRepository {
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.wallet.entity.Wallet> findByIdAndRole() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.wallet.entity.Wallet> findByCustomerId() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.wallet.entity.Wallet> findByCustomerIdAndRole() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.wallet.entity.Wallet> findByRole() { return null; }
    public abstract boolean existsByCustomerIdAndRole() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.wallet.entity.Wallet> findByRoleAndBalanceGreaterThan() { return null; }
    public abstract java.lang.Double getTotalBalanceByRole() { return null; }
    public abstract java.lang.Long countByRole() { return null; }
    public abstract java.lang.Double getAverageBalanceByRole() { return null; }
}
