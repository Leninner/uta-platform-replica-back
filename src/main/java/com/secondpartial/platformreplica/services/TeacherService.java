package com.secondpartial.platformreplica.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.secondpartial.platformreplica.repositories.CourseRepository;
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

    @Autowired
    CourseRepository courseRepository;

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
    public ResponseEntity<LinkedHashMap<String, Object>> update(Long id, TeacherUpdateDTO teacher, String rol ) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();

        if (!rol.equals(RolEnum.ADMIN.toString())) {
            response.put("error", "You don't have permission to update this teacher");
            return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.UNAUTHORIZED);
        }

        TeacherModel teacherModel = teacherRepository.findById(id).orElse(null);

        if (teacherRepository.existsById(id)) {
            if (teacher.getName()!= null){
                teacherModel.getUser().setName(teacher.getName());
            }

            if (teacher.getEmail()!= null){
                teacherModel.getUser().setEmail(teacher.getEmail());
            }

            if (teacher.getAddress()!= null){
                teacherModel.getUser().setAddress(teacher.getAddress());
            }

            if (teacher.getPhoneNumber()!= null){
                teacherModel.getUser().setPhoneNumber(teacher.getPhoneNumber());
            }

            if (teacher.getCityId()!= null){
                CityModel city = cityRepository.findById(teacher.getCityId()).orElse(null);
                teacherModel.getUser().setCity(city);
            }

            if (teacher.getDni()!= null){
                teacherModel.getUser().setDni(teacher.getDni());
            }

            if (teacher.getCoursesIds() != null) {
                List<CourseModel> courses = new ArrayList<>();
                for (Long courseId : teacher.getCoursesIds()) {
                    CourseModel course = courseRepository.getReferenceById(courseId);
                    TeacherModel OldTeacher = course.getTeacher();
                    List<CourseModel> oldCourses = OldTeacher.getCourses();
                    oldCourses.remove(course);
                    OldTeacher.setCourses(oldCourses);
                    teacherRepository.save(OldTeacher);

                    course.setTeacher(teacherModel);
                    courses.add(course);
                }
                teacherModel.setCourses(courses);
            }


            teacherRepository.save(teacherModel);

            TeacherResponseDTO teacherResponeDTO = new TeacherResponseDTO();
            List<CoursePartInfoDTO> coursePartInfoDTO = new ArrayList<>();
            List<CourseModel> courses = teacherModel.getCourses();

            for (CourseModel course : courses) {
                coursePartInfoDTO.add(courseService.getCoursePartInfo(course.getId()));
            }

            teacherResponeDTO.setCourses(coursePartInfoDTO);
            teacherResponeDTO.setId(teacherModel.getId());
            teacherResponeDTO.setName(teacherModel.getUser().getName());
            teacherResponeDTO.setEmail(teacherModel.getUser().getEmail());
            teacherResponeDTO.setDni(teacherModel.getUser().getDni());
            teacherResponeDTO.setRol(RolEnum.TEACHER.toString());
            teacherResponeDTO.setAddress(teacherModel.getUser().getAddress());
            teacherResponeDTO.setPhoneNumber(teacherModel.getUser().getPhoneNumber());
            teacherResponeDTO.setCity(teacherModel.getUser().getCity().getName());
            teacherResponeDTO.setProvince(teacherModel.getUser().getCity().getProvince().getName());

            response.put("teacher", teacherResponeDTO);

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
