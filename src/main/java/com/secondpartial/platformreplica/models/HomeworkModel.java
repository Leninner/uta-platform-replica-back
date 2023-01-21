package com.secondpartial.platformreplica.models;

import java.sql.Timestamp;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @OneToMany(mappedBy = "homework")
    private List<HomeworkStudentModel> homeworkStudents;

}
