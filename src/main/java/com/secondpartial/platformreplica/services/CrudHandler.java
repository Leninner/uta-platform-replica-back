package com.secondpartial.platformreplica.services;

import java.util.LinkedHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.secondpartial.platformreplica.dtos.CourseCreationDTO;
import com.secondpartial.platformreplica.dtos.CourseUpdateDTO;
import com.secondpartial.platformreplica.dtos.TeacherUpdateDTO;
import com.secondpartial.platformreplica.dtos.UserDTO;
import com.secondpartial.platformreplica.dtos.UserModifyDTO;

public abstract class CrudHandler {
    public ResponseEntity<LinkedHashMap<String, Object>> create(UserDTO user) {
        return null;
    }

    public ResponseEntity<LinkedHashMap<String, Object>> create(CourseCreationDTO course, String rol) {
        return null;
    }

    public ResponseEntity<LinkedHashMap<String, Object>> get(Long id, String token) {
        return null;
    }

    public ResponseEntity<LinkedHashMap<String, Object>> get(Long id) {
        return null;
    }

    public ResponseEntity<LinkedHashMap<String, Object>> update(Long id, UserModifyDTO user,
            String token, MultipartFile userImage, String rol) {
        return null;
    }

    public ResponseEntity<LinkedHashMap<String, Object>> update(CourseUpdateDTO course, String rol) {
        return null;
    }

    public ResponseEntity<LinkedHashMap<String, Object>> update(Long id, TeacherUpdateDTO teacher, String rol) {
        return null;
    }

    public ResponseEntity<LinkedHashMap<String, Object>> delete(Long id, String rol, String token) {
        return null;
    }

    public ResponseEntity<LinkedHashMap<String, Object>> delete(Long id, String rol) {
        return null;
    }

    public ResponseEntity<LinkedHashMap<String, Object>> delete(Long id) {
        return null;
    }
}
