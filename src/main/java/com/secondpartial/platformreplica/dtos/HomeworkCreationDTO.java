package com.secondpartial.platformreplica.dtos;

import lombok.Data;

@Data
public class HomeworkCreationDTO {
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
