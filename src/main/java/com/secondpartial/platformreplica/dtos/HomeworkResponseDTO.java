package com.secondpartial.platformreplica.dtos;

import java.sql.Date;

import com.secondpartial.platformreplica.models.CourseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class HomeworkResponseDTO {
    Long id;
    String title;
    Long courseId;
    String description;
    String partial;
    Long teacherId;
    String dateInit;
    String dateEnd;
    String status;
    String indicationsFile;

}
