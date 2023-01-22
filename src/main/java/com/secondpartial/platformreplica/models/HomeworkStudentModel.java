package com.secondpartial.platformreplica.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "homework_id")
    private HomeworkModel homework;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentModel student;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "student_file")
    private String studentFile;

    @Column(name = "comment")
    private String comment;

}
