package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.secondpartial.platformreplica.dtos.StudentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.StudentUpdateDTO;
import com.secondpartial.platformreplica.dtos.UserDTO;
import com.secondpartial.platformreplica.enums.SemesterEnum;
import com.secondpartial.platformreplica.enums.RolEnum;
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

    public ResponseEntity<LinkedHashMap<String, Object>> update(String token, Long id, StudentUpdateDTO student,
            String rol) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();

        if (!rol.equals(RolEnum.ADMIN.toString())) {
            response.put("message", "You don't have permission to update a student");
            return ResponseEntity.status(403).body(response);
        }
        StudentModel studentModel = studentRepository.findById(id).orElse(null);

        if (studentRepository.existsById(id)) {
            if (student.getName() != null) {
                studentModel.getUser().setName(student.getName());
            }

            if (student.getEmail() != null) {
                studentModel.getUser().setEmail(student.getEmail());
            }

            if (student.getDni() != null) {
                studentModel.getUser().setDni(student.getDni());
            }

            if (student.getAddress() != null) {
                studentModel.getUser().setAddress(student.getAddress());
            }

            if (student.getPhoneNumber() != null) {
                studentModel.getUser().setPhoneNumber(student.getPhoneNumber());
            }

            if (student.getCityId() != null) {
                studentModel.getUser().setCity(cityRepository.getReferenceById(student.getCityId()));
            }

            if (student.getCareer() != null) {
                studentModel.getUser().setCareer(careerRepository.findByName(student.getCareer()));
            }

            if (student.getSemester() != null) {
                studentModel.setSemester(SemesterEnum.getSemesterEnum(student.getSemester()));
            }

            if (student.getCoursesIds() != null) {
                List<CourseModel> courses = new ArrayList<CourseModel>();
                List<Long> coursesIds = student.getCoursesIds();

                for (Long courseId : coursesIds) {
                    courses.add(courseRepository.getReferenceById(courseId));
                }

                studentModel.setCourses(courses);
            }

            studentRepository.save(studentModel);

            StudentResponseDTO studentResponse = new StudentResponseDTO();
            studentResponse.setId(studentModel.getId());
            studentResponse.setName(studentModel.getUser().getName());
            studentResponse.setEmail(studentModel.getUser().getEmail());
            studentResponse.setDni(studentModel.getUser().getDni());
            studentResponse.setAddress(studentModel.getUser().getAddress());
            studentResponse.setPhoneNumber(studentModel.getUser().getPhoneNumber());
            studentResponse.setCity(studentModel.getUser().getCity().getName());
            studentResponse.setCareer(studentModel.getUser().getCareer().getName());
            studentResponse.setSemester(studentModel.getSemester().toString());


            response.put("student", studentResponse);
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
