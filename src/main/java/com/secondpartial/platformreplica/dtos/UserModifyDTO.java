package com.secondpartial.platformreplica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserModifyDTO {
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private String dni;
    private Long cityId;
}
