package com.secondpartial.platformreplica.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondpartial.platformreplica.dtos.AssignmentCreationDTO;
import com.secondpartial.platformreplica.services.AssignmentService;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
  @Autowired
  AssignmentService assignmentService;

  @PostMapping()
  public ResponseEntity<HashMap<String, Object>> createAssignment(@RequestBody AssignmentCreationDTO assignment) {
    return assignmentService.createAssignment(assignment);
  }
}
