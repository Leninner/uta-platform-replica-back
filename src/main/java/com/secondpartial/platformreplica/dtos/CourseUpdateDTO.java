package com.secondpartial.platformreplica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CourseUpdateDTO {
    Long id;
    String name;
    String semester;
    String description;
    Long careerId;
    Long teacherId;
}
