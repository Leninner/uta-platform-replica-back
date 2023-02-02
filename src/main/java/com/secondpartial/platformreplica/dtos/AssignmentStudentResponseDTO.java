package com.secondpartial.platformreplica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AssignmentStudentResponseDTO {
    private String studentName;
    private Double grade;
    private String comment;
    private String studentFiles;
    private Boolean isCompleted;
    private Long id;
}
