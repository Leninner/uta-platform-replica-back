package com.secondpartial.platformreplica.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondpartial.platformreplica.dtos.UserDTO;
import com.secondpartial.platformreplica.dtos.UserModifyDTO;
import com.secondpartial.platformreplica.models.UserModel;
import com.secondpartial.platformreplica.repositories.UserRepository;
import com.secondpartial.platformreplica.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  UserService userService;

  @Autowired
  UserRepository userRepository;

  @GetMapping()
  public List<UserModel> getUsers(@RequestHeader(value = "Authorization") String token) {
    return userService.getUsers(token);
  }

  @GetMapping("/{rol}")
  public ResponseEntity<HashMap<String, Object>> getUsersByRol(@PathVariable("rol") String rol) {
    return userService.getUsersByRol(rol);
  }

  @PostMapping()
  public ResponseEntity<LinkedHashMap<String, Object>> registerUser(@RequestBody UserDTO user) {
    return userService.create(user);
  }

  @PostMapping("/bulk")
  public ResponseEntity<HashMap<String, Object>> registerUsers(@RequestBody List<UserDTO> users) {
    return userService.registerBulk(users);
  }

  @PutMapping(value = "/{id}", consumes = { "multipart/form-data" })
  public ResponseEntity<LinkedHashMap<String, Object>> ModifyUser(@PathVariable("id") Long id,
      @ModelAttribute UserModifyDTO user,
      @RequestHeader(value = "Authorization") String token, @RequestHeader String rol) {
    return userService.update(id, user, token, user.getUserImage(), rol);
  }

  @GetMapping("/cities")
  public ResponseEntity<HashMap<String, Object>> getCities() {
    return userService.getCities();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<LinkedHashMap<String, Object>> deleteUser(@PathVariable("id") Long id,
      @RequestHeader String rol,
      @RequestHeader(value = "Authorization") String token) {
    return userService.delete(id, rol, token);
  }
}
