public class com.foodapp.deliveryexecutive.profile.repository.ExecutiveBankAccountRepository {
    public abstract java.util.List<com.foodapp.deliveryexecutive.profile.entity.ExecutiveBankAccount> findByExecutiveId() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.profile.entity.ExecutiveBankAccount> findByExecutiveIdAndIsPrimary() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.profile.entity.ExecutiveBankAccount> findByExecutiveIdAndId() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.profile.entity.ExecutiveBankAccount> findByAccountNumber() { return null; }
    public abstract boolean existsByExecutiveIdAndAccountNumber() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.profile.entity.ExecutiveBankAccount> findVerifiedAccountsByExecutiveId() { return null; }
    public abstract void clearPrimaryForExecutive() { return null; }
    public abstract java.lang.Long countByExecutiveId() { return null; }
}
