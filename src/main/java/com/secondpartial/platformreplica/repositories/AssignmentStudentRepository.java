package com.secondpartial.platformreplica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.models.AssignmentModel;
import com.secondpartial.platformreplica.models.AssignmentStudentModel;
import com.secondpartial.platformreplica.models.StudentModel;

@Repository
public interface AssignmentStudentRepository extends JpaRepository<AssignmentStudentModel, Long> {

    @Query(value = "select * from assignment_student where student_id = :studentId and assignment_id = :assignmentId", nativeQuery = true)
    AssignmentStudentModel findByStudentAndAssignment(StudentModel student, AssignmentModel assignment);

}
