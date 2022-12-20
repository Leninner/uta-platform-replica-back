package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.secondpartial.platformreplica.models.CourseModel;
import com.secondpartial.platformreplica.repositories.CourseRepository;

@Service
public class CourseService {
  @Autowired
  CourseRepository courseRepository;

  public ArrayList<CourseModel> getCoursesByUserRol(String rol) {
    return (ArrayList<CourseModel>) courseRepository.findByRol(rol);
  }

  public CourseModel createCourse(CourseModel course) {
    System.out.println("course: " + course);
    return courseRepository.save(course);
  }
}
