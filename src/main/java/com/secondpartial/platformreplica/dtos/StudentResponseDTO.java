package com.secondpartial.platformreplica.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String userImageUrl;
    private String rol;
    private String career;
    private String dni;
    private String semester;
    private String address;
    private String phoneNumber;
    private String city;
    private String province;
    private List<CoursePartInfoDTO> courses;

    public StudentResponseDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
