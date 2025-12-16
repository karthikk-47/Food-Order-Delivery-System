/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.apache.commons.net.ftp.FTPClient
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.scheduling.annotation.Async
 *  org.springframework.stereotype.Service
 *  org.springframework.web.multipart.MultipartFile
 */
package com.foodapp.deliveryexecutive.filestorage.service.impl;

import com.foodapp.deliveryexecutive.filestorage.config.FtpConfigProperties;
import com.foodapp.deliveryexecutive.filestorage.model.FileInfo;
import com.foodapp.deliveryexecutive.filestorage.repository.FileStorageRepository;
import com.foodapp.deliveryexecutive.filestorage.service.FileStorageService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Generated;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FtpFileStorageService
implements FileStorageService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(FtpFileStorageService.class);
    private final FtpConfigProperties ftpConfigProperties;
    private final FileStorageRepository fileStorageRepository;

    @Override
    public FileInfo storeFile(MultipartFile file, String entityType, String entityId, boolean isPrimary) throws IOException {
        try (InputStream inputStream = file.getInputStream();){
            FileInfo fileInfo = this.storeFile(inputStream, file.getOriginalFilename(), file.getContentType(), file.getSize(), entityType, entityId, isPrimary);
            return fileInfo;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public FileInfo storeFile(InputStream inputStream, String fileName, String contentType, long size, String entityType, String entityId, boolean isPrimary) throws IOException {
        FTPClient ftpClient = this.connectFtp();
        try {
            String fileId = UUID.randomUUID().toString();
            String fileExtension = this.getFileExtension(fileName);
            String remoteFilePath = String.format("%s/%s/%s/%s%s", this.ftpConfigProperties.getBaseDir(), entityType.toLowerCase(), entityId, fileId, fileExtension);
            String directoryPath = String.format("%s/%s/%s", this.ftpConfigProperties.getBaseDir(), entityType.toLowerCase(), entityId);
            this.createDirectories(ftpClient, directoryPath);
            ftpClient.setFileType(2);
            boolean uploaded = ftpClient.storeFile(remoteFilePath, inputStream);
            if (!uploaded) {
                throw new IOException("Failed to upload file to FTP server");
            }
            FileInfo fileInfo = new FileInfo();
            fileInfo.setId(fileId);
            fileInfo.setOriginalFilename(fileName);
            fileInfo.setFileType(contentType);
            fileInfo.setSize(size);
            fileInfo.setFilePath(remoteFilePath);
            fileInfo.setFileUrl(this.generateFileUrl(remoteFilePath));
            fileInfo.setEntityType(entityType);
            fileInfo.setEntityId(entityId);
            fileInfo.setPrimary(isPrimary);
            fileInfo.setUploadedAt(LocalDateTime.now());
            if (isPrimary) {
                this.fileStorageRepository.findByEntityTypeAndEntityIdAndIsPrimary(entityType, entityId, true).forEach(f -> {
                    f.setPrimary(false);
                    this.fileStorageRepository.save(f);
                });
            }
            FileInfo fileInfo2 = (FileInfo)this.fileStorageRepository.save(fileInfo);
            return fileInfo2;
        }
        finally {
            this.disconnectFtp(ftpClient);
        }
    }

    @Override
    public FileInfo getFile(String fileId) {
        return this.fileStorageRepository.findById(fileId).orElse(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public InputStream getFileAsStream(String fileId) throws IOException {
        FileInfo fileInfo = this.getFile(fileId);
        if (fileInfo == null) {
            return null;
        }
        FTPClient ftpClient = this.connectFtp();
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            boolean success = ftpClient.retrieveFile(fileInfo.getFilePath(), (OutputStream)outputStream);
            if (!success) {
                throw new IOException("Failed to retrieve file from FTP server");
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
            return byteArrayInputStream;
        }
        finally {
            this.disconnectFtp(ftpClient);
        }
    }

    @Override
    public List<FileInfo> getFilesByEntity(String entityType, String entityId) {
        return this.fileStorageRepository.findByEntityTypeAndEntityId(entityType, entityId);
    }

    @Override
    public FileInfo getPrimaryFile(String entityType, String entityId) {
        List<FileInfo> primaryFiles = this.fileStorageRepository.findByEntityTypeAndEntityIdAndIsPrimary(entityType, entityId, true);
        return primaryFiles.isEmpty() ? null : primaryFiles.get(0);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Async
    public void deleteFile(String fileId) {
        FileInfo fileInfo = this.getFile(fileId);
        if (fileInfo != null) {
            FTPClient ftpClient = null;
            try {
                ftpClient = this.connectFtp();
                ftpClient.deleteFile(fileInfo.getFilePath());
                this.fileStorageRepository.deleteById(fileId);
            }
            catch (IOException e) {
                log.error("Error deleting file from FTP server: {}", e.getMessage(), e);
            }
            finally {
                this.disconnectFtp(ftpClient);
            }
        }
    }

    @Override
    @Async
    public void deleteFilesByEntity(String entityType, String entityId) {
        List<FileInfo> files = this.getFilesByEntity(entityType, entityId);
        files.forEach(file -> this.deleteFile(file.getId()));
    }

    @Override
    public String generateFileUrl(String filePath) {
        return this.ftpConfigProperties.getBaseUrl() + "/" + filePath;
    }

    private FTPClient connectFtp() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(this.ftpConfigProperties.getConnectTimeout());
        ftpClient.setDataTimeout(this.ftpConfigProperties.getDataTimeout());
        ftpClient.setBufferSize(this.ftpConfigProperties.getBufferSize());
        ftpClient.connect(this.ftpConfigProperties.getHost(), this.ftpConfigProperties.getPort());
        if (!ftpClient.login(this.ftpConfigProperties.getUsername(), this.ftpConfigProperties.getPassword())) {
            throw new IOException("FTP login failed");
        }
        if (this.ftpConfigProperties.isPassiveMode()) {
            ftpClient.enterLocalPassiveMode();
        }
        return ftpClient;
    }

    private void disconnectFtp(FTPClient ftpClient) {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }
        catch (IOException e) {
            log.error("Error disconnecting from FTP server: {}", e.getMessage(), e);
        }
    }

    private void createDirectories(FTPClient ftpClient, String path) throws IOException {
        String[] pathElements = path.split("/");
        StringBuilder dirPath = new StringBuilder();
        for (String dir : pathElements) {
            if (dir.isEmpty()) continue;
            dirPath.append("/").append(dir);
            if (ftpClient.changeWorkingDirectory(dirPath.toString()) || ftpClient.makeDirectory(dirPath.toString())) continue;
            throw new IOException("Unable to create directory: " + String.valueOf(dirPath));
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

    @Generated
    public FtpFileStorageService(FtpConfigProperties ftpConfigProperties, FileStorageRepository fileStorageRepository) {
        this.ftpConfigProperties = ftpConfigProperties;
        this.fileStorageRepository = fileStorageRepository;
    }
}
