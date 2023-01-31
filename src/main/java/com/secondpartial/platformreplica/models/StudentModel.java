package com.secondpartial.platformreplica.models;

import java.util.List;

import com.secondpartial.platformreplica.enums.SemesterEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;

  @OneToOne()
  @JoinColumn(name = "user_id")
  private UserModel user;

  @ManyToMany()
  @JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
  private List<CourseModel> courses;

  @ManyToMany(mappedBy = "students")
  private List<AssignmentModel> assignments;

  @Column(nullable = true)
  @Enumerated(EnumType.STRING)
  private SemesterEnum semester;
}
