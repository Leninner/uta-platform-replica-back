package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.dtos.CoursePartInfoDTO;
import com.secondpartial.platformreplica.dtos.TeacherResponseDTO;
import com.secondpartial.platformreplica.dtos.TeacherUpdateDTO;
import com.secondpartial.platformreplica.dtos.UserDTO;
import com.secondpartial.platformreplica.enums.RolEnum;
import com.secondpartial.platformreplica.models.CityModel;
import com.secondpartial.platformreplica.models.CourseModel;
import com.secondpartial.platformreplica.models.TeacherModel;
import com.secondpartial.platformreplica.repositories.CityRepository;
import com.secondpartial.platformreplica.repositories.TeacherRepository;

@Service
public class TeacherService extends CrudHandler {
    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public ResponseEntity<LinkedHashMap<String, Object>> create(UserDTO user) {
        return userService.create(user);
    }

    @Override
    public ResponseEntity<LinkedHashMap<String, Object>> get(Long id) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        TeacherModel teacher = teacherRepository.findById(id).orElse(null);

        if (teacherRepository.existsById(id)) {
            TeacherResponseDTO teacherResponeDTO = new TeacherResponseDTO();
            List<CoursePartInfoDTO> coursePartInfoDTO = new ArrayList<>();
            List<CourseModel> courses = teacher.getCourses();

            for (CourseModel course : courses) {
                coursePartInfoDTO.add(courseService.getCoursePartInfo(course.getId()));
            }

            teacherResponeDTO.setCourses(coursePartInfoDTO);
            teacherResponeDTO.setId(teacher.getId());
            teacherResponeDTO.setName(teacher.getUser().getName());
            teacherResponeDTO.setEmail(teacher.getUser().getEmail());
            teacherResponeDTO.setDni(teacher.getUser().getDni());
            teacherResponeDTO.setRol(RolEnum.TEACHER.toString());
            teacherResponeDTO.setAddress(teacher.getUser().getAddress());
            teacherResponeDTO.setPhoneNumber(teacher.getUser().getPhoneNumber());
            teacherResponeDTO.setCity(teacher.getUser().getCity().getName());
            teacherResponeDTO.setProvince(teacher.getUser().getCity().getProvince().getName());

            response.put("teacher", teacherResponeDTO);
            return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
        } else {
            response.put("error", "Teacher not found");
            return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<LinkedHashMap<String, Object>> update(Long id, TeacherUpdateDTO teacher) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        TeacherModel teacherModel = teacherRepository.findById(id).orElse(null);

        if (teacherRepository.existsById(id)) {
            teacherModel.getUser().setName(teacher.getName());
            teacherModel.getUser().setEmail(teacher.getEmail());
            teacherModel.getUser().setAddress(teacher.getAddress());
            teacherModel.getUser().setPhoneNumber(teacher.getPhoneNumber());

            CityModel city = cityRepository.findById(teacher.getCityId()).orElse(null);
            teacherModel.getUser().setCity(city);

            teacherModel.getUser().setDni(teacher.getDni());

            teacherRepository.save(teacherModel);

            this.get(id);

            response.put("message", "Teacher updated successfully");
            return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
        } else {
            response.put("error", "Teacher not found");
            return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<LinkedHashMap<String, Object>> delete(Long id) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        TeacherModel teacher = teacherRepository.findById(id).orElse(null);

        if (teacherRepository.existsById(id)) {
            userService.delete(teacher.getUser().getId());
            response.put("message", "Teacher deleted successfully");
            return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
        } else {
            response.put("error", "Teacher not found");
            return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }
}
