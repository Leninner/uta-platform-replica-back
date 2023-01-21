package com.secondpartial.platformreplica.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.CourseCreationDTO;
import com.secondpartial.platformreplica.dtos.CoursePartInfoDTO;
import com.secondpartial.platformreplica.dtos.CourseResponseDTO;
import com.secondpartial.platformreplica.dtos.StudentResponseDTO;
import com.secondpartial.platformreplica.dtos.CityDTO;
import com.secondpartial.platformreplica.enums.SemesterEnum;
import com.secondpartial.platformreplica.models.CareerModel;
import com.secondpartial.platformreplica.models.CourseModel;
import com.secondpartial.platformreplica.models.CourseStudentModel;
import com.secondpartial.platformreplica.models.StudentModel;
import com.secondpartial.platformreplica.models.TeacherModel;
import com.secondpartial.platformreplica.repositories.CareerRepository;
import com.secondpartial.platformreplica.repositories.CourseRepository;
import com.secondpartial.platformreplica.repositories.CourseStudentRepository;
import com.secondpartial.platformreplica.repositories.StudentRepository;
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
  StudentRepository studentRepository;

  @Autowired
  AuthService authService;

  @Autowired
  JWTUtil jwtUtil;

  @Autowired
  CourseStudentRepository courseStudentRepository;

  public ResponseEntity<HashMap<String, Object>> getCoursesByRolAndId(String token, String rol, Long userId) {
    System.out.println("token: " + token);
    HashMap<String, Object> response = new HashMap<>();
    ArrayList<CourseModel> courses = null;

    try {
      if (jwtUtil.getKey(token) == null) {
        response.put("message", "Invalid token");
        response.put("status", 401);
        return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.UNAUTHORIZED);
      }
    } catch (Exception e) {
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
        course.getImage(), null, teacher, career, null);

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

  public Boolean addStudentToCourse(Long studentId, Long courseId) {
    CourseStudentModel courseStudent = new CourseStudentModel(null, studentId, courseId);
    courseStudentRepository.save(courseStudent);

    return true;
  }

  public ArrayList<CourseModel> orderCourseBySemester(ArrayList<CourseModel> courses) {
    ArrayList<CourseModel> orderedCourses = new ArrayList<>();
    final String[] SEMESTERS = SemesterEnum.getSemestersEnumToString();

    for (String semester : SEMESTERS) {
      for (CourseModel course : courses) {
        if (course.getSemester().toString().compareTo(semester) == 0) {
          orderedCourses.add(course);
        }
        System.out.println(course.getSemester().toString() + " " + semester);
      }
    }

    return orderedCourses;
  }

  public LinkedHashMap<String, Object> getCoursesOfEachSemester(Long careerId) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();
    ArrayList<CourseModel> courses = (ArrayList<CourseModel>) courseRepository.findByIdCareer(careerId);
    final String[] SEMESTERS = SemesterEnum.getSemestersEnumToString();

    for (String semester : SEMESTERS) {
      ArrayList<CourseResponseDTO> coursesResponse = new ArrayList<>();
      for (CourseModel course : courses) {
        if (course.getSemester().toString().compareTo(semester) == 0) {
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
      }
      response.put(semester, coursesResponse);
    }


    return new LinkedHashMap<String, Object>(response);
  }

  public ResponseEntity<HashMap<String, Object>> getStudentsByCourseId(Long courseId) {
    HashMap<String, Object> response = new HashMap<>();
    ArrayList<CourseStudentModel> courseStudents = (ArrayList<CourseStudentModel>) courseStudentRepository
        .findByCourseId(courseId);
    ArrayList<StudentResponseDTO> studentsResponse = new ArrayList<>();

    for (CourseStudentModel courseStudent : courseStudents) {
      StudentModel student = studentRepository.findById(courseStudent.getStudentId()).get();
      StudentResponseDTO studentResponse = new StudentResponseDTO() {
        {
          setId(student.getId());
          setName(student.getUser().getName());
          setEmail(student.getUser().getEmail());
          setImage(student.getUser().getImage());
          setRol(student.getUser().getRol().toString());
          setAdress(student.getUser().getAddress());
          setPhoneNumber(student.getUser().getPhoneNumber());
          setCity(student.getUser().getCity().getName());
          setProvince(
              new CityDTO(student.getUser().getCity().getName(), student.getUser().getCity().getProvince().getName())
                  .getProvinceName());

          List<CoursePartInfoDTO> courses = new ArrayList<>();
          for (CourseModel courseStudent : student.getCourses()) {
            CoursePartInfoDTO courseResponse = new CoursePartInfoDTO() {
              {
                setId(courseStudent.getId());
                setName(courseStudent.getName());
                setSemester(courseStudent.getSemester().toString());
                setCareerName(courseStudent.getCareer().getName());
              }
            };
            courses.add(courseResponse);
          }
          setCourses(courses);
        }
      };
      studentsResponse.add(studentResponse);
    }

    response.put("status", 200);
    response.put("students", studentsResponse);
    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
  }

  public Lis

}
