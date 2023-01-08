package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.CourseStudentModel;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudentModel, Long> {

}
