package com.secondpartial.platformreplica.dtos;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AssignmentStudentModifyDTO implements java.io.Serializable {
    List<MultipartFile> studentFiles;
}