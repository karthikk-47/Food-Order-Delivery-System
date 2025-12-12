public class com.foodapp.deliveryexecutive.filestorage.service.FileStorageService {
    public abstract com.foodapp.deliveryexecutive.filestorage.model.FileInfo getFile() { return null; }
    public abstract java.util.List<com.foodapp.deliveryexecutive.filestorage.model.FileInfo> getFilesByEntity() { return null; }
    public abstract com.foodapp.deliveryexecutive.filestorage.model.FileInfo getPrimaryFile() { return null; }
    public abstract void deleteFile() { return null; }
    public abstract void deleteFilesByEntity() { return null; }
    public abstract java.lang.String generateFileUrl() { return null; }
}
