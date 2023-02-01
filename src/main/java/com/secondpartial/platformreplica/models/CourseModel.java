package com.secondpartial.platformreplica.models;

import java.util.List;

import com.secondpartial.platformreplica.enums.SemesterEnum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private SemesterEnum semester;

  @Column(nullable = true)
  private String description;

  @Column(nullable = true)
  private String image;

  @ManyToMany(mappedBy = "courses")
  private List<StudentModel> students;

  @ManyToOne()
  @JoinColumn(name = "teacher_id", nullable = true)
  private TeacherModel teacher;

  @ManyToOne()
  @JoinColumn(name = "career_id")
  private CareerModel career;

  @OneToMany(mappedBy = "course")
  private List<AssignmentModel> assignments;
}
