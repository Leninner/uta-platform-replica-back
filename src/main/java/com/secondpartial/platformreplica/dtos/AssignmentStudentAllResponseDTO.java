package com.secondpartial.platformreplica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AssignmentStudentAllResponseDTO {
    private String studentName;
    private Double grade;
    private String comment;
    private String studentFiles;
    private String assignmentName;
    private String partial;
    private String indicationsFiles;
    private String description;
    private String dateInit;
    private String dateEnd;
    private String status;
}
