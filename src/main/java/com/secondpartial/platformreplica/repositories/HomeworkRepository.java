package com.secondpartial.platformreplica.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.enums.PartialEnum;
import com.secondpartial.platformreplica.models.HomeworkModel;

@Repository
public interface HomeworkRepository extends JpaRepository<HomeworkModel, Long> {

    @Query(value = "select * from homeworks h where h.course_id = :courseID", nativeQuery = true)
    public Iterable<HomeworkModel> findHomeworksByCourseID(@Param("courseID") Long courseID);

    @Query(value = "select * from homeworks h where h.course_id = :courseID and h.title = :title and h.partial = :partial ", nativeQuery = true)
    public Boolean existsHomeworkByCourseIdAndTitleAndPartial(@Param("courseID") Long courseId,
            @Param("title") String title, @Param("partial") PartialEnum partial);

}
