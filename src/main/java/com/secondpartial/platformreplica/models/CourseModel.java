package com.secondpartial.platformreplica.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
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
}
