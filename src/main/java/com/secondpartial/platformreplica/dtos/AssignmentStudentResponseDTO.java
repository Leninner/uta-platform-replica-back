package com.secondpartial.platformreplica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AssignmentStudentResponseDTO {
    private String name;
    private String partial;
    private String indicationsFile;
    private String description;
    private String dateInit;
    private String dateEnd;
    private String status;
    private Double grade;
    private String comment;
    private String studentFile;
}
