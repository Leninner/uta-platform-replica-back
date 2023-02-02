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
import com.secondpartial.platformreplica.dtos.AssignmentStudentAllResponseDTO;
import com.secondpartial.platformreplica.dtos.AssignmentStudentResponseDTO;
import com.secondpartial.platformreplica.dtos.AssignmentTeacherResponseDTO;
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

  public ResponseEntity<LinkedHashMap<String, Object>> create(String rol, AssignmentCreationDTO assignment,
      MultipartFile[] files) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    System.out.println(assignment.toString());

    if (!rol.equals(RolEnum.TEACHER.toString())) {
      response.put("message", "You don't have permission to create an assignment");
      return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    CourseModel course = courseRepository.findById(assignment.getCourseId()).orElse(null);

    if (course == null) {
      response.put("message", "Course not found");
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    List<StudentModel> students = course.getStudents();

    try {

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

      if (files != null) {
        newAssignment.setIndicationsFiles(s3Service.setAssignmentFiles(newAssignment, files));
      } else {
        newAssignment.setIndicationsFiles(null);
      }

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

    } catch (Exception e) {
      response.put("message", "Error creating assignment");
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<LinkedHashMap<String, Object>> getAssignmentsByCourseId(Long courseId, Long userId,
      String rol) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    CourseModel course = courseRepository.findById(courseId).orElse(null);

    List<AssignmentModel> assignments = course.getAssignments();
    ArrayList<AssignmentTeacherResponseDTO> assignmentsResponse = new ArrayList<>();
    ArrayList<AssignmentStudentAllResponseDTO> assignmentsStudentResponse = new ArrayList<>();

    for (AssignmentModel assignment : assignments) {

      if (rol.equals(RolEnum.STUDENT.toString())) {
        StudentModel student = studentRepository.findByUserId(userId);
        AssignmentStudentModel assignmentStudent = assignmentStudentRepository
            .findByStudentAndAssignment(assignment.getId(), student.getId());

        if (assignmentStudent == null) {
          response.put("message", "Assignment not found");
          // return ResponseEntity.badRequest().body(response);
        }

        AssignmentStudentAllResponseDTO assignmentStudentAllResponse = new AssignmentStudentAllResponseDTO();
        assignmentStudentAllResponse.setId(assignment.getId());
        assignmentStudentAllResponse.setStudentName(studentRepository.findByUserId(userId).getUser().getName());
        assignmentStudentAllResponse.setGrade(assignmentStudent.getGrade());
        assignmentStudentAllResponse.setStudentFiles(assignmentStudent.getStudentFiles());
        assignmentStudentAllResponse.setIsCompleted(assignmentStudent.getIsCompleted());
        assignmentStudentAllResponse.setComment(assignmentStudent.getComment());
        assignmentStudentAllResponse.setAssignmentName(assignment.getName());
        assignmentStudentAllResponse.setDescription(assignment.getDescription());
        assignmentStudentAllResponse.setPartial(assignment.getPartial().toString());
        assignmentStudentAllResponse.setDateInit(assignment.getDateInit().toString());
        assignmentStudentAllResponse.setDateEnd(assignment.getDateEnd().toString());
        assignmentStudentAllResponse.setStatus(assignment.getStatus().toString());
        assignmentStudentAllResponse.setIndicationsFiles(assignment.getIndicationsFiles());

        assignmentsStudentResponse.add(assignmentStudentAllResponse);
        response.put("assignments", assignmentsStudentResponse);
      }

      if (rol.equals(RolEnum.TEACHER.toString())) {
        List<AssignmentStudentResponseDTO> assignmentStudentsResponseDTO = new ArrayList<>();

        List<StudentModel> students = courseRepository.findById(courseId).orElse(course).getStudents();

        AssignmentTeacherResponseDTO assignmentTeacherResponse = new AssignmentTeacherResponseDTO();
        assignmentTeacherResponse.setId(assignment.getId());
        assignmentTeacherResponse.setAssignmentName(assignment.getName());
        assignmentTeacherResponse.setDescription(assignment.getDescription());
        assignmentTeacherResponse.setPartial(assignment.getPartial().toString());
        assignmentTeacherResponse.setDateInit(assignment.getDateInit().toString());
        assignmentTeacherResponse.setDateEnd(assignment.getDateEnd().toString());
        assignmentTeacherResponse.setStatus(assignment.getStatus().toString());
        assignmentTeacherResponse.setIndicationsFiles(assignment.getIndicationsFiles());

        for (StudentModel student : students) {
          AssignmentStudentModel assignmentStudent = assignmentStudentRepository.findByStudentAndAssignment(
              assignment.getId(),
              student.getId());

          AssignmentStudentResponseDTO assignmentStudentResponse = new AssignmentStudentResponseDTO();

          assignmentStudentResponse.setStudentName(student.getUser().getName());
          assignmentStudentResponse.setGrade(assignmentStudent.getGrade());
          assignmentStudentResponse.setStudentFiles(assignmentStudent.getStudentFiles());
          assignmentStudentResponse.setIsCompleted(assignmentStudent.getIsCompleted());
          assignmentStudentResponse.setComment(assignmentStudent.getComment());
          assignmentStudentResponse.setId(student.getId());

          assignmentStudentsResponseDTO.add(assignmentStudentResponse);
        }

        assignmentTeacherResponse.setStudents(assignmentStudentsResponseDTO);
        assignmentsResponse.add(assignmentTeacherResponse);
        response.put("assignments", assignmentsResponse);
      }    
    }

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

  public ResponseEntity<LinkedHashMap<String, Object>> delete(String rol, Long assignmentId) {
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

    List<AssignmentStudentModel> assignmentStudents = assignmentStudentRepository.findByAssignment(assignmentId);

    for (AssignmentStudentModel assignmentStudent : assignmentStudents) {
      assignmentStudentRepository.delete(assignmentStudent);
    }

    assignmentRepository.delete(assignment);

    response.put("message", "Assignment deleted successfully");
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<LinkedHashMap<String, Object>> getAssignmentById(Long assignmentId, Long userId, String rol) {
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

    assignmentStudent.setGrade(grade);
    assignmentStudentRepository.save(assignmentStudent);

    response.put("message", "Assignment grade set successfully");

    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<LinkedHashMap<String, Object>> updateStudentFiles(
      Long assignmentId,
      Long studentId, List<MultipartFile> files) {

    System.out.println("studentId: " + studentId);
    System.out.println("assignmentId: " + assignmentId);
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();
    String url;

    AssignmentModel assignment = assignmentRepository.getReferenceById(assignmentId);

    AssignmentStudentModel assignmentStudent = assignmentStudentRepository.findByStudentAndAssignment(assignmentId,
        studentId);

    if (assignmentStudent == null) {
      System.out.println("assignmentStudent == null");
      response.put("message", "Assignment not found");
      return ResponseEntity.badRequest().body(response);
    }

    if (assignment.getStatus().equals(StatusEnum.UNAVAILABLE)) {
      return ResponseEntity.badRequest().body(response);
    }
    url = s3Service.setStudentFiles(assignmentStudent, files);
    assignmentStudent.setStudentFiles(url);
    assignmentStudent.setIsCompleted(true);

    assignmentStudentRepository.save(assignmentStudent);

    AssignmentStudentResponseDTO assignmentStudentResponse = new AssignmentStudentResponseDTO();
    assignmentStudentResponse.setGrade(assignmentStudent.getGrade());
    assignmentStudentResponse.setStudentFiles(assignmentStudent.getStudentFiles());
    assignmentStudentResponse.setComment(assignmentStudent.getComment());
    assignmentStudentResponse.setIsCompleted(assignmentStudent.getIsCompleted());

    response.put("assignmentStudent", assignmentStudentResponse);

    response.put("message", "Files uploaded successfully:");
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<LinkedHashMap<String, Object>> getAssignmentByStudentIdAndAssignmentId(Long assignmentId,
      Long studentId) {
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

    AssignmentStudentAllResponseDTO assignmentStudentAllResponse = new AssignmentStudentAllResponseDTO();
    assignmentStudentAllResponse.setAssignmentName(assignment.getName());
    assignmentStudentAllResponse.setDescription(assignment.getDescription());
    assignmentStudentAllResponse.setPartial(assignment.getPartial().toString());
    assignmentStudentAllResponse.setDateInit(assignment.getDateInit().toString());
    assignmentStudentAllResponse.setDateEnd(assignment.getDateEnd().toString());
    assignmentStudentAllResponse.setStatus(assignment.getStatus().toString());
    assignmentStudentAllResponse.setIndicationsFiles(assignment.getIndicationsFiles());

    assignmentStudentAllResponse.setStudentName(studentRepository.getReferenceById(studentId).getUser().getName());
    assignmentStudentAllResponse.setGrade(assignmentStudent.getGrade());
    assignmentStudentAllResponse.setStudentFiles(assignmentStudent.getStudentFiles());
    assignmentStudentAllResponse.setComment(assignmentStudent.getComment());

    response.put("assignment", assignmentStudentAllResponse);

    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

}
