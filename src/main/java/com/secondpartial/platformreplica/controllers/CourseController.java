package com.secondpartial.platformreplica.controllers;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.secondpartial.platformreplica.dtos.CourseCreationDTO;
import com.secondpartial.platformreplica.models.CourseModel;
import com.secondpartial.platformreplica.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
  @Autowired
  CourseService courseService;

  @GetMapping(path = "/{rol}")
  public ArrayList<CourseModel> getCoursesByUserRol(@PathVariable("rol") String rol) {
    return this.courseService.getCoursesByUserRol(rol);
  }

  @PostMapping()
  public CourseModel createCourse(@RequestBody CourseCreationDTO course) {
    return this.courseService.createCourse(
      new CourseModel(
        course.getName(),
        course.getSemester(),
        course.getDescription(),
        course.getImage(),
        course.getUsers()
      )
    );
  }
}
