package com.secondpartial.platformreplica.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.UserDTO;
import com.secondpartial.platformreplica.enums.RolEnum;
import com.secondpartial.platformreplica.models.CityModel;
import com.secondpartial.platformreplica.models.UserModel;
import com.secondpartial.platformreplica.repositories.CityRepository;
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

  public List<UserModel> getUsers(String token) {
    if (!validarToken(token)) {
      return null;
    }
    
    return (List<UserModel>) userRepository.findAll();
  }

  public HashMap<String, Object> register(UserDTO user) {
    HashMap<String, Object> response = new HashMap<>();
    UserModel userExists = this.getByEmail(user.getEmail());
    Boolean exists = userExists != null;
    if(exists) {    
      response.put("message", "Un usuario con esa información ya existe");
      response.put("status", 400);
      return response;
    }

    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    String hash = argon2.hash(1, 1024, 1, user.getPassword());
    user.setPassword(hash);
    CityModel city = cityRepository.findById(user.getCityId()).get();
    UserModel userModel = new UserModel(
      null, 
      user.getName(), 
      user.getEmail(), 
      user.getPassword(), 
      user.getAddress(), 
      RolEnum.getRolEnum(user.getRol()), 
      user.getPhoneNumber(), 
      user.getImage(), 
      city, 
      null
    );
    userRepository.save(userModel);
    response.put("message", "Usuario registrado");
    response.put("status", 200);
    return response;
  }

  public UserModel getByEmail(String email) {
    return userRepository.getByEmail(email);
  }

  private boolean validarToken(String token) {
    String usuarioId = jwtUtil.getKey(token);
    return usuarioId != null;
  }
}
