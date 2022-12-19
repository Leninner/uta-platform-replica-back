package com.secondpartial.platformreplica.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class CourseModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
