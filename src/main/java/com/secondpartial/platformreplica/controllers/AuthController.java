package com.secondpartial.platformreplica.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondpartial.platformreplica.dtos.LoginDTO;
import com.secondpartial.platformreplica.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<HashMap<String, Object>> login(@RequestBody LoginDTO loginDto) {
    return authService.login(loginDto.getEmail(), loginDto.getPassword());
  }
}
