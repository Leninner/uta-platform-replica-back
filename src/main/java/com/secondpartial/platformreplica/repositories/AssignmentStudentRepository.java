package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.AssignmentStudentModel;

@Repository
public interface AssignmentStudentRepository extends JpaRepository<AssignmentStudentModel, Long> {

        @Query(value = "SELECT * FROM assignment_student ast where ast.assignment_id = :assignmentId and ast.student_id = :studentId", nativeQuery = true)
        AssignmentStudentModel findByStudentAndAssignment(@Param("assignmentId") Long assignmentId,
                        @Param("studentId") Long studentId);

}
