package com.secondpartial.platformreplica.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.secondpartial.platformreplica.models.vm.AssetModel;
import com.secondpartial.platformreplica.services.S3Service;

@RestController
@RequestMapping("/assets")
public class AssetController {
    @Autowired
    S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<LinkedHashMap<String, Object>> uploadFile(@RequestParam MultipartFile file) {
        String key = s3Service.putObject(file);
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("key", key);
        response.put("url", s3Service.getObjectURL(key));

        return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/download", params = "key")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam String key) {
        AssetModel asset = s3Service.getObject(key);
        ByteArrayResource resource = new ByteArrayResource(asset.getContent());

        return ResponseEntity.ok().header("Content-Type", asset.getContentType())
                .contentLength(asset.getContent().length)
                .body(resource);
    }

    @DeleteMapping(value = "/delete-object", params = "key")
    public ResponseEntity<LinkedHashMap<String, Object>> deleteObject(@RequestParam String key) {
        s3Service.deleteObject(key);
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Object deleted successfully");
        return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
    }
}
