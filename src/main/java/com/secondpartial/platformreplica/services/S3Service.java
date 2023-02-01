package com.secondpartial.platformreplica.services;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.secondpartial.platformreplica.models.AssignmentModel;
import com.secondpartial.platformreplica.models.AssignmentStudentModel;
import com.secondpartial.platformreplica.models.UserModel;
import com.secondpartial.platformreplica.models.vm.AssetModel;

@Service
public class S3Service {
  private final static String BUCKET_NAME = "uta-platform-bucket";

  @Autowired
  private AmazonS3Client s3Client;

  public String putObject(MultipartFile multipartFile) {
    String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
    String key = String.format("%s.%s", multipartFile.getName(), extension);

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentType(multipartFile.getContentType());

    try {
      PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, multipartFile.getInputStream(),
          metadata).withCannedAcl(CannedAccessControlList.PublicRead);

      s3Client.putObject(putObjectRequest);
      return key;
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public AssetModel getObject(String key) {
    S3Object s3Object = s3Client.getObject(BUCKET_NAME, key);
    ObjectMetadata metadata = s3Object.getObjectMetadata();
    try {
      S3ObjectInputStream inputStream = s3Object.getObjectContent();
      byte[] bytes = IOUtils.toByteArray(inputStream);
      return new AssetModel(bytes, metadata.getContentType());
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public void deleteObject(String key) {
    s3Client.deleteObject(BUCKET_NAME, key);
  }

  public String getObjectURL(String key) {
    return String.format("https://%s.s3.amazonaws.com/%s", BUCKET_NAME, key);
  }

  public LinkedHashMap<String, String> setUserImage(UserModel userModel, MultipartFile userImage) {
    if (userModel.getUserImageKey() != null) {
      s3Client.deleteObject(BUCKET_NAME, userModel.getUserImageKey());
    }

    LinkedHashMap<String, String> userImageMap = new LinkedHashMap<>();
    String extension = StringUtils.getFilenameExtension(userImage.getOriginalFilename());

    if (extension == null) {
      extension = "jpg";
    }
    String key = String.format("users/%s/userImage/%s_img_%s.%s", userModel.getId(), userModel.getId(),
        new Date().getTime(), extension);
    String url = "";

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentType(userImage.getContentType());

    try {
      PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, userImage.getInputStream(),
          metadata).withCannedAcl(CannedAccessControlList.PublicRead);

      s3Client.putObject(putObjectRequest);
      url = String.format("https://%s.s3.amazonaws.com/%s", BUCKET_NAME, key);

      userImageMap.put("userImageKey", key);
      userImageMap.put("userImageUrl", url);
      return userImageMap;
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public String setStudentFiles(AssignmentStudentModel assignmentStudentModel,
      List<MultipartFile> files) {
    String studentFilesMap = "";
    String key = "";
    String url = "";
    String extension = "";
    String fileName = "";

    for (MultipartFile file : files) {
      extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
      fileName = file.getOriginalFilename().replace("." + extension, "");

      key = String.format("assignments/%s/students/%s/%s.%s", assignmentStudentModel.getAssignmentId(),
          assignmentStudentModel.getStudentId(), fileName, extension);

      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentType(file.getContentType());

      try {
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, file.getInputStream(),
            metadata).withCannedAcl(CannedAccessControlList.PublicRead);

        s3Client.putObject(putObjectRequest);
        url = String.format("https://%s.s3.amazonaws.com/%s", BUCKET_NAME, key);

        if (studentFilesMap.equals("")) {
          studentFilesMap += url;
        } else {
          studentFilesMap += ", " + url;
        }

      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }
    return studentFilesMap;
  }

  public String setAssignmentFiles(AssignmentModel assignmentModel,
      MultipartFile[] files) {
    String assignmentFilesMap = "";
    String key = "";
    String url = "";
    String extension = "";
    String fileName = "";

    for (MultipartFile file : files) {
      extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

      fileName = file.getOriginalFilename().replace("." + extension, "").replace(" ", "_");

      key = String.format("assignments/%s/indicationsFiles/%s.%s", assignmentModel.getId(), fileName,
          extension);

      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentType(file.getContentType());

      try {
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, file.getInputStream(),
            metadata).withCannedAcl(CannedAccessControlList.PublicRead);

        s3Client.putObject(putObjectRequest);
        url = String.format("https://%s.s3.amazonaws.com/%s", BUCKET_NAME, key);

        if (assignmentFilesMap.equals("")) {
          assignmentFilesMap += url;
        } else {
          assignmentFilesMap += ", " + url;
        }

      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }
    return assignmentFilesMap;
  }

}