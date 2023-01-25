package com.secondpartial.platformreplica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.secondpartial.platformreplica.services.FirebaseService;

@RestController
public class FirebaseController {

    @Autowired
    private FirebaseService firebaseService;

    /**
     * this method is used to post/upload files into the google cloud firebase
     * @param file variable shall contain multipart file to be uploaded
     * @return success message on uploading the file
     * @throws Exception
     */
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        return firebaseService.uploadFile(file);
    }

    @PostMapping("/upload-user-image/{id}")
    public ResponseEntity<String> uploadUserImage(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) throws Exception {
        return firebaseService.uploadUserImage(file, id);
    }

}