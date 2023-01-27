package com.secondpartial.platformreplica.services;

import java.io.IOException;
import java.util.LinkedHashMap;

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
        LinkedHashMap<String, String> userImageMap = new LinkedHashMap<>();
        String extension = StringUtils.getFilenameExtension(userImage.getOriginalFilename());
        String key = String.format("users/%s/userImage/%s_img.%s", userModel.getId(), userModel.getId(), extension);
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
}
