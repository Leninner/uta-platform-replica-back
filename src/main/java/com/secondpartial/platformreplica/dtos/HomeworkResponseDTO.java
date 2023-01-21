package com.secondpartial.platformreplica.dtos;

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
    String dateInit;
    String dateEnd;
    String status;
    String indicationsFile;

}
