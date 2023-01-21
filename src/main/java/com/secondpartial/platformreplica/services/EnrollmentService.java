package com.secondpartial.platformreplica.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.enrollment.*;
import com.secondpartial.platformreplica.repositories.CourseRepository;
import com.secondpartial.platformreplica.utils.ResponseApiUtil;

@Service
public class EnrollmentService {
    @Autowired
    private ResponseApiUtil responseApiUtil;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    public ResponseEntity<HashMap<String, Object>> enrollStudentToCourse(EnrollStudentDTO enrollStudentDTO) {
        String studentSemester = enrollStudentDTO.getStudent().getSemester();
        String courseSemester = enrollStudentDTO.getCourse().getSemester();
        Long studentID = enrollStudentDTO.getStudent().getId();
        Long courseID = enrollStudentDTO.getCourse().getId();

        if (!studentSemester.equals(courseSemester)) {
            return responseApiUtil.handleResponse("Student and course semesters should be the same",
                    HttpStatus.BAD_REQUEST);
        }

        ArrayList<Long> courses = (ArrayList<Long>) courseRepository.findCoursesIDsByStudentID(studentID);

        if (courses.size() == 5) {
            return responseApiUtil.handleResponse("Student cannot have more than 5 courses", HttpStatus.BAD_REQUEST);
        }

        if (courses.contains(courseID)) {
            return responseApiUtil.handleResponse("Student already enrolled in this course", HttpStatus.BAD_REQUEST);
        }

        courseService.addStudentToCourse(studentID, courseID);
        return responseApiUtil.handleResponse("Student enrolled successfully", HttpStatus.OK);
    }
}