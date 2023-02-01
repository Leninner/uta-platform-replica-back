package com.secondpartial.platformreplica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AssignmentResponseDTO {
    private Long id;
    private String name;
    private String partial;
    private String description;
    private String dateInit;
    private String dateEnd;
    private String status;
    private String indicationsFiles;
}
