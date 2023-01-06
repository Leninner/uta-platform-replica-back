package com.secondpartial.platformreplica.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "careers")
@Data
public class CareerModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CourseModel> courses;

  @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<UserModel> users;
}
