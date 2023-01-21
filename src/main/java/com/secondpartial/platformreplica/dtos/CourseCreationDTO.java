package com.secondpartial.platformreplica.dtos;

import lombok.Data;

@Data
public class CourseCreationDTO {
  String name;
  String semester;
  String description;
  String image;
  Long careerId;
  Long teacherId;
}
