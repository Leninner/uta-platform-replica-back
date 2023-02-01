package com.secondpartial.platformreplica.dtos;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserModifyDTO implements java.io.Serializable {
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private String dni;
    private Long cityId;
    private MultipartFile userImage;
    private Long careerId;
}
