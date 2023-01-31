package com.secondpartial.platformreplica.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "assignment_student")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AssignmentStudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(name = "assignment_id")
    private Long assignmentId;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "comment")
    private String comment;

    // antes era un string, ahora es una lista de strings
    @Column(name = "student_files")
    private List<String> studentFiles;

}
