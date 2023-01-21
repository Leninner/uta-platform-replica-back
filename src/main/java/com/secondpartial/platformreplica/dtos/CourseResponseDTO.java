package com.secondpartial.platformreplica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {
  Long id;
  String name;
  String semester;
  String description;
  String image;
  String careerName;
  String teacherName;

}
