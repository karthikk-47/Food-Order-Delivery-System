/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.springframework.core.io.InputStreamResource
 *  org.springframework.http.MediaType
 *  org.springframework.http.ResponseEntity
 *  org.springframework.http.ResponseEntity$BodyBuilder
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 *  org.springframework.web.multipart.MultipartFile
 */
package com.foodapp.deliveryexecutive.filestorage.controller;

import com.foodapp.deliveryexecutive.filestorage.model.FileInfo;
import com.foodapp.deliveryexecutive.filestorage.service.FileStorageService;
import java.io.IOException;
import java.util.List;
import lombok.Generated;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value={"/api/files"})
public class FileStorageController {
    private final FileStorageService fileStorageService;

    @PostMapping(value={"/upload"})
    public FileInfo uploadFile(@RequestParam(value="file") MultipartFile file, @RequestParam String entityType, @RequestParam String entityId, @RequestParam(defaultValue="false") boolean isPrimary) throws IOException {
        return this.fileStorageService.storeFile(file, entityType, entityId, isPrimary);
    }

    @GetMapping(value={"/{fileId}"})
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileId) throws IOException {
        FileInfo fileInfo = this.fileStorageService.getFile(fileId);
        if (fileInfo == null) {
            return ResponseEntity.notFound().build();
        }
        InputStreamResource resource = new InputStreamResource(this.fileStorageService.getFileAsStream(fileId));
        return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().header("Content-Disposition", new String[]{"attachment; filename=" + fileInfo.getOriginalFilename()})).contentType(MediaType.parseMediaType((String)fileInfo.getFileType())).contentLength(fileInfo.getSize()).body(resource);
    }

    @GetMapping(value={"/entity/{entityType}/{entityId}"})
    public List<FileInfo> getFilesByEntity(@PathVariable String entityType, @PathVariable String entityId) {
        return this.fileStorageService.getFilesByEntity(entityType, entityId);
    }

    @GetMapping(value={"/entity/{entityType}/{entityId}/primary"})
    public FileInfo getPrimaryFile(@PathVariable String entityType, @PathVariable String entityId) {
        return this.fileStorageService.getPrimaryFile(entityType, entityId);
    }

    @DeleteMapping(value={"/{fileId}"})
    public void deleteFile(@PathVariable String fileId) {
        this.fileStorageService.deleteFile(fileId);
    }

    @DeleteMapping(value={"/entity/{entityType}/{entityId}"})
    public void deleteFilesByEntity(@PathVariable String entityType, @PathVariable String entityId) {
        this.fileStorageService.deleteFilesByEntity(entityType, entityId);
    }

    @Generated
    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }
}
