package com.secondpartial.platformreplica.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondpartial.platformreplica.dtos.AssignmentCreationDTO;
import com.secondpartial.platformreplica.dtos.AssignmentStudentModifyDTO;
import com.secondpartial.platformreplica.dtos.SetGradeDTO;
import com.secondpartial.platformreplica.services.AssignmentService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
  @Autowired
  AssignmentService assignmentService;

  @GetMapping(path = "/course/{courseId}/user/{userId}")
  public ResponseEntity<LinkedHashMap<String, Object>> getAssignmentsByCourseId(
      @PathVariable("courseId") Long courseId, @PathVariable("userId") Long userId, @RequestHeader String rol) {
    return assignmentService.getAssignmentsByCourseId(courseId, userId, rol);
  }

  @PutMapping(value = "modify/{id}", consumes = { "multipart/form-data" })
  public ResponseEntity<LinkedHashMap<String, Object>> modifyAssignment(@RequestHeader String rol,
      @PathVariable("id") Long id,
      @ModelAttribute AssignmentCreationDTO assignment) {
    return assignmentService.modifyAssignment(rol, id, assignment, assignment.getIndicationsFiles());
  }

  @DeleteMapping(value = "delete/{id}")
  public ResponseEntity<LinkedHashMap<String, Object>> deleteAssignment(@RequestHeader String rol,
      @PathVariable("id") Long id) {
    return assignmentService.delete(rol, id);
  }

  @GetMapping(path = "/{assignmentId}/student/{studentId}")
  public ResponseEntity<LinkedHashMap<String, Object>> getAssignmentByStudentIdAndAssignmentId(
      @PathVariable("assignmentId") Long assignmentId,
      @PathVariable("studentId") Long studentId) {
    return assignmentService.getAssignmentByStudentIdAndAssignmentId(assignmentId,
        studentId);
  }

  @PutMapping(value = "/{assignmentId}/student/{studentId}")
  public ResponseEntity<LinkedHashMap<String, Object>> setAssignmentStudentGrade(
      @RequestHeader("role") String role,
      @PathVariable("assignmentId") Long assignmentId,
      @PathVariable("studentId") Long studentId,
      @RequestBody SetGradeDTO gradeSummary) {
        System.out.println("Prueba " + gradeSummary.getGrade());
    return assignmentService.setAssignmentStudentGrade(role, assignmentId, studentId, Double.parseDouble(gradeSummary.getGrade()));
  }

  @PostMapping(consumes = { "multipart/form-data" })
  public ResponseEntity<LinkedHashMap<String, Object>> createAssignment(@RequestHeader(value = "rol") String rol,
      @ModelAttribute AssignmentCreationDTO assignment) {
    return assignmentService.create(rol, assignment, assignment.getIndicationsFiles());
  }

  @PutMapping(value = "/{assignmentId}/student/{studentId}", consumes = { "multipart/form-data" })
  public ResponseEntity<LinkedHashMap<String, Object>> updateStudentFiles(
      @PathVariable("assignmentId") Long assignmentId,
      @PathVariable("studentId") Long studentId,
      @ModelAttribute AssignmentStudentModifyDTO assignment) {
    System.out.println("Prueba");
    return assignmentService.updateStudentFiles(assignmentId, studentId, assignment.getStudentFiles());
  }

}
