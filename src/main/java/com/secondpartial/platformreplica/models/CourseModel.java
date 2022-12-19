package com.secondpartial.platformreplica.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class CourseModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String semester;

  @Column(nullable = true)
  private String description;

  @Column(nullable = true)
  private String image;

  @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<UserModel> users;

  public CourseModel(String name, String semester, String description, String image, List<UserModel> users) {
    this.name = name;
    this.semester = semester;
    this.description = description;
    this.image = image;
    this.users = users;
  }
}
