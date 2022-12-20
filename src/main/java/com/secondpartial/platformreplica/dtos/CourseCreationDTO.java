package com.secondpartial.platformreplica.dtos;

import java.util.List;
import com.secondpartial.platformreplica.models.UserModel;

public class CourseCreationDTO {
  String name;
  String semester;
  String description;
  String image;
  List<UserModel> users;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSemester() {
    return semester;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public List<UserModel> getUsers() {
    return users;
  }

  public void setUsers(List<UserModel> users) {
    this.users = users;
  }
}
