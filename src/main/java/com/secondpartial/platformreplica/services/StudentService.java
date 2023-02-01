package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.StudentUpdateDTO;
import com.secondpartial.platformreplica.dtos.UserDTO;
import com.secondpartial.platformreplica.enums.SemesterEnum;
import com.secondpartial.platformreplica.models.CourseModel;
import com.secondpartial.platformreplica.models.StudentModel;
import com.secondpartial.platformreplica.repositories.CareerRepository;
import com.secondpartial.platformreplica.repositories.CityRepository;
import com.secondpartial.platformreplica.repositories.CourseRepository;
import com.secondpartial.platformreplica.repositories.StudentRepository;

@Service
public class StudentService {
    @Autowired
    CourseService courseService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserService userService;

    @Autowired
    CareerRepository careerRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CourseRepository courseRepository;

    public ResponseEntity<LinkedHashMap<String, Object>> create(UserDTO user) {
        return userService.create(user);
    }

    public ResponseEntity<LinkedHashMap<String, Object>> get(Long id) {
        return userService.get(id);
    }

    public ResponseEntity<LinkedHashMap<String, Object>> update(String token, Long id, StudentUpdateDTO student) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
        StudentModel studentModel = studentRepository.findById(id).orElse(null);

        if (studentRepository.existsById(id)) {
            studentModel.getUser().setName(student.getName());
            studentModel.getUser().setEmail(student.getEmail());
            studentModel.getUser().setDni(student.getDni());
            studentModel.getUser().setAddress(student.getAddress());
            studentModel.getUser().setPhoneNumber(student.getPhoneNumber());
            studentModel.getUser().setCity(cityRepository.getReferenceById(student.getCityId()));

            studentModel.getUser().setCareer(careerRepository.findByName(student.getCareer()));
            studentModel.setSemester(SemesterEnum.getSemesterEnum(student.getSemester()));

            List<CourseModel> courses = new ArrayList<CourseModel>();
            List<Long> coursesIds = student.getCoursesIds();

            for (Long courseId : coursesIds) {
                courses.add(courseRepository.getReferenceById(courseId));
            }

            studentModel.setCourses(courses);

            studentRepository.save(studentModel);

            this.get(id);

            response.put("message", "Student updated");
            return ResponseEntity.status(200).body(response);
        } else {
            response.put("message", "Student not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    public ResponseEntity<LinkedHashMap<String, Object>> delete(Long id) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
        StudentModel studentModel = studentRepository.findById(id).orElse(null);

        if (studentRepository.existsById(id)) {
            studentRepository.delete(studentModel);

            response.put("message", "Student deleted");
            return new ResponseEntity<LinkedHashMap<String, Object>>(response, null, 200);
        } else {
            response.put("message", "Student not found");
            return new ResponseEntity<LinkedHashMap<String, Object>>(response, null, 404);
        }
    }
}
