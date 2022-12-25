package com.secondpartial.platformreplica.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.CityDTO;
import com.secondpartial.platformreplica.dtos.UserDTO;
import com.secondpartial.platformreplica.models.CityModel;
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

  public HashMap<String, Object> login(
    String mail,
    String password
  ) throws Exception {
    UserModel user = userService.getByEmail(mail);
    Boolean validUser = this.validateUser(user, password);
    if (!validUser) {
      throw new Exception("AuthService: login " + mail + " ********");
    }
    
    HashMap<String, String> userInfo = new HashMap<>();
    userInfo.put("name", user.getName());
    userInfo.put("email", user.getEmail());
    userInfo.put("address", user.getAddress());
    userInfo.put("phoneNumber", user.getPhoneNumber());
    userInfo.put("rol", user.getRol().toString());
    userInfo.put("image", user.getImage());
    userInfo.put("city", new CityDTO(user.getCity().getName(), user.getCity().getProvince().getName()).getName());
    userInfo.put("province", new CityDTO(user.getCity().getName(), user.getCity().getProvince().getName()).getProvinceName());
    userInfo.put("id", user.getId().toString());
    

    String token = jwtUtil.create(mail, password);
    HashMap<String, Object> response = new HashMap<>();
    response.put("token", token);
    response.put("user", userInfo);

    return response;
  }

  private Boolean validateUser(UserModel user, String passwordToVerify){
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
