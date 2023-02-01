package com.secondpartial.platformreplica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String dni;
    private String userImageUrl;
    private String city;
    private String rol;
    private String career;
    private String semester;
}
