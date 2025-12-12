public class com.foodapp.deliveryexecutive.rating.repository.RatingRepository {
    public abstract java.util.List<com.foodapp.deliveryexecutive.rating.entity.Rating> findByIdAndRole() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.rating.entity.Rating> findByCustomerId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.rating.entity.Rating> findByCustomerIdAndRole() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.rating.entity.Rating> findByCustomerIdAndRoleAndStars() { return null; }
    public abstract java.lang.Double getAverageRatingByCustomerIdAndRole() { return null; }
    public abstract java.lang.Long countByCustomerIdAndRole() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.rating.entity.Rating> findTopRatingsByCustomerIdAndRole() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.rating.entity.Rating> findRecentRatingsByCustomerIdAndRole() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.rating.entity.Rating> findByRoleAndMinStars() { return null; }
    public abstract boolean existsByCustomerIdAndRole() { return null; }
}
