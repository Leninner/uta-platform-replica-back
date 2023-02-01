package com.secondpartial.platformreplica.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherUpdateDTO {
    private String name;
    private String email;
    private String dni;
    private String rol;
    private String address;
    private String phoneNumber;
    private Long cityId;
    private String province;
    private List<Long> coursesIds;
}
