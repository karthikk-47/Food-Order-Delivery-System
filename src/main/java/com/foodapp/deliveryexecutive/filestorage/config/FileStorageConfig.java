package com.foodapp.deliveryexecutive.filestorage.config;

import com.foodapp.deliveryexecutive.filestorage.config.FtpConfigProperties;
import com.foodapp.deliveryexecutive.filestorage.repository.FileStorageRepository;
import com.foodapp.deliveryexecutive.filestorage.service.FileStorageService;
import com.foodapp.deliveryexecutive.filestorage.service.impl.FtpFileStorageService;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@EnableConfigurationProperties(value={FtpConfigProperties.class})
public class FileStorageConfig {
    @Bean
    public FTPClient ftpClient() {
        return new FTPClient();
    }

    @Bean
    public FileStorageService fileStorageService(FtpConfigProperties ftpConfigProperties, FileStorageRepository fileStorageRepository, FTPClient ftpClient) {
        return new FtpFileStorageService(ftpConfigProperties, fileStorageRepository);
    }
}
