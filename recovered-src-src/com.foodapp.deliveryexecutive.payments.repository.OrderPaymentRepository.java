public class com.foodapp.deliveryexecutive.payments.repository.OrderPaymentRepository {
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.OrderPayment> findByOrderId() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.OrderPayment> findByRazorpayOrderId() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.payments.entity.OrderPayment> findByRazorpayPaymentId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.OrderPayment> findByUserId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.OrderPayment> findByUserIdAndStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.OrderPayment> findByStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.OrderPayment> findByUserIdOrderByCreatedAtDesc() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.OrderPayment> findSuccessfulPaymentsByUserId() { return null; }
    public abstract java.lang.Double getTotalAmountPaidByUser() { return null; }
    public abstract java.lang.Long getSuccessfulPaymentCountByUser() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.payments.entity.OrderPayment> findExpiredPendingPayments() { return null; }
}
