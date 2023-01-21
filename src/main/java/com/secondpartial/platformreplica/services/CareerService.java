package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.repositories.CareerRepository;
import com.secondpartial.platformreplica.enums.SemesterEnum;
import com.secondpartial.platformreplica.models.CareerModel;

@Service
public class CareerService {
    @Autowired
    CareerRepository careerRepository;
    @Autowired
    CourseService courseService;

    public ResponseEntity<LinkedHashMap<String, LinkedHashMap<String, Object>>> getAllCarreersWithSemestersAndCourses() {
        LinkedHashMap<String, LinkedHashMap<String, Object>> response = new LinkedHashMap<>();
        LinkedHashMap<String, Object> semesters = new LinkedHashMap<>();
        ArrayList<CareerModel> careers = new ArrayList<>();
        careers = (ArrayList<CareerModel>) careerRepository.findAll();

        for (CareerModel career : careers) {
            semesters = courseService.getCoursesOfEachSemester(career.getId());
            semesters = orderSemesters(semesters);
            response.put(career.getName(), semesters);
        }
        return new ResponseEntity<LinkedHashMap<String, LinkedHashMap<String, Object>>>(
                response, HttpStatus.OK);
    }

    public LinkedHashMap<String, Object> orderSemesters(LinkedHashMap<String, Object> semesters) {
        LinkedHashMap<String, Object> orderedSemesters = new LinkedHashMap<>();
        String[] semestersArray = SemesterEnum.getSemestersEnumToString();

        for (String semester : semestersArray) {
            orderedSemesters.put(semester, semesters.get(semester));
        }

        return orderedSemesters;
    }

}
