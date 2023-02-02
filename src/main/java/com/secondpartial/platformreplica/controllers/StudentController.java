package com.secondpartial.platformreplica.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondpartial.platformreplica.dtos.StudentUpdateDTO;
import com.secondpartial.platformreplica.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PutMapping("/{id}")
    public ResponseEntity<LinkedHashMap<String, Object>> Update(@PathVariable("id") Long id,
            @ModelAttribute StudentUpdateDTO student, @RequestHeader(value = "Authorization") String token,
            @RequestHeader String rol) {
        return studentService.update(token, id, student, rol);
    }
}
