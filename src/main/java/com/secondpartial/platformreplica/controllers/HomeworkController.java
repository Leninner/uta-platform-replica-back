package com.secondpartial.platformreplica.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondpartial.platformreplica.dtos.HomeworkCreationDTO;
import com.secondpartial.platformreplica.services.HomeworkService;

@RestController
@RequestMapping("/homeworks")
public class HomeworkController {
    @Autowired
    HomeworkService homeworkService;

    @PostMapping()
    public ResponseEntity<HashMap<String, Object>> createHomework(@RequestBody HomeworkCreationDTO homework) {
        return homeworkService.createHomework(homework);
    }
}
