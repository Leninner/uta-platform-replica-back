package com.secondpartial.platformreplica.models;

import java.sql.Timestamp;
import java.util.List;

import com.secondpartial.platformreplica.enums.PartialEnum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "homework")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseModel course;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "indications_file")
    private String indicationsFile;

    @Column(name = "partial")
    @Enumerated(EnumType.STRING)
    private PartialEnum partial;

    @Column(name = "date_init")
    private Timestamp dateInit;

    @Column(name = "date_end")
    private Timestamp dateEnd;

    @Column(name = "status")
    private String status;

    @ManyToMany()
    @JoinTable(name = "homework_student", joinColumns = @JoinColumn(name = "homework_id"), inverseJoinColumns = @JoinColumn(name = "studen_id"))
    private List<StudentModel> students;

}
