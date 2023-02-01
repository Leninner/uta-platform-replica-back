package com.secondpartial.platformreplica.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondpartial.platformreplica.services.CareerService;

@RestController
@RequestMapping("/careers")
public class CareerController {
    @Autowired
    CareerService careerService;

    @GetMapping()
    public ResponseEntity<LinkedHashMap<String, LinkedHashMap<String, Object>>> getAllCarreersWithSemestersAndCourses() {
        return careerService.getAllCarreersWithSemestersAndCourses();
    }

}
