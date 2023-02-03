package com.secondpartial.platformreplica.services;

import java.util.*;

import com.secondpartial.platformreplica.models.*;
import com.secondpartial.platformreplica.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.CourseCreationDTO;
import com.secondpartial.platformreplica.dtos.CoursePartInfoDTO;
import com.secondpartial.platformreplica.dtos.CourseResponseDTO;
import com.secondpartial.platformreplica.dtos.CourseUpdateDTO;
import com.secondpartial.platformreplica.dtos.StudentResponseDTO;
import com.secondpartial.platformreplica.dtos.CityDTO;
import com.secondpartial.platformreplica.enums.RolEnum;
import com.secondpartial.platformreplica.enums.SemesterEnum;
import com.secondpartial.platformreplica.utils.JWTUtil;

@Service
public class CourseService extends CrudHandler {
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

  @Autowired
  AssignmentStudentRepository assignmentStudentRepository;

  @Autowired
    AssignmentRepository assignmentRepository;

  public ResponseEntity<LinkedHashMap<String, Object>> getCoursesByRolAndId(String token, String rol, Long userId) {
    System.out.println("token: " + token);
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();
    ArrayList<CourseModel> courses = null;

    try {
      if (jwtUtil.getKey(token) == null) {
        response.put("message", "Invalid token");
        response.put("status", 401);
        return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.UNAUTHORIZED);
      }
    } catch (Exception e) {
      response.put("message", "Invalid token");
      response.put("status", 401);
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.UNAUTHORIZED);
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
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.NOT_FOUND);
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
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<LinkedHashMap<String, Object>> create(CourseCreationDTO course, String rol) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    if (!rol.equals(RolEnum.ADMIN.toString())) {
      response.put("message", "You don't have permission to create a course");
      response.put("status", 401);
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.UNAUTHORIZED);
    }

    if (((List<CourseModel>) courseRepository.findByNameAndSemester(course.getName(), SemesterEnum.getSemesterEnum(course.getSemester()).toString())).size() > 0) {
      response.put("message", "Course already exists");
      response.put("status", 400);
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    CareerModel career = careerRepository.findById(course.getCareerId()).get();
    TeacherModel teacher = teacherRepository.findById(course.getTeacherId()).get();

    CourseModel courseModel = new CourseModel(
        null, course.getName(), SemesterEnum.getSemesterEnum(course.getSemester()), course.getDescription(),
        course.getImage(), null, teacher, career, null);

    courseRepository.save(courseModel);

    CourseResponseDTO courseResponse = new CourseResponseDTO() {
      {
        setId(courseModel.getId());
        setName(courseModel.getName());
        setSemester(courseModel.getSemester().toString());
        setDescription(courseModel.getDescription());
        setCareerName(courseModel.getCareer().getName());
        setTeacherName(courseModel.getTeacher().getUser().getName());
      }
    };
    response.put("message", "Course created successfully");
    response.put("status", 200);
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<LinkedHashMap<String, Object>> update(CourseUpdateDTO course, String rol) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    if (!rol.equals(RolEnum.ADMIN.toString())) {
      response.put("message", "You don't have permission to update a course");
      response.put("status", 401);
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.UNAUTHORIZED);
    }

    CourseModel courseModel = courseRepository.getReferenceById(course.getId());

    if (courseModel == null) {
      response.put("message", "Course not found");
      response.put("status", 404);
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.NOT_FOUND);
    }

    if (((List<CourseModel>) courseRepository.findByNameAndSemester(course.getName(), courseModel.getSemester().toString())).size() > 0) {
      response.put("message", "Course already exists");
      response.put("status", 400);
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    if(course.getName() != null) {
      courseModel.setName(course.getName());
    }

    if(course.getDescription() != null) {
      courseModel.setDescription(course.getDescription());
    }

    if(course.getTeacherId() != null) {
      List<CourseModel> coursesOfLastTeacher;
      try {
        coursesOfLastTeacher = courseModel.getTeacher().getCourses();
        coursesOfLastTeacher.remove(courseModel);
        courseModel.getTeacher().setCourses(coursesOfLastTeacher);
        teacherRepository.save(courseModel.getTeacher());
      } catch (Exception e) {
        System.out.println(e);
      }
      
      TeacherModel teacher = teacherRepository.findById(course.getTeacherId()).get();

      if(teacher.getCourses() == null) {
        teacher.setCourses(new ArrayList<CourseModel>());
      }
        List<CourseModel> coursesOfNewTeacher = teacher.getCourses();
            coursesOfNewTeacher.add(courseModel);
            teacher.setCourses(coursesOfNewTeacher);
            teacherRepository.save(teacher);
      courseModel.setTeacher(teacher);
    }

    courseRepository.save(courseModel);
    response.put("message", "Course updated successfully");
    response.put("status", 200);
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.CREATED);
  }

  public ResponseEntity<LinkedHashMap<String, Object>> createCourses(ArrayList<CourseCreationDTO> courses) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    for (CourseCreationDTO course : courses) {
      this.create(course, "ADMIN");
    }

    response.put("message", "Courses created successfully");
    response.put("status", 200);
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.CREATED);
  }

  public Boolean addStudentToCourse(Long studentId, Long courseId) {
    CourseStudentModel courseStudent = new CourseStudentModel(null, studentId, courseId);
    StudentModel studentModel = studentRepository.getReferenceById(studentId);
    CourseModel courseModel = courseRepository.getReferenceById(courseId);
    List<AssignmentModel> assignments = courseModel.getAssignments();

    for (AssignmentModel assignment: assignments){
      AssignmentStudentModel assignmentStudentModel = new AssignmentStudentModel(null, assignment.getId(), studentId, null, null, null, false);
        assignmentStudentRepository.save(assignmentStudentModel);
    }
    courseStudentRepository.save(courseStudent);

    return true;
  }

  @Override
  public ResponseEntity<LinkedHashMap<String, Object>> delete(Long id, String rol) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    if (!rol.equals(RolEnum.ADMIN.toString())) {
      response.put("message", "You don't have permission to delete a course");
      response.put("status", 401);
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.UNAUTHORIZED);
    }

    CourseModel course = courseRepository.getReferenceById(id);

    if (course == null) {
      response.put("message", "Course not found");
      response.put("status", 404);
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.NOT_FOUND);
    }

    List<AssignmentModel> assignments = (List<AssignmentModel>) assignmentRepository.findByCourseId(id);
    StudentModel student = new StudentModel();

    for (AssignmentModel assignment : assignments) {
      List<AssignmentStudentModel> assignmentStudents = (List<AssignmentStudentModel>) assignmentStudentRepository.findByAssignment(assignment.getId());

      for (AssignmentStudentModel assignmentStudent : assignmentStudents) {
        student = studentRepository.getReferenceById(assignmentStudent.getStudentId());

        List<AssignmentModel> assignmentModelsStudent = student.getAssignments();
        assignmentModelsStudent.remove(assignment);

        student.setAssignments(assignmentModelsStudent);

        studentRepository.save(student);
        assignmentStudentRepository.delete(assignmentStudent);
      }
      assignmentRepository.delete(assignment);
    }


    List<CourseStudentModel> courseStudents = (List<CourseStudentModel>) courseStudentRepository.findByCourseId(id);
    for (CourseStudentModel courseStudent : courseStudents) {
      courseStudentRepository.delete(courseStudent);
    }
    courseRepository.delete(course);
    response.put("message", "Course deleted successfully");
    response.put("status", 200);
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<LinkedHashMap<String, Object>> get(Long id) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();
    CourseModel course = courseRepository.findById(id).get();

    if (course == null) {
      response.put("message", "Course not found");
      response.put("status", 404);
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.NOT_FOUND);
    }

    response.put("course", course);
    response.put("message", "Course found successfully");
    response.put("status", 200);
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
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
          String teacherName;

          if(course.getTeacher() == null) {
            teacherName = "No teacher assigned";
          } else {
            teacherName = course.getTeacher().getUser().getName();
          }

          CourseResponseDTO courseResponse = new CourseResponseDTO() {
            {
              setId(course.getId());
              setName(course.getName());
              setSemester(course.getSemester().toString());
              setDescription(course.getDescription());
              setImage(course.getImage());
              setCareerName(course.getCareer().getName());
              setTeacherName(teacherName);
            }
          };
          coursesResponse.add(courseResponse);
        }
      }
      response.put(semester, coursesResponse);
    }

    return new LinkedHashMap<String, Object>(response);
  }

  public ResponseEntity<LinkedHashMap<String, Object>> getStudentsByCourseId(Long courseId) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();
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
          setUserImageUrl(student.getUser().getUserImageUrl());
          setRol(student.getUser().getRol().toString());
          setAddress(student.getUser().getAddress());
          setPhoneNumber(student.getUser().getPhoneNumber());
          setCity(student.getUser().getCity().getName());
          setProvince(
              new CityDTO(student.getUser().getCity().getId(), student.getUser().getCity().getName(),
                  student.getUser().getCity().getProvince().getName())
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
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  public List<StudentModel> getStudentModelsByCourseId(Long courseId) {
    ArrayList<CourseStudentModel> courseStudents = (ArrayList<CourseStudentModel>) courseStudentRepository
        .findByCourseId(courseId);
    ArrayList<StudentModel> students = new ArrayList<>();

    for (CourseStudentModel courseStudent : courseStudents) {
      StudentModel student = studentRepository.findById(courseStudent.getStudentId()).get();
      students.add(student);
    }

    return students;
  }

  public CoursePartInfoDTO getCoursePartInfo(Long courseId) {
    CourseModel course = courseRepository.findById(courseId).get();
    CoursePartInfoDTO courseResponse = new CoursePartInfoDTO() {
      {
        setId(course.getId());
        setName(course.getName());
        setSemester(course.getSemester().toString());
        setCareerName(course.getCareer().getName());
      }
    };
    return courseResponse;
  }

}
