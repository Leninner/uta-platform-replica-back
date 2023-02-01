package com.secondpartial.platformreplica.dtos;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AssignmentCreationDTO implements java.io.Serializable {
  Long courseId;
  String name;
  String description;
  String partial;
  String dateInit;
  String dateEnd;
  String status;
  MultipartFile[] indicationsFiles;
}
