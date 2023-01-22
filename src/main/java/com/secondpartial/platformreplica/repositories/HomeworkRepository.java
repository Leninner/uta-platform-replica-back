package com.secondpartial.platformreplica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secondpartial.platformreplica.enums.PartialEnum;
import com.secondpartial.platformreplica.models.HomeworkModel;

@Repository
public interface HomeworkRepository extends JpaRepository<HomeworkModel, Long> {

    @Query(value = "select * from homework h where h.course_id = :courseID", nativeQuery = true)
    public Iterable<HomeworkModel> findHomeworksByCourseID(@Param("courseID") Long courseID);

    @Query(value = "select * from homework h where h.course_id = :courseID and h.title = :title and h.partial = :partial ", nativeQuery = true)
    public List<HomeworkModel> getHomeworkByCourseIdAndTitleAndPartial(@Param("courseID") Long courseId,
            @Param("title") String title, @Param("partial") PartialEnum partial);

}
