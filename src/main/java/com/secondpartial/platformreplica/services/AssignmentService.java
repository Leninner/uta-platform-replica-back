package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.secondpartial.platformreplica.dtos.AssignmentCreationDTO;
import com.secondpartial.platformreplica.dtos.AssignmentResponseDTO;
import com.secondpartial.platformreplica.dtos.AssignmentStudentResponseDTO;
import com.secondpartial.platformreplica.enums.PartialEnum;
import com.secondpartial.platformreplica.enums.RolEnum;
import com.secondpartial.platformreplica.enums.StatusEnum;
import com.secondpartial.platformreplica.models.AssignmentModel;
import com.secondpartial.platformreplica.models.AssignmentStudentModel;
import com.secondpartial.platformreplica.models.CourseModel;
import com.secondpartial.platformreplica.models.StudentModel;
import com.secondpartial.platformreplica.repositories.AssignmentRepository;
import com.secondpartial.platformreplica.repositories.AssignmentStudentRepository;
import com.secondpartial.platformreplica.repositories.CourseRepository;
import com.secondpartial.platformreplica.repositories.StudentRepository;
import com.secondpartial.platformreplica.utils.DateUtil;

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

  @Autowired
  S3Service s3Service;

  DateUtil dateUtil = new DateUtil();

  public ResponseEntity<LinkedHashMap<String, Object>> createAssignment(String rol, AssignmentCreationDTO assignment,
      MultipartFile[] files) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    System.out.println(assignment.toString());

    if (!rol.equals(RolEnum.TEACHER.toString())) {
      response.put("message", "You don't have permission to create an assignment");
      return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    CourseModel course = courseRepository.findById(assignment.getCourseId()).orElse(null);

    List<StudentModel> students = course.getStudents();

    AssignmentModel newAssignment = new AssignmentModel();
    newAssignment.setName(assignment.getName());
    newAssignment.setDescription(assignment.getDescription());
    newAssignment.setPartial(PartialEnum.valueOf(assignment.getPartial()));
    newAssignment.setDateInit(Timestamp.valueOf(dateUtil.transformWebDateToDBDate(assignment.getDateInit())));
    newAssignment.setDateEnd(Timestamp.valueOf(dateUtil.transformWebDateToDBDate(assignment.getDateEnd())));
    newAssignment.setCourse(course);

    Timestamp now = new Timestamp(System.currentTimeMillis());
    if (newAssignment.getDateInit().before(now)) {
      newAssignment.setStatus(StatusEnum.AVAILABLE);
    } else {
      newAssignment.setStatus(StatusEnum.UNAVAILABLE);
    }

    ArrayList<StudentModel> studentsToAdd = new ArrayList<>();
    for (StudentModel student : students) {
      studentsToAdd.add(student);
    }

    assignmentRepository.save(newAssignment);

    newAssignment.setStudents(studentsToAdd);

    newAssignment.setIndicationsFiles(s3Service.setAssignmentFiles(newAssignment, files));

    assignmentRepository.save(newAssignment);

    AssignmentResponseDTO assignmentResponse = new AssignmentResponseDTO();
    assignmentResponse.setId(newAssignment.getId());
    assignmentResponse.setName(newAssignment.getName());
    assignmentResponse.setDescription(newAssignment.getDescription());
    assignmentResponse.setPartial(newAssignment.getPartial().toString());
    assignmentResponse.setDateInit(newAssignment.getDateInit().toString());
    assignmentResponse.setDateEnd(newAssignment.getDateEnd().toString());
    assignmentResponse.setStatus(newAssignment.getStatus().toString());
    assignmentResponse.setIndicationsFiles(newAssignment.getIndicationsFiles());

    response.put("assignment", assignmentResponse);

    response.put("message", "Assignment created successfully");
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<LinkedHashMap<String, Object>> getAssignmentsByCourseId(Long courseId) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

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
      assignmentResponse.setIndicationsFiles(assignment.getIndicationsFiles());
      assignmentsResponse.add(assignmentResponse);
    }

    response.put("assignments", assignmentsResponse);
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<LinkedHashMap<String, Object>> modifyAssignment(String rol, Long assigmentId,
      AssignmentCreationDTO assignmentCreationDTO, MultipartFile[] files) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    if (!rol.equals(RolEnum.TEACHER.toString())) {
      response.put("message", "You don't have permission to modify an assignment");
      return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

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
      assignment.setDateInit(Timestamp.valueOf(dateUtil.transformWebDateToDBDate(assignmentCreationDTO.getDateInit())));
    }

    if (assignmentCreationDTO.getDateEnd() != null) {
      assignment.setDateEnd(Timestamp.valueOf(dateUtil.transformWebDateToDBDate(assignmentCreationDTO.getDateEnd())));
    }

    if (assignmentCreationDTO.getStatus() != null) {
      assignment.setStatus(StatusEnum.valueOf(assignmentCreationDTO.getStatus()));
    }

    if (assignmentCreationDTO.getIndicationsFiles() != null) {
      assignment.setIndicationsFiles(s3Service.setAssignmentFiles(assignment, files));
    }

    assignmentRepository.save(assignment);

    response.put("message", "Assignment modified successfully");

    LinkedHashMap<String, Object> assignmentResponse = new LinkedHashMap<>();
    assignmentResponse.put("id", assignment.getId().toString());
    assignmentResponse.put("name", assignment.getName());
    assignmentResponse.put("description", assignment.getDescription());
    assignmentResponse.put("partial", assignment.getPartial().toString());
    assignmentResponse.put("dateInit", assignment.getDateInit().toString());
    assignmentResponse.put("dateEnd", assignment.getDateEnd().toString());
    assignmentResponse.put("status", assignment.getStatus().toString());
    assignmentResponse.put("indicationsFiles", assignment.getIndicationsFiles());

    response.put("assignment", assignmentResponse);

    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);

  }

  public ResponseEntity<LinkedHashMap<String, Object>> deleteAssignment(String rol, Long assignmentId) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    if (!rol.equals(RolEnum.TEACHER.toString())) {
      response.put("message", "You don't have permission to delete an assignment");
      return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    AssignmentModel assignment = assignmentRepository.getReferenceById(assignmentId);

    if (assignment == null) {
      response.put("message", "Assignment not found");
      return ResponseEntity.badRequest().body(response);
    }

    assignmentRepository.delete(assignment);

    response.put("message", "Assignment deleted successfully");
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<LinkedHashMap<String, Object>> getAssignmentById(Long assignmentId) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    AssignmentModel assignment = assignmentRepository.getReferenceById(assignmentId);

    if (assignment == null) {
      response.put("message", "Assignment not found");
      return ResponseEntity.badRequest().body(response);
    }

    LinkedHashMap<String, Object> assignmentResponse = new LinkedHashMap<>();
    assignmentResponse.put("id", assignment.getId().toString());
    assignmentResponse.put("name", assignment.getName());
    assignmentResponse.put("description", assignment.getDescription());
    assignmentResponse.put("partial", assignment.getPartial().toString());
    assignmentResponse.put("dateInit", assignment.getDateInit().toString());
    assignmentResponse.put("dateEnd", assignment.getDateEnd().toString());
    assignmentResponse.put("status", assignment.getStatus().toString());
    assignmentResponse.put("indicationsFiles", assignment.getIndicationsFiles());

    response.put("assignment", assignmentResponse);

    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<LinkedHashMap<String, Object>> getAssignmentByStudentIdAndAssignmentId(
      Long assignmentId, Long studentId) {

    System.out.println("studentId: " + studentId);
    System.out.println("assignmentId: " + assignmentId);
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    AssignmentModel assignment = assignmentRepository.getReferenceById(assignmentId);

    if (assignment == null) {
      response.put("message", "Assignment not found xd");
      return ResponseEntity.badRequest().body(response);
    }

    AssignmentStudentModel assignmentStudent = assignmentStudentRepository
        .findByStudentAndAssignment(assignmentId, studentId);

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
    assignmentStudentResponse.setIndicationsFiles(assignment.getIndicationsFiles());

    assignmentStudentResponse.setGrade(assignmentStudent.getGrade());
    assignmentStudentResponse.setStudentFiles(assignmentStudent.getStudentFiles());
    assignmentStudentResponse.setComment(assignmentStudent.getComment());

    response.put("assignment", assignmentStudentResponse);

    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<LinkedHashMap<String, Object>> setAssignmentStudentGrade(String rol, Long studentId,
      Long assignmentId, double grade) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();
    if (!rol.equals(RolEnum.TEACHER.toString())) {
      response.put("message", "You don't have permission to do this action");
      return ResponseEntity.badRequest().body(response);
    }

    AssignmentStudentModel assignmentStudent = assignmentStudentRepository
        .findByStudentAndAssignment(studentId, assignmentId);

    if (assignmentStudent == null) {
      response.put("message", "Assignment not found");
      return ResponseEntity.badRequest().body(response);
    }

    AssignmentModel assignment = assignmentRepository.getReferenceById(assignmentId);

    assignmentStudent.setGrade(grade);
    assignmentStudentRepository.save(assignmentStudent);

    AssignmentStudentResponseDTO assignmentStudentResponse = new AssignmentStudentResponseDTO();
    assignmentStudentResponse.setName(assignment.getName());
    assignmentStudentResponse.setDescription(assignment.getDescription());
    assignmentStudentResponse.setPartial(assignment.getPartial().toString());
    assignmentStudentResponse.setDateInit(assignment.getDateInit().toString());
    assignmentStudentResponse.setDateEnd(assignment.getDateEnd().toString());
    assignmentStudentResponse.setStatus(assignment.getStatus().toString());
    assignmentStudentResponse.setIndicationsFiles(assignment.getIndicationsFiles());
    assignmentStudentResponse.setGrade(assignmentStudent.getGrade());
    assignmentStudentResponse.setStudentFiles(assignmentStudent.getStudentFiles());
    assignmentStudentResponse.setComment(assignmentStudent.getComment());

    response.put("message", "Assignment grade set successfully");

    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<LinkedHashMap<String, Object>> updateStudentFiles(String rol,
      Long assignmentId,
      Long studentId, List<MultipartFile> files) {

    System.out.println("rol: " + rol);
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();
    String url;

    if (!rol.equals(RolEnum.STUDENT.toString())) {
      response.put("message", "You don't have permission to do this action");
      return ResponseEntity.badRequest().body(response);
    }

    AssignmentModel assignment = assignmentRepository.getReferenceById(assignmentId);

    AssignmentStudentModel assignmentStudent = assignmentStudentRepository.findByStudentAndAssignment(assignmentId,
        studentId);

    if (assignmentStudent == null) {
      response.put("message", "Assignment not found");
      return ResponseEntity.badRequest().body(response);
    }

    if (assignment.getStatus().equals(StatusEnum.UNAVAILABLE)) {
      return ResponseEntity.badRequest().body(response);
    }
    url = s3Service.setStudentFiles(assignmentStudent, files);
    assignmentStudent.setStudentFiles(url);

    assignmentStudentRepository.save(assignmentStudent);

    AssignmentStudentResponseDTO assignmentStudentResponse = new AssignmentStudentResponseDTO();
    assignmentStudentResponse.setName(assignment.getName());
    assignmentStudentResponse.setDescription(assignment.getDescription());
    assignmentStudentResponse.setPartial(assignment.getPartial().toString());
    assignmentStudentResponse.setDateInit(assignment.getDateInit().toString());
    assignmentStudentResponse.setDateEnd(assignment.getDateEnd().toString());
    assignmentStudentResponse.setStatus(assignment.getStatus().toString());
    assignmentStudentResponse.setIndicationsFiles(assignment.getIndicationsFiles());
    assignmentStudentResponse.setGrade(assignmentStudent.getGrade());
    assignmentStudentResponse.setStudentFiles(assignmentStudent.getStudentFiles());
    assignmentStudentResponse.setComment(assignmentStudent.getComment());

    response.put("message", "Files uploaded successfully:");
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

}
