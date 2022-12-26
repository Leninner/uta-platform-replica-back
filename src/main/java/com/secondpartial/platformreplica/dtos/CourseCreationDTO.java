package com.secondpartial.platformreplica.dtos;

import java.util.List;

import com.secondpartial.platformreplica.models.CareerModel;
import com.secondpartial.platformreplica.models.StudentModel;
import com.secondpartial.platformreplica.models.TeacherModel;

import lombok.Data;

@Data
public class CourseCreationDTO {
  String name;
  String semester;
  String description;
  String image;
  CareerModel career;
  List<StudentModel> students;
  TeacherModel teacher;
}
