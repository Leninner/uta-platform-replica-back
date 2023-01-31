package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.AssignmentStudentModel;

@Repository
public interface AssignmentStudentRepository extends JpaRepository<AssignmentStudentModel, Long> {

        @Query(value = "select * from assignment_student ast where ast.student_id = :studentId and ast.assignment_id = :assignmentId", nativeQuery = true)
        AssignmentStudentModel findByStudentAndAssignment(@Param("studentId") Long studentId,
                        @Param("assignmentId") Long assignmentId);

        @Query(value = "SELECT x.* FROM public.assignment_student x where assignment_id = :assignmentId and student_id = :studentId", nativeQuery = true)
        AssignmentStudentModel getAssignmentStudentByStudentIdAndAssignmentId(@Param("studentId") Long studentId,
                        @Param("assignmentId") Long assignmentId);

}
