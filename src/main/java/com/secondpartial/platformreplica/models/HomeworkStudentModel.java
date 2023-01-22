package com.secondpartial.platformreplica.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "homework_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkStudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(name = "homework_id")
    private Long homework;

    @Column(name = "student_id")
    private Long student;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "student_file")
    private String studentFile;

    @Column(name = "comment")
    private String comment;

}
