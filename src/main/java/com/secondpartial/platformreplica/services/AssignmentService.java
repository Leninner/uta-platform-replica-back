package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.AssignmentCreationDTO;
import com.secondpartial.platformreplica.enums.PartialEnum;
import com.secondpartial.platformreplica.models.AssignmentModel;
import com.secondpartial.platformreplica.models.CourseModel;
import com.secondpartial.platformreplica.models.StudentModel;
import com.secondpartial.platformreplica.models.TeacherModel;
import com.secondpartial.platformreplica.repositories.AssignmentRepository;
import com.secondpartial.platformreplica.repositories.CourseRepository;

@Service
public class AssignmentService {

  @Autowired
  AssignmentRepository assignmentRepository;

  @Autowired
  CourseRepository courseRepository;

  public ResponseEntity<HashMap<String, Object>> createAssignment(AssignmentCreationDTO assignment) {
    HashMap<String, Object> response = new HashMap<>();

    CourseModel course = courseRepository.findById(assignment.getCourseId()).orElse(null);

    List<StudentModel> students = course.getStudents();

    TeacherModel teacher = course.getTeacher();

    AssignmentModel newAssignment = new AssignmentModel();
    newAssignment.setName(assignment.getName());
    newAssignment.setDescription(assignment.getDescription());
    newAssignment.setPartial(PartialEnum.valueOf(assignment.getPartial()));
    newAssignment.setDateInit(assignment.getDateInit());
    newAssignment.setDateEnd(assignment.getDateEnd());
    newAssignment.setCourse(course);
    newAssignment.setTeacher(teacher);

    ArrayList<StudentModel> studentsToAdd = new ArrayList<>();
    for (StudentModel student : students) {
      System.out.println(student.getId());
      studentsToAdd.add(student);      
    }

    assignmentRepository.save(newAssignment);

    newAssignment.setStudents(studentsToAdd);

    assignmentRepository.save(newAssignment);

    response.put("message", "Assignment created successfully");
    return ResponseEntity.ok(response);
  }
}
