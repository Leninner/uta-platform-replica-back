package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.CourseCreationDTO;
import com.secondpartial.platformreplica.enums.SemesterEnum;
import com.secondpartial.platformreplica.models.CareerModel;
import com.secondpartial.platformreplica.models.CourseModel;
import com.secondpartial.platformreplica.models.TeacherModel;
import com.secondpartial.platformreplica.repositories.CareerRepository;
import com.secondpartial.platformreplica.repositories.CourseRepository;
import com.secondpartial.platformreplica.repositories.TeacherRepository;

@Service
public class CourseService {
  @Autowired
  CourseRepository courseRepository;

  @Autowired
  CareerRepository careerRepository;

  @Autowired
  TeacherRepository teacherRepository;

  public ArrayList<CourseModel> getCoursesByUserRol(String rol) {
    return (ArrayList<CourseModel>) courseRepository.findByRol(rol);
  }

  public ResponseEntity<HashMap<String, Object>> createCourse(CourseCreationDTO course) {
    HashMap<String, Object> response = new HashMap<>();
    CareerModel career = careerRepository.findById(course.getCareerId()).get();
    TeacherModel teacher = teacherRepository.findById(course.getTeacherId()).get();

    CourseModel courseModel = new CourseModel(
        null, course.getName(), SemesterEnum.getSemesterEnum(course.getSemester()), course.getDescription(),
        course.getImage(), null, teacher, career);
    
    courseRepository.save(courseModel);
    response.put("message", "Course created successfully");
    response.put("status", 200);
    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CREATED);
  }
}
