public class com.foodapp.deliveryexecutive.homemaker.repository.MenuRepository {
    public abstract java.util.List<com.foodapp.deliveryexecutive.homemaker.entity.Menu> findByHomemakerId() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.homemaker.entity.Menu> findByHomemakerIdAndStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.homemaker.entity.Menu> findByStatus() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.homemaker.entity.Menu> findByMenuType() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.homemaker.entity.Menu> findActiveMenusForHomemaker() { return null; }
    public abstract java.util.Optional<com.foodapp.deliveryexecutive.homemaker.entity.Menu> findByIdAndHomemakerId() { return null; }
}
