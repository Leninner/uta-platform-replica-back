package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.TeacherModel;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherModel, Long> {
    @Query(value = "delete from teachers where id = :Id ", nativeQuery = true)
    public void deleteTeacher(@Param("Id") Long Id);
}
