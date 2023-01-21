package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.HomeworkStudentModel;

@Repository
public interface HomeworkStudentRepository extends JpaRepository<HomeworkStudentModel, Long> {

}
