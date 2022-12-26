package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {
  
}
