package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.AssignmentModel;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentModel, Long>{
    @Query(value = "select * from assignments a where a.course_id = :courseId", nativeQuery = true)
    public Iterable<AssignmentModel> findByCourseId(@Param("courseId") Long courseId);
}
