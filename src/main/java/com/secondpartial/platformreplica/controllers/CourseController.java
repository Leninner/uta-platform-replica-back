package com.secondpartial.platformreplica.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.secondpartial.platformreplica.dtos.CourseUpdateDTO;
import org.springframework.data.repository.query.Param;
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
  public ResponseEntity<LinkedHashMap<String, Object>> getCoursesByUserRol(@RequestHeader("Authorization") String token,
      @PathVariable("rol") String rol, @PathVariable("userId") Long userId) {
    return courseService.getCoursesByRolAndId(token, rol, userId);
  }

  @GetMapping(path = "/{courseId}/students")
  public ResponseEntity<LinkedHashMap<String, Object>> getStudentsByCourseId(@PathVariable("courseId") Long courseId) {
    return courseService.getStudentsByCourseId(courseId);
  }

  @PostMapping()
  public ResponseEntity<LinkedHashMap<String, Object>> createCourse(@RequestBody CourseCreationDTO course,
      @RequestHeader("rol") String rol) {
    return courseService.create(course, rol);
  }

  @PostMapping("/bulk")
  public ResponseEntity<LinkedHashMap<String, Object>> createCourses(
      @RequestBody ArrayList<CourseCreationDTO> courses) {
    return courseService.createCourses(courses);
  }

  @PutMapping()
    public ResponseEntity<LinkedHashMap<String, Object>> updateCourse( @RequestBody CourseUpdateDTO course,
        @RequestHeader("rol") String rol) {
        return courseService.update(course, rol);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<LinkedHashMap<String, Object>> deleteCourse(@PathVariable("courseId") Long courseId,
        @RequestHeader("rol") String rol) {
        return courseService.delete(courseId, rol);
    }

}
