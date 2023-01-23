package com.secondpartial.platformreplica.models;

import java.util.List;

import com.secondpartial.platformreplica.enums.PartialEnum;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "assignments")
@Data
public class AssignmentModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = true)
  private String description;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PartialEnum partial;

  @Column(nullable = false)
  private String dateInit;

  @Column(nullable = false)
  private String dateEnd;

  @ManyToOne()
  @JoinColumn(name = "course_id")
  private CourseModel course;

  @ManyToOne()
  @JoinColumn(name = "teacher_id")
  private TeacherModel teacher;

  @ManyToMany()
  @JoinTable(name = "assignment_student", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "assignment_id"))
  private List<StudentModel> students;
}
