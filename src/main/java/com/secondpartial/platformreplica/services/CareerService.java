package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.repositories.CareerRepository;
import com.secondpartial.platformreplica.models.CareerModel;

@Service
public class CareerService {
    @Autowired
    CareerRepository careerRepository;
    @Autowired
    CourseService courseService;

    public ResponseEntity<HashMap<String, HashMap<String, Object>>> getAllCarreersWithSemestersAndCourses() {
        HashMap<String, HashMap<String, Object>> response = new HashMap<String, HashMap<String, Object>>();
        HashMap<String, Object> semesters = new HashMap<>();
        ArrayList<CareerModel> careers = new ArrayList<>();
        careers = (ArrayList<CareerModel>) careerRepository.findAll();

        for (CareerModel career : careers) {
            semesters = courseService.getCoursesOfEachSemester(career.getId());
            response.put(career.getName(), semesters);
        }

        return new ResponseEntity<HashMap<String, HashMap<String, Object>>>(
                response, HttpStatus.OK);
    }
}
