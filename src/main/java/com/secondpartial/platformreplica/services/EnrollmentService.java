package com.secondpartial.platformreplica.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.enrollment.*;
import com.secondpartial.platformreplica.repositories.CourseRepository;
import com.secondpartial.platformreplica.repositories.StudentRepository;
import com.secondpartial.platformreplica.utils.ResponseApiUtil;

@Service
public class EnrollmentService {
  @Autowired
  private ResponseApiUtil responseApiUtil;

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private CourseService courseService;

  @Autowired
  private StudentRepository studentRepository;

  public ResponseEntity<HashMap<String, Object>> enrollStudentToCourse(EnrollStudentDTO enrollStudentDTO) {
    System.out.println("enrollStudentDTO: " + enrollStudentDTO);
    String studentSemester = enrollStudentDTO.getStudent().getSemester();
    String courseSemester = enrollStudentDTO.getCourse().getSemester();
    Long userID = enrollStudentDTO.getStudent().getId();
    Long courseID = enrollStudentDTO.getCourse().getId();

    if (!studentSemester.equals(courseSemester)) {
      return responseApiUtil.handleResponse("El estudiante no puede inscribirse a un curso de otro semestre",
          HttpStatus.BAD_REQUEST);
    }

    ArrayList<Long> courses = (ArrayList<Long>) courseRepository.findCoursesIDsByStudentID(userID);

    if (courses.size() == 5) {
      return responseApiUtil.handleResponse("El estudiante no puede tener más de 5 cursos", HttpStatus.BAD_REQUEST);
    }

    if (courses.contains(courseID)) {
      return responseApiUtil.handleResponse("El estudiando ya está matriculado en este curso", HttpStatus.BAD_REQUEST);
    }

    Long studentID = studentRepository.findByUserId(userID).getId();

    courseService.addStudentToCourse(studentID, courseID);
    return responseApiUtil.handleResponse("Student enrolled successfully", HttpStatus.OK);
  }
}