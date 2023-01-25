package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.AssignmentCreationDTO;
import com.secondpartial.platformreplica.dtos.AssignmentResponseDTO;
import com.secondpartial.platformreplica.dtos.AssignmentStudentResponseDTO;
import com.secondpartial.platformreplica.enums.PartialEnum;
import com.secondpartial.platformreplica.enums.StatusEnum;
import com.secondpartial.platformreplica.models.AssignmentModel;
import com.secondpartial.platformreplica.models.AssignmentStudentModel;
import com.secondpartial.platformreplica.models.CourseModel;
import com.secondpartial.platformreplica.models.StudentModel;
import com.secondpartial.platformreplica.repositories.AssignmentRepository;
import com.secondpartial.platformreplica.repositories.AssignmentStudentRepository;
import com.secondpartial.platformreplica.repositories.CourseRepository;
import com.secondpartial.platformreplica.repositories.StudentRepository;

@Service
public class AssignmentService {

  @Autowired
  AssignmentRepository assignmentRepository;

  @Autowired
  CourseRepository courseRepository;

  @Autowired
  StudentRepository studentRepository;

  @Autowired
  AssignmentStudentRepository assignmentStudentRepository;

  public ResponseEntity<HashMap<String, Object>> createAssignment(AssignmentCreationDTO assignment) {
    HashMap<String, Object> response = new HashMap<>();

    CourseModel course = courseRepository.findById(assignment.getCourseId()).orElse(null);

    List<StudentModel> students = course.getStudents();

    AssignmentModel newAssignment = new AssignmentModel();
    newAssignment.setName(assignment.getName());
    newAssignment.setDescription(assignment.getDescription());
    newAssignment.setPartial(PartialEnum.valueOf(assignment.getPartial()));
    newAssignment.setDateInit(Timestamp.valueOf(assignment.getDateInit()));
    newAssignment.setDateEnd(Timestamp.valueOf(assignment.getDateEnd()));
    newAssignment.setCourse(course);
    newAssignment.setStatus(StatusEnum.valueOf(assignment.getStatus()));
    newAssignment.setIndicationsFile(assignment.getIndicationsFile());

    ArrayList<StudentModel> studentsToAdd = new ArrayList<>();
    for (StudentModel student : students) {
      studentsToAdd.add(student);
    }

    assignmentRepository.save(newAssignment);

    newAssignment.setStudents(studentsToAdd);

    assignmentRepository.save(newAssignment);

    response.put("message", "Assignment created successfully");
    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<HashMap<String, Object>> getAssignmentsByCourseId(Long courseId) {
    HashMap<String, Object> response = new HashMap<>();

    CourseModel course = courseRepository.findById(courseId).orElse(null);

    List<AssignmentModel> assignments = course.getAssignments();
    List<AssignmentResponseDTO> assignmentsResponse = new ArrayList<>();

    for (AssignmentModel assignment : assignments) {
      AssignmentResponseDTO assignmentResponse = new AssignmentResponseDTO();
      assignmentResponse.setId(assignment.getId());
      assignmentResponse.setName(assignment.getName());
      assignmentResponse.setDescription(assignment.getDescription());
      assignmentResponse.setPartial(assignment.getPartial().toString());
      assignmentResponse.setDateInit(assignment.getDateInit().toString());
      assignmentResponse.setDateEnd(assignment.getDateEnd().toString());
      assignmentResponse.setStatus(assignment.getStatus().toString());
      assignmentsResponse.add(assignmentResponse);
    }

    response.put("assignments", assignmentsResponse);
    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<HashMap<String, Object>> modifyAssignment(Long assigmentId,
      AssignmentCreationDTO assignmentCreationDTO) {
    HashMap<String, Object> response = new HashMap<>();

    AssignmentModel assignment = assignmentRepository.getReferenceById(assigmentId);

    if (assignment == null) {
      response.put("message", "Assignment not found");
      return ResponseEntity.badRequest().body(response);
    }

    if (assignmentCreationDTO.getName() != null) {
      assignment.setName(assignmentCreationDTO.getName());
    }

    if (assignmentCreationDTO.getDescription() != null) {
      assignment.setDescription(assignmentCreationDTO.getDescription());
    }

    if (assignmentCreationDTO.getPartial() != null) {
      assignment.setPartial(PartialEnum.valueOf(assignmentCreationDTO.getPartial()));
    }

    if (assignmentCreationDTO.getDateInit() != null) {
      assignment.setDateInit(Timestamp.valueOf(assignmentCreationDTO.getDateInit()));
    }

    if (assignmentCreationDTO.getDateEnd() != null) {
      assignment.setDateEnd(Timestamp.valueOf(assignmentCreationDTO.getDateEnd()));
    }

    if (assignmentCreationDTO.getStatus() != null) {
      assignment.setStatus(StatusEnum.valueOf(assignmentCreationDTO.getStatus()));
    }

    if (assignmentCreationDTO.getIndicationsFile() != null) {
      assignment.setIndicationsFile(assignmentCreationDTO.getIndicationsFile());
    }

    assignmentRepository.save(assignment);

    response.put("message", "Assignment modified successfully");

    HashMap<String, String> assignmentResponse = new HashMap<>();
    assignmentResponse.put("id", assignment.getId().toString());
    assignmentResponse.put("name", assignment.getName());
    assignmentResponse.put("description", assignment.getDescription());
    assignmentResponse.put("partial", assignment.getPartial().toString());
    assignmentResponse.put("dateInit", assignment.getDateInit().toString());
    assignmentResponse.put("dateEnd", assignment.getDateEnd().toString());
    assignmentResponse.put("status", assignment.getStatus().toString());
    assignmentResponse.put("indicationsFile", assignment.getIndicationsFile());

    response.put("assignment", assignmentResponse);

    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);

  }

  public ResponseEntity<HashMap<String, Object>> deleteAssignment(Long assignmentId) {
    HashMap<String, Object> response = new HashMap<>();

    AssignmentModel assignment = assignmentRepository.getReferenceById(assignmentId);

    if (assignment == null) {
      response.put("message", "Assignment not found");
      return ResponseEntity.badRequest().body(response);
    }

    assignmentRepository.delete(assignment);

    response.put("message", "Assignment deleted successfully");
    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<HashMap<String, Object>> getAssignmentById(Long assignmentId) {
    HashMap<String, Object> response = new HashMap<>();

    AssignmentModel assignment = assignmentRepository.getReferenceById(assignmentId);

    if (assignment == null) {
      response.put("message", "Assignment not found");
      return ResponseEntity.badRequest().body(response);
    }

    HashMap<String, String> assignmentResponse = new HashMap<>();
    assignmentResponse.put("id", assignment.getId().toString());
    assignmentResponse.put("name", assignment.getName());
    assignmentResponse.put("description", assignment.getDescription());
    assignmentResponse.put("partial", assignment.getPartial().toString());
    assignmentResponse.put("dateInit", assignment.getDateInit().toString());
    assignmentResponse.put("dateEnd", assignment.getDateEnd().toString());
    assignmentResponse.put("status", assignment.getStatus().toString());
    assignmentResponse.put("indicationsFile", assignment.getIndicationsFile());

    response.put("assignment", assignmentResponse);

    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<HashMap<String, Object>> getAssignmentByStudentIdAndAssignmentId(Long studentId,
      Long assignmentId) {
    HashMap<String, Object> response = new HashMap<>();

    StudentModel student = studentRepository.getReferenceById(studentId);

    if (student == null) {
      response.put("message", "Student not found");
      return ResponseEntity.badRequest().body(response);
    }

    AssignmentModel assignment = assignmentRepository.getReferenceById(assignmentId);
    AssignmentStudentModel assignmentStudent = assignmentStudentRepository
        .findByStudentAndAssignment(student, assignment);

    if (assignmentStudent == null) {
      response.put("message", "Assignment not found");
      return ResponseEntity.badRequest().body(response);
    }

    AssignmentStudentResponseDTO assignmentStudentResponse = new AssignmentStudentResponseDTO();
    assignmentStudentResponse.setName(assignment.getName());
    assignmentStudentResponse.setDescription(assignment.getDescription());
    assignmentStudentResponse.setPartial(assignment.getPartial().toString());
    assignmentStudentResponse.setDateInit(assignment.getDateInit().toString());
    assignmentStudentResponse.setDateEnd(assignment.getDateEnd().toString());
    assignmentStudentResponse.setStatus(assignment.getStatus().toString());
    assignmentStudentResponse.setIndicationsFile(assignment.getIndicationsFile());
    assignmentStudentResponse.setGrade(assignmentStudent.getGrade());
    assignmentStudentResponse.setStudentFile(assignmentStudent.getStudentFile());
    assignmentStudentResponse.setComment(assignmentStudent.getComment());

    response.put("assignment", assignmentStudentResponse);

    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<HashMap<String, Object> setAssignmentStudentGrade

}
