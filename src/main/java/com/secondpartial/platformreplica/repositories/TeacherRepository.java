package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.TeacherModel;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherModel, Long> {

}
