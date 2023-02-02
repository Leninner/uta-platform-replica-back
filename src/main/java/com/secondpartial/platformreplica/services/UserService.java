package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.secondpartial.platformreplica.enums.SemesterEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.secondpartial.platformreplica.dtos.CityDTO;
import com.secondpartial.platformreplica.dtos.UserDTO;
import com.secondpartial.platformreplica.dtos.UserModifyDTO;
import com.secondpartial.platformreplica.dtos.UserResponseDTO;
import com.secondpartial.platformreplica.enums.RolEnum;
import com.secondpartial.platformreplica.models.AssignmentStudentModel;
import com.secondpartial.platformreplica.models.CareerModel;
import com.secondpartial.platformreplica.models.CityModel;
import com.secondpartial.platformreplica.models.CourseModel;
import com.secondpartial.platformreplica.models.CourseStudentModel;
import com.secondpartial.platformreplica.models.StudentModel;
import com.secondpartial.platformreplica.models.TeacherModel;
import com.secondpartial.platformreplica.models.UserModel;
import com.secondpartial.platformreplica.repositories.AssignmentStudentRepository;
import com.secondpartial.platformreplica.repositories.CareerRepository;
import com.secondpartial.platformreplica.repositories.CityRepository;
import com.secondpartial.platformreplica.repositories.CourseRepository;
import com.secondpartial.platformreplica.repositories.CourseStudentRepository;
import com.secondpartial.platformreplica.repositories.StudentRepository;
import com.secondpartial.platformreplica.repositories.TeacherRepository;
import com.secondpartial.platformreplica.repositories.UserRepository;
import com.secondpartial.platformreplica.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
public class UserService extends CrudHandler {
  @Autowired
  UserRepository userRepository;

  @Autowired
  JWTUtil jwtUtil;

  @Autowired
  CityRepository cityRepository;

  @Autowired
  StudentRepository studentRepository;

  @Autowired
  TeacherRepository teacherRepository;

  @Autowired
  CareerRepository careerRepository;

  @Autowired
  CourseRepository courseRepository;

  @Autowired
  CourseStudentRepository courseStudentRepository;

  @Autowired
  AssignmentStudentRepository assignmentStudentRepository;

  @Autowired
  S3Service s3Service;

  public List<UserModel> getUsers(String token) {
    if (!validateToken(token)) {
      return null;
    }

    return (List<UserModel>) userRepository.findAll();
  }

  @Override
  public ResponseEntity<LinkedHashMap<String, Object>> get(Long id, String token) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    if (!validateToken(token)) {
      response.put("message", "Invalid token!");
      response.put("status", HttpStatus.UNAUTHORIZED.value());
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.UNAUTHORIZED);
    }

    UserModel user = userRepository.findById(id).orElse(null);

    if (user == null) {
      response.put("message", "User not found!");
      response.put("status", HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.NOT_FOUND);
    }

    UserResponseDTO userResponse = new UserResponseDTO();
    userResponse.setId(user.getId());
    userResponse.setName(user.getName());
    userResponse.setEmail(user.getEmail());
    userResponse.setAddress(user.getAddress());
    userResponse.setPhoneNumber(user.getPhoneNumber());
    userResponse.setDni(user.getDni());
    userResponse.setRol(user.getRol().toString());
    userResponse.setCity(user.getCity().getName());
    userResponse.setCareer(user.getCareer().getName());
    userResponse.setCareer(user.getCareer().getName());

    response.put("user", userResponse);

    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<LinkedHashMap<String, Object>> create(UserDTO user) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    if (this.validateIfExists(user.getEmail())) {
      response.put("message", "An user with this email already exists!");
      response.put("status", HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    UserModel userModel = this.convertRequestDataToUserModelData(user);
    userRepository.save(userModel);
    this.processUserCreationByRol(userModel, user);

    response.put("message", "User registered successfully!");
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<HashMap<String, Object>> registerBulk(List<UserDTO> users) {
    HashMap<String, Object> response = new HashMap<>();
    List<UserModel> userss = new ArrayList<UserModel>();

    for (UserDTO user : users) {
      UserModel userToSave = this.convertRequestDataToUserModelData(user);
      userss.add(userToSave);
    }

    userRepository.saveAll(userss);
    response.put("message", "Usuarios registrados");
    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
  }

  public void processUserCreationByRol(UserModel userModel, UserDTO user) {
    if (userModel.getRol() == RolEnum.STUDENT) {
      StudentModel student = new StudentModel(null, userModel, null, null, user.getSemester());
      List<Long> courseIds = user.getCourseIds();

      if (courseIds != null) {
        List<CourseModel> courses = student.getCourses();

        if (courses == null) {
          courses = new ArrayList<CourseModel>();
        }

        for (Long courseId : courseIds) {
          courses.add(courseRepository.findById(courseId).get());
        }

        student.setCourses(courses);
      }

      studentRepository.save(student);
      return;
    }

    if (userModel.getRol() == RolEnum.TEACHER) {
      TeacherModel teacher = new TeacherModel(null, userModel, null);
      List<Long> courseIds = user.getCourseIds();

      if (courseIds != null) {
        System.out.println("courseIds: " + courseIds);
        List<CourseModel> courses = teacher.getCourses();

        if (courses == null) {
          courses = new ArrayList<CourseModel>();
        }

        for (Long courseId : courseIds) {
          CourseModel course = courseRepository.findById(courseId).get();
          course.setTeacher(teacher);
          courses.add(courseRepository.getReferenceById(courseId));
        }

        teacher.setCourses(courses);
      }

      teacherRepository.save(teacher);
      return;
    }
  }

  public UserModel convertRequestDataToUserModelData(UserDTO user) {
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    String hash = argon2.hash(1, 1024, 1, user.getPassword());
    user.setPassword(hash);

    CityModel city = cityRepository.findById(user.getCityId()).get();
    CareerModel career = careerRepository.findById(user.getCareerId()).get();

    UserModel userModel = new UserModel();
    userModel.setName(user.getName());
    userModel.setEmail(user.getEmail());
    userModel.setPassword(user.getPassword());
    userModel.setAddress(user.getAddress());
    userModel.setRol(RolEnum.getRolEnum(user.getRol()));
    userModel.setPhoneNumber(user.getPhoneNumber());
    userModel.setDni(user.getDni());
    userModel.setCity(city);
    userModel.setCareer(career);
    return userModel;
  }

  public UserModel getByEmail(String email) {
    return userRepository.getByEmail(email);
  }

  private Boolean validateToken(String token) {
    String usuarioId = jwtUtil.getKey(token);
    return usuarioId != null;
  }

  private Boolean validateIfExists(String email) {
    UserModel user = userRepository.getByEmail(email);
    return user != null;
  }

  @Override
  @Transactional
  public ResponseEntity<LinkedHashMap<String, Object>> delete(Long userId, String rol, String token) {
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    if (!validateToken(token)) {
      response.put("message", "Invalid token");
      response.put("status", HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    if (!rol.equals(RolEnum.ADMIN.toString())) {
      response.put("message", "You don't have permission to do this action");
      response.put("status", HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    UserModel userModel = userRepository.findById(userId).get();
    if (userModel == null) {
      response.put("message", "User not found");
      response.put("status", HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    if (userModel.getRol() == RolEnum.ADMIN) {
      response.put("message", "You can't delete an admin");
      response.put("status", HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    if (userModel.getRol() == RolEnum.STUDENT) {
      StudentModel student = userModel.getStudent();

      if (student == null) {
        userRepository.delete(userModel);
        response.put("message", "User deleted");
        response.put("status", HttpStatus.OK.value());
        return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
      }

      if (student.getAssignments() != null) {
        List<AssignmentStudentModel> assignments = assignmentStudentRepository
            .getByStudent(student.getId());

        for (AssignmentStudentModel assignment : assignments) {
          assignmentStudentRepository.delete(assignment);
        }
        student.getAssignments().clear();
      }
      if (student.getCourses() != null) {
        List<CourseStudentModel> courses = (List<CourseStudentModel>) courseStudentRepository
            .findByStudentId(student.getId());

        for (CourseStudentModel course : courses) {
          courseStudentRepository.delete(course);
        }
        student.getCourses().clear();
      }
      studentRepository.delete(student);
    } else if (userModel.getRol() == RolEnum.TEACHER) {
      TeacherModel teacher = userModel.getTeacher();

      if (teacher == null) {
        userRepository.delete(userModel);
        response.put("message", "User deleted");
        response.put("status", HttpStatus.OK.value());
      }
      if (teacher.getCourses() != null) {
        List<CourseModel> courses = (List<CourseModel>) teacherRepository
            .getReferenceById(teacher.getId()).getCourses();

        for (CourseModel course : courses) {
          course.setTeacher(null);
          courseRepository.save(course);
        }
        teacher.getCourses().clear();
      }
      teacherRepository.deleteById(teacher.getId());
    }

    userRepository.delete(userModel);
    response.put("message", "User deleted successfully");
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<LinkedHashMap<String, Object>> update(Long id, UserModifyDTO user,
      String token, MultipartFile userImage, String rol) {
    if (rol == null) {
      rol = "STUDENT";
    }

    LinkedHashMap<String, Object> response = new LinkedHashMap<>();

    if (!validateToken(token)) {
      response.put("message", "Invalid token");
      response.put("status", HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    UserModel userModel = userRepository.findById(id).get();
    if (userModel == null) {
      response.put("message", "User not found");
      response.put("status", HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    if (user.getCareerId() != null) {

      if (!rol.equals(RolEnum.ADMIN.toString())) {
        response.put("message", "You don't have permission to do this action");
        response.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
      }

      CareerModel career = careerRepository.findById(user.getCareerId()).get();
      userModel.setCareer(career);
    }

    if (userImage != null) {
      LinkedHashMap<String, String> userImageMap = s3Service.setUserImage(userModel, userImage);
      userModel.setUserImageUrl(userImageMap.get("userImageUrl"));
      userModel.setUserImageKey(userImageMap.get("userImageKey"));
    }

    if (user.getName() != null) {
      System.out.println(user.getName());
      userModel.setName(user.getName());
    }

    if (user.getEmail() != null) {
      userModel.setEmail(user.getEmail());
    }

    if (user.getAddress() != null) {
      userModel.setAddress(user.getAddress());
    }

    if (user.getPhoneNumber() != null) {
      userModel.setPhoneNumber(user.getPhoneNumber());
    }

    if (user.getDni() != null) {
      userModel.setDni(user.getDni());
    }

    if (user.getCityId() != null) {
      CityModel city = cityRepository.findById(user.getCityId()).get();
      userModel.setCity(city);
    }

    userRepository.save(userModel);

    HashMap<String, String> userInfo = new HashMap<>();
    userInfo.put("name", userModel.getName());
    userInfo.put("email", userModel.getEmail());
    userInfo.put("address", userModel.getAddress());
    userInfo.put("phoneNumber", userModel.getPhoneNumber());
    userInfo.put("rol", userModel.getRol().toString());
    userInfo.put("userImageUrl", userModel.getUserImageUrl());
    userInfo.put("city",
        new CityDTO(userModel.getCity().getId(), userModel.getCity().getName(),
            userModel.getCity().getProvince().getName()).getName());
    userInfo.put("province",
        new CityDTO(userModel.getCity().getId(), userModel.getCity().getName(),
            userModel.getCity().getProvince().getName()).getProvinceName());
    userInfo.put("id", userModel.getId().toString());

    response.put("message", "User modified successfully!");
    response.put("userInfo", userInfo);
    response.put("status", HttpStatus.OK.value());
    return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<HashMap<String, Object>> getCities() {
    HashMap<String, Object> response = new HashMap<>();
    List<CityModel> cities = cityRepository.findAll();
    List<CityDTO> citiesDTO = new ArrayList<CityDTO>();

    for (CityModel city : cities) {
      citiesDTO.add(new CityDTO(city.getId(), city.getName(), city.getProvince().getName()));
    }

    response.put("cities", citiesDTO);
    response.put("status", HttpStatus.OK.value());
    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
  }

  public ResponseEntity<HashMap<String, Object>> getUsersByRol(String rol) {
    HashMap<String, Object> response = new HashMap<>();

    List<UserModel> users = (List<UserModel>) userRepository.findByRol(rol);
    ArrayList<HashMap<String, Object>> usersDTO = new ArrayList<>();

    for (UserModel user : users) {
      HashMap<String, Object> userInfo = new HashMap<>();
      userInfo.put("name", user.getName());
      userInfo.put("email", user.getEmail());
      userInfo.put("address", user.getAddress());
      userInfo.put("phoneNumber", user.getPhoneNumber());
      userInfo.put("rol", user.getRol().toString());
      userInfo.put("userImageUrl", user.getUserImageUrl());

      userInfo.put("city",
          new CityDTO(user.getCity().getId(), user.getCity().getName(), user.getCity().getProvince().getName())
              .getName());
      userInfo.put("province",
          new CityDTO(user.getCity().getId(), user.getCity().getName(), user.getCity().getProvince().getName())
              .getProvinceName());
      userInfo.put("id", user.getId().toString());

      if (rol.equals("STUDENT")) {
        userInfo.put("semester", user.getStudent().getSemester().toString());
        userInfo.put("roleId", user.getStudent().getId().toString());
      }

      if (rol.equals("TEACHER")) {
        userInfo.put("roleId", user.getTeacher().getId().toString());
      }

      usersDTO.add(userInfo);
    }

    response.put("data", usersDTO);
    response.put("status", HttpStatus.OK.value());
    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
  }

}
