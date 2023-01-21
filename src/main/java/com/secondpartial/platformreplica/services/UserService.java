package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.secondpartial.platformreplica.dtos.CityDTO;
import com.secondpartial.platformreplica.dtos.UserDTO;
import com.secondpartial.platformreplica.dtos.UserModifyDTO;
import com.secondpartial.platformreplica.enums.RolEnum;
import com.secondpartial.platformreplica.models.CareerModel;
import com.secondpartial.platformreplica.models.CityModel;
import com.secondpartial.platformreplica.models.CourseModel;
import com.secondpartial.platformreplica.models.StudentModel;
import com.secondpartial.platformreplica.models.TeacherModel;
import com.secondpartial.platformreplica.models.UserModel;
import com.secondpartial.platformreplica.repositories.CareerRepository;
import com.secondpartial.platformreplica.repositories.CityRepository;
import com.secondpartial.platformreplica.repositories.CourseRepository;
import com.secondpartial.platformreplica.repositories.StudentRepository;
import com.secondpartial.platformreplica.repositories.TeacherRepository;
import com.secondpartial.platformreplica.repositories.UserRepository;
import com.secondpartial.platformreplica.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
public class UserService {
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

  public List<UserModel> getUsers(String token) {
    if (!validateToken(token)) {
      return null;
    }

    return (List<UserModel>) userRepository.findAll();
  }

  public ResponseEntity<HashMap<String, Object>> register(UserDTO user) {
    HashMap<String, Object> response = new HashMap<>();

    if (this.validateIfExists(user.getEmail())) {
      response.put("message", "An user with this email already exists!");
      response.put("status", HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    UserModel userModel = this.convertRequestDataToUserModelData(user);
    userRepository.save(userModel);
    this.processUserByRol(userModel, user);

    response.put("message", "User registered successfully!");
    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
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

  public void processUserByRol(UserModel userModel, UserDTO user) {
    if (userModel.getRol() == RolEnum.STUDENT) {
      StudentModel student = new StudentModel(null, userModel, null, null);
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

    UserModel userModel = new UserModel(
        null,
        user.getName(),
        user.getEmail(),
        user.getPassword(),
        user.getAddress(),
        RolEnum.getRolEnum(user.getRol()),
        user.getPhoneNumber(),
        user.getDni(),
        user.getImage(),
        city, null, null, career);

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

  public ResponseEntity<HashMap<String, Object>> modifyUser(Long id, UserModifyDTO user,
      String token) {
    HashMap<String, Object> response = new HashMap<>();

    if (!validateToken(token)) {
      response.put("message", "Invalid token");
      response.put("status", HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    UserModel userModel = userRepository.findById(id).get();
    if (userModel == null) {
      response.put("message", "User not found");
      response.put("status", HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    if (user.getName() != null) {
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

    if (user.getImage() != null) {
      userModel.setImage(user.getImage());
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
    userInfo.put("image", userModel.getImage());
    userInfo.put("city", new CityDTO(userModel.getCity().getName(), userModel.getCity().getProvince().getName()).getName());
    userInfo.put("province",
    new CityDTO(userModel.getCity().getName(), userModel.getCity().getProvince().getName()).getProvinceName());
    userInfo.put("id", userModel.getId().toString());
    
    response.put("message", "User modified successfully!");
    response.put("userInfo", userInfo);
    response.put("status", HttpStatus.OK.value());
    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
  }

}
