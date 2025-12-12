public class com.foodapp.deliveryexecutive.admin.repository.VerificationRepository {
    public abstract java.util.List<com.foodapp.deliveryexecutive.admin.entity.Verification> findByUserIdAndUserType() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.admin.entity.Verification> findByStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.admin.entity.Verification> findByUserTypeAndStatus() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.admin.entity.Verification> findByUserIdAndUserTypeAndVerificationType() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.admin.entity.Verification> findByVerificationTypeAndStatus() { return null; }
}
