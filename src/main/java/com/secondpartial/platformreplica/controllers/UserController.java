package com.secondpartial.platformreplica.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.secondpartial.platformreplica.dtos.UserDTO;
import com.secondpartial.platformreplica.models.UserModel;
import com.secondpartial.platformreplica.repositories.UserRepository;
import com.secondpartial.platformreplica.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  UserService userService;

  @Autowired
  UserRepository userRepository;


  @GetMapping()
  public List<UserModel> getUsers(@RequestHeader(value="Authorization") String token) {
    return userService.getUsers(token);
  }

  @PostMapping()
  public HashMap<String, Object> registerUser(@RequestBody UserDTO user) {
      return userService.register(user);
  }
}
