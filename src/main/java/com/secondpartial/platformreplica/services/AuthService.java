package com.secondpartial.platformreplica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public String login(
    String mail,
    String password
  ) throws Exception {
    UserModel user = userService.getByEmail(mail);
    System.out.println(user.getName() + " " + user.getEmail() + " " + user.getPassword());
    Boolean validUser = this.validateUser(user, password);
    
    if (!validUser) {
      throw new Exception("AuthService: login " + mail + " ********");
    }

    return jwtUtil.create(mail, password);
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
