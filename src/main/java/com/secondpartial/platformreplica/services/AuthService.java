package com.secondpartial.platformreplica.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.CityDTO;
import com.secondpartial.platformreplica.models.UserModel;
import com.secondpartial.platformreplica.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
public class AuthService {
  @Autowired
  JWTUtil jwtUtil;

  @Autowired
  UserService userService;

  public ResponseEntity<HashMap<String, Object>> login(
      String mail,
      String password) {
    UserModel user = userService.getByEmail(mail);
    HashMap<String, Object> response = new HashMap<>();
    if (user == null) {
      response.put("message", "El usuario no existe");
      return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    Boolean validUser = this.validateUser(user, password);
    if (!validUser) {
      response.put("message", "Contraseña incorrecta");
      return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    HashMap<String, String> userInfo = new HashMap<>();
    userInfo.put("name", user.getName());
    userInfo.put("email", user.getEmail());
    userInfo.put("address", user.getAddress());
    userInfo.put("phoneNumber", user.getPhoneNumber());
    userInfo.put("rol", user.getRol().toString());
    userInfo.put("userImageUrl", user.getUserImageUrl());
    System.out.println(user.getUserImageUrl());
    userInfo.put("city",
        new CityDTO(user.getCity().getId(), user.getCity().getName(), user.getCity().getProvince().getName())
            .getName());
    userInfo.put("province",
        new CityDTO(user.getCity().getId(), user.getCity().getName(), user.getCity().getProvince().getName())
            .getProvinceName());
    userInfo.put("id", user.getId().toString());
    
    System.out.println("rol + " + user.getRol().toString());
    
    if (user.getRol().toString().equals("STUDENT")) {
      userInfo.put("semester", user.getStudent().getSemester().toString());
      userInfo.put("roleId", user.getStudent().getId().toString());
    }

    if (user.getRol().toString().equals("TEACHER")) {
      userInfo.put("roleId", user.getTeacher().getId().toString());
    }

    String token = jwtUtil.create(mail, password);
    response.put("token", token);
    response.put("user", userInfo);

    return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
  }

  private Boolean validateUser(UserModel user, String passwordToVerify) {
    return this.checkPassword(passwordToVerify, user.getPassword());
  }

  private Boolean checkPassword(String loginPassword, String userPassword) {
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    if (argon2.verify(userPassword, loginPassword)) {
      return true;
    }

    return false;
  }
}
