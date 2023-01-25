package com.secondpartial.platformreplica.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;

import static com.secondpartial.common.Constants.*;

@Service
public class FirebaseService {
  public ResponseEntity<String> uploadFile(MultipartFile multipartFile) throws IOException {
    String objectName = generateFileName(multipartFile);

    FileInputStream serviceAccount = new FileInputStream(FIREBASE_SDK_JSON);
    File file = convertMultiPartToFile(multipartFile);
    Path filePath = file.toPath();

    Storage storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setProjectId(FIREBASE_PROJECT_ID).build().getService();
    BlobId blobId = BlobId.of(FIREBASE_BUCKET, objectName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();

    System.out.println("Uploading file to Firebase " + blobInfo.getName());
    storage.create(blobInfo, Files.readAllBytes(filePath));

    return ResponseEntity.status(HttpStatus.CREATED).body("file uploaded successfully");
  }

  private File convertMultiPartToFile(MultipartFile file) throws IOException {
    File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
    FileOutputStream fos = new FileOutputStream(convertedFile);
    fos.write(file.getBytes());
    fos.close();
    return convertedFile;
  }

  private String generateFileName(MultipartFile multiPart) {
    return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
  }
}