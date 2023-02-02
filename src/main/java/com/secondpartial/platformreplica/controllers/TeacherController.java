package com.secondpartial.platformreplica.controllers;

import com.secondpartial.platformreplica.dtos.TeacherUpdateDTO;
import com.secondpartial.platformreplica.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

        @Autowired
        private TeacherService teacherService;

        @PutMapping("/{id}")
        public ResponseEntity<LinkedHashMap<String, Object>> Update(@PathVariable("id") Long id,
                                                                    @ModelAttribute TeacherUpdateDTO teacher,
                                                                    @RequestHeader String rol) {
            return teacherService.update( id, teacher, rol);
        }
}
