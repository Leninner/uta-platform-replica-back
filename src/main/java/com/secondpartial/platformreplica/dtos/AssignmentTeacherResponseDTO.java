package com.secondpartial.platformreplica.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AssignmentTeacherResponseDTO {
    private Long id;
    private String assignmentName;
    private String partial;
    private String indicationsFiles;
    private String description;
    private String dateInit;
    private String dateEnd;
    private String status;
    private List<AssignmentStudentResponseDTO> students;
}
