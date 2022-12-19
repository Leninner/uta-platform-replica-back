package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.secondpartial.platformreplica.models.CourseModel;

@Repository
public interface CourseRepository extends CrudRepository<CourseModel, Long> {
  @Query(value = "select * from courses c join users u on u.course_id = c.id where u.rol = ?1", nativeQuery = true)
  public Iterable<CourseModel> findByRol(String rol);
}
