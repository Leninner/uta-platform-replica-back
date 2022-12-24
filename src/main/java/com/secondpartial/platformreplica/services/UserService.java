package com.secondpartial.platformreplica.services;

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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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

  public UserModel register(UserDTO user) {
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
    return userRepository.save(userModel);
  }

  public UserModel getByEmail(String email) {
    System.out.println("UserService: getByEmail " + email);
    return userRepository.getByEmail(email);
  }

  private boolean validarToken(String token) {
    String usuarioId = jwtUtil.getKey(token);
    return usuarioId != null;
  }
}
