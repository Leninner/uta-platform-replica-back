package com.secondpartial.platformreplica.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
  private String name;
  private String email;
  private String password;
  private String address;
  private String phoneNumber;
  private String image;
  private Long cityId;
  private String rol;
  private Long careerId;
  private List<Long> courseIds;
}
