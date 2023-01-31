package com.secondpartial.platformreplica.dtos;

import java.util.List;

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
  List<MultipartFile> indicationsFiles;
}
