package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {
    @Query(value = "select * from students s where s.id = :studentId", nativeQuery = true)
    public Iterable<StudentModel> findByStudentId(@Param("studentId") Long studentId);

    @Query(value = "select * from students s where s.user_id = :userId ", nativeQuery = true)
    public StudentModel findByUserId(@Param("userId") Long userId);

}