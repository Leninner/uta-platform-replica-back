package com.secondpartial.platformreplica.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String dni;
    private String rol;
    private String address;
    private String phoneNumber;
    private String city;
    private String province;
    private List<CoursePartInfoDTO> courses;
}
