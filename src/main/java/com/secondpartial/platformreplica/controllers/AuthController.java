package com.secondpartial.platformreplica.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
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
  public HashMap<String, Object> login(@RequestBody LoginDTO loginDto) {
    try {
      return authService.login(loginDto.getEmail(), loginDto.getPassword());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  /**
   * 
  @Post('login')
  async login(@Body() loginDto: LoginDto): Promise<{ accessToken: string }> {
    return this.authService.login(loginDto.username, loginDto.password)
  }

  @Get('register')
  async generateTokens(@Query('code') code: string): Promise<boolean> {
    return this.authService.requestAccessToken(code)
  }

  @Get('register-user/:password')
  async generateSaltPassword(
    @Param('password') password: string,
  ): Promise<string> {
    return this.authService.generateSaltPassword(password)
  }
   */
}
