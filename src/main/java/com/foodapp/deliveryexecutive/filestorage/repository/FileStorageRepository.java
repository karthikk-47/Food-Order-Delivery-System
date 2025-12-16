package com.foodapp.deliveryexecutive.filestorage.repository;

import com.foodapp.deliveryexecutive.filestorage.model.FileInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageRepository
extends JpaRepository<FileInfo, String> {
    public List<FileInfo> findByEntityTypeAndEntityId(String var1, String var2);

    public List<FileInfo> findByEntityTypeAndEntityIdAndIsPrimary(String var1, String var2, boolean var3);

    public void deleteByEntityTypeAndEntityId(String var1, String var2);
}
