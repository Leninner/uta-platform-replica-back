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

  @ManyToMany
  @JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
  private List<StudentModel> students;

  @ManyToOne()
  @JoinColumn(name = "teacher_id")
  private TeacherModel teacher;

  @ManyToOne()
  @JoinColumn(name = "career_id")
  private CareerModel career;
}
