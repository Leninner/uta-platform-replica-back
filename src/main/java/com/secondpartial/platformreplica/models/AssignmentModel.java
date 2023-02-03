package com.secondpartial.platformreplica.models;

import java.sql.Timestamp;
import java.util.List;

import com.secondpartial.platformreplica.enums.PartialEnum;
import com.secondpartial.platformreplica.enums.StatusEnum;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "assignments")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
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
  private Timestamp dateInit;

  @Column(nullable = false)
  private Timestamp dateEnd;

  @ManyToOne()
  @JoinColumn(name = "course_id")
  private CourseModel course;

  @Column(name = "status", nullable = true)
  @Enumerated(EnumType.STRING)
  private StatusEnum status;

  @Column(name = "indications_files", length = 1024, nullable = true)
  private String indicationsFiles;

  @ManyToMany()
  @JoinTable(name = "assignment_student", joinColumns = @JoinColumn(name = "assignment_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
  private List<StudentModel> students;
}
