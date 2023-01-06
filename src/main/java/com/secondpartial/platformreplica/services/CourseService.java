package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.CourseCreationDTO;
import com.secondpartial.platformreplica.dtos.CourseResponseDTO;
import com.secondpartial.platformreplica.enums.SemesterEnum;
import com.secondpartial.platformreplica.models.CareerModel;
import com.secondpartial.platformreplica.models.CourseModel;
import com.secondpartial.platformreplica.models.TeacherModel;
import com.secondpartial.platformreplica.repositories.CareerRepository;
import com.secondpartial.platformreplica.repositories.CourseRepository;
import com.secondpartial.platformreplica.repositories.TeacherRepository;
import com.secondpartial.platformreplica.utils.JWTUtil;

@Service
public class CourseService {
  @Autowired
  CourseRepository courseRepository;

  @Autowired
  CareerRepository careerRepository;

  @Autowired
  TeacherRepository teacherRepository;

  @Autowired
  AuthService authService;

  @Autowired
  JWTUtil jwtUtil;

  public ResponseEntity<HashMap<String, Object>> getCoursesByRolAndId(String token, String rol, Long userId) {
    System.out.println("token: " + token);
    HashMap<String, Object> response = new HashMap<>();
    ArrayList<CourseModel> courses = null;

    if(jwtUtil.getKey(token) == null) {
      response.put("message", "Invalid token");
      response.put("status", 401);
      return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.UNAUTHORIZED);
    }

    if (rol.compareTo("STUDENT") == 0) {
      courses = (ArrayList<CourseModel>) courseRepository.findByIdStudent(userId);
    } else if (rol.compareTo("TEACHER") == 0) {
      courses = (ArrayList<CourseModel>) courseRepository.findByIdTeacher(userId);
    } else if (rol.compareTo("ADMIN") == 0) {
      courses = (ArrayList<CourseModel>) courseRepository.findAll();
    } else {
      response.put("message", "Rol not found");
      response.put("status", 404);
      return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.NOT_FOUND);
    }

    ArrayList<CourseResponseDTO> coursesResponse = new ArrayList<>();

    for (CourseModel course : courses) {
      CourseResponseDTO courseResponse = new CourseResponseDTO() {
        {
          setId(course.getId());
          setName(course.getName());
          setSemester(course.getSemester().toString());
          setDescription(course.getDescription());
          setImage(course.getImage());
          setCareerName(course.getCareer().getName());
          setTeacherName(course.getTeacher().getUser().getName());
        }
      };

      coursesResponse.add(courseResponse);
    }

    response.put("status", 200);
    response.put("courses", coursesResponse);
    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
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

  public ResponseEntity<HashMap<String, Object>> createCourses(ArrayList<CourseCreationDTO> courses) {
    HashMap<String, Object> response = new HashMap<>();

    for (CourseCreationDTO course : courses) {
      this.createCourse(course);
    }

    response.put("message", "Courses created successfully");
    response.put("status", 200);
    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CREATED);
  }
}
