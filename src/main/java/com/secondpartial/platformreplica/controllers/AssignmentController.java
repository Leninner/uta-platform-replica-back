package com.secondpartial.platformreplica.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondpartial.platformreplica.dtos.AssignmentCreationDTO;
import com.secondpartial.platformreplica.services.AssignmentService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
  @Autowired
  AssignmentService assignmentService;

  @PostMapping()
  public ResponseEntity<LinkedHashMap<String, Object>> createAssignment(@RequestBody AssignmentCreationDTO assignment) {
    return assignmentService.createAssignment(assignment);
  }

  @GetMapping(path = "/course/{courseId}")
  public ResponseEntity<LinkedHashMap<String, Object>> getAssignmentsByCourseId(
      @PathVariable("courseId") Long courseId) {
    return assignmentService.getAssignmentsByCourseId(courseId);
  }

  @PutMapping(value = "modify/{id}")
  public ResponseEntity<LinkedHashMap<String, Object>> modifyAssignment(@PathVariable("id") Long id,
      @RequestBody AssignmentCreationDTO assignment) {
    return assignmentService.modifyAssignment(id, assignment);
  }

  @PutMapping(value = "delete/{id}")
  public ResponseEntity<LinkedHashMap<String, Object>> deleteAssignment(@PathVariable("id") Long id) {
    return assignmentService.deleteAssignment(id);
  }

  @GetMapping(path = "/{assignmentId}/studen/{studentId}")
  public ResponseEntity<LinkedHashMap<String, Object>> getAssignmentByStudentIdAndAssignmentId(
      @PathVariable("assignmentId") Long assignmentId,
      @PathVariable("studentId") Long studentId) {
    return assignmentService.getAssignmentByStudentIdAndAssignmentId(assignmentId, studentId);
  }

}
