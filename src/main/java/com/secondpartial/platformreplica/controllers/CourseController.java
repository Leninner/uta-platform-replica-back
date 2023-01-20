package com.secondpartial.platformreplica.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.secondpartial.platformreplica.dtos.CourseCreationDTO;
import com.secondpartial.platformreplica.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
  @Autowired
  CourseService courseService;

  @GetMapping(path = "/{rol}/{userId}")
  public ResponseEntity<HashMap<String, Object>> getCoursesByUserRol(@RequestHeader("Authorization") String token,
      @PathVariable("rol") String rol, @PathVariable("userId") Long userId) {
    return courseService.getCoursesByRolAndId(token, rol, userId);
  }

  @GetMapping(path = "/{courseId}/students")
  public ResponseEntity<HashMap<String, Object>> getStudentsByCourseId(@PathVariable("courseId") Long courseId) {
    return courseService.getStudentsByCourseId(courseId);
  }

  @PostMapping()
  public ResponseEntity<HashMap<String, Object>> createCourse(@RequestBody CourseCreationDTO course) {
    return courseService.createCourse(course);
  }

  @PostMapping("/bulk")
  public ResponseEntity<HashMap<String, Object>> createCourses(@RequestBody ArrayList<CourseCreationDTO> courses) {
    return courseService.createCourses(courses);
  }
}
