package com.secondpartial.platformreplica.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.secondpartial.platformreplica.dtos.enrollment.*;
import com.secondpartial.platformreplica.services.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
  @Autowired
  EnrollmentService enrollmentService;

  @PostMapping()
  public ResponseEntity<HashMap<String, Object>> enrollStudentToCourse(
      @RequestBody EnrollStudentDTO enrollStudentDTO) {
        System.out.println("enrollStudentDTO: " + enrollStudentDTO);
    return enrollmentService.enrollStudentToCourse(enrollStudentDTO);
  }
}
