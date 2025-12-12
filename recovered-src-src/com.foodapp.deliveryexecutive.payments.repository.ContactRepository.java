public class com.foodapp.deliveryexecutive.payments.repository.ContactRepository {
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.Contact> findByEmail() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.Contact> findByContact() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.Contact> findByReferenceId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.Contact> findByType() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.Contact> findByActive() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.Contact> findActiveByEmail() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.Contact> findActiveByContact() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.Contact> findActiveByType() { return null; }
    public abstract boolean existsByEmail() { return null; }
    public abstract boolean existsByContact() { return null; }
}
