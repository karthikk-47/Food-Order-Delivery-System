/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.web.multipart.MultipartFile
 */
package com.foodapp.deliveryexecutive.filestorage.service;

import com.foodapp.deliveryexecutive.filestorage.model.FileInfo;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public FileInfo storeFile(MultipartFile var1, String var2, String var3, boolean var4) throws IOException;

    public FileInfo storeFile(InputStream var1, String var2, String var3, long var4, String var6, String var7, boolean var8) throws IOException;

    public FileInfo getFile(String var1);

    public InputStream getFileAsStream(String var1) throws IOException;

    public List<FileInfo> getFilesByEntity(String var1, String var2);

    public FileInfo getPrimaryFile(String var1, String var2);

    public void deleteFile(String var1);

    public void deleteFilesByEntity(String var1, String var2);

    public String generateFileUrl(String var1);
}
