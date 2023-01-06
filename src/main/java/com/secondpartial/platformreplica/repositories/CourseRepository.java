package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.secondpartial.platformreplica.models.CourseModel;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Long> {
  @Query(value = "select * from courses c join users u on u.course_id = c.id where u.rol = ?1", nativeQuery = true)
  public Iterable<CourseModel> findByRolAndId(String rol, Long userId);

  @Query(value = "select * from course_student cs inner join courses c on c.id = cs.course_id inner join students s on s.id = cs.student_id where s.user_id = :userId", nativeQuery = true)
  public Iterable<CourseModel> findByIdStudent(@Param("userId") Long userId);

  @Query(value = "select c.id, c.description, c.image, c.name, c.semester, c.career_id, c.teacher_id from courses c inner join teachers t on t.id = c.teacher_id where t.user_id = :userId", nativeQuery = true)
  public Iterable<CourseModel> findByIdTeacher(@Param("userId") Long userId);

  @Query(value = "select id from courses", nativeQuery = true)
  public Iterable<Long> findCoursesIDsByStudentID(Long studentId);
}
