package com.secondpartial.platformreplica.dtos;

import java.util.List;

import com.secondpartial.platformreplica.enums.SemesterEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {
  private String name;
  private String email;
  private String password;
  private String address;
  private String phoneNumber;
  private String dni;
  private String userImageUrl;
  private Long cityId;
  private String rol;
  private Long careerId;
  private SemesterEnum semester;
  private List<Long> courseIds;

  public UserDTO(String name, String email, String password, String address, String phoneNumber, String dni,
      String userImageUrl, Long cityId) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.dni = dni;
    this.userImageUrl = userImageUrl;
    this.cityId = cityId;
  }
}
