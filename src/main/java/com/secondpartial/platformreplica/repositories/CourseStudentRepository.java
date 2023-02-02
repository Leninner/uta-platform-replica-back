package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.CourseStudentModel;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudentModel, Long> {
    @Query(value = "select * from course_student cs where cs.course_id = :courseId", nativeQuery = true)
    public Iterable<CourseStudentModel> findByCourseId(@Param("courseId") Long courseId);

    @Query(value = "select * from course_student cs where cs.student_id = :studentId", nativeQuery = true)
    public Iterable<CourseStudentModel> findByStudentId(@Param("studentId") Long studentId);

}
