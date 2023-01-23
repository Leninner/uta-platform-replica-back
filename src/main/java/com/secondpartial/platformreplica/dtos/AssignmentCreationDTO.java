package com.secondpartial.platformreplica.dtos;

import lombok.Data;

@Data
public class AssignmentCreationDTO {
  Long courseId;
  String name;
  String description;
  String partial;
  String dateInit;
  String dateEnd;
  String status;
  String indicationsFile;
}
