package com.secondpartial.platformreplica.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.secondpartial.platformreplica.enums.PartialEnum;
import com.secondpartial.platformreplica.dtos.HomeworkCreationDTO;
import com.secondpartial.platformreplica.dtos.HomeworkResponseDTO;
import com.secondpartial.platformreplica.models.HomeworkModel;
import com.secondpartial.platformreplica.repositories.CourseRepository;
import com.secondpartial.platformreplica.repositories.HomeworkRepository;

@Service
public class HomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private CourseRepository courseRepository;

    public ResponseEntity<HashMap<String, Object>> getHomeworksByCourseID(Long courseID) {
        ResponseEntity<HashMap<String, Object>> response = null;
        ArrayList<HomeworkModel> homeworks = (ArrayList<HomeworkModel>) homeworkRepository
                .findHomeworksByCourseID(courseID);

        if (homeworks.size() > 0) {
            ArrayList<HomeworkResponseDTO> homeworksResponse = new ArrayList<>();
            for (HomeworkModel homework : homeworks) {
                HomeworkResponseDTO homeworkResponse = new HomeworkResponseDTO() {
                    {
                        setId(homework.getId());
                        setCourseId(homework.getCourse().getId());
                        setTitle(homework.getTitle());
                        setDescription(homework.getDescription());
                        setDateInit(String.valueOf(homework.getDateInit()));
                        setDateEnd(String.valueOf(homework.getDateEnd()));
                        setPartial(homework.getPartial().getParcial());
                        setStatus(getStatus());
                        setIndicationsFile(homework.getIndicationsFile());
                    }
                };
                homeworksResponse.add(homeworkResponse);
            }
            response = new ResponseEntity<HashMap<String, Object>>(new HashMap<String, Object>() {
                {
                    put("message", "Homeworks found");
                    put("homeworks", homeworksResponse);
                }
            }, null, 200);
        } else {
            response = new ResponseEntity<HashMap<String, Object>>(new HashMap<String, Object>() {
                {
                    put("message", "No homeworks found");
                }
            }, null, 404);
        }
        return response;
    }

    public Boolean existsHomeworkByCourseIdAndTitleAndPartial(Long courseId, String title, PartialEnum partial) {
        return homeworkRepository.existsHomeworkByCourseIdAndTitleAndPartial(courseId, title, partial);
    }

    public ResponseEntity<HashMap<String, Object>> createHomework(HomeworkCreationDTO homework) {

        HashMap<String, Object> response = new HashMap<>();

        if (existsHomeworkByCourseIdAndTitleAndPartial(homework.getCourseId(), homework.getTitle(),
                PartialEnum.getPartialEnum(homework.getPartial()))) {
            response.put("message", "Homework already exists");
            response.put("status", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        HomeworkModel homeworkModel = new HomeworkModel() {
            {
                setCourse(courseRepository.getReferenceById(homework.getCourseId()));
                setTitle(homework.getTitle());
                setDescription(homework.getDescription());
                setDateInit(Timestamp.valueOf(homework.getDateInit().replace('T', ' ')));
                setDateEnd(Timestamp.valueOf(homework.getDateEnd().replace('T', ' ')));
                setPartial(PartialEnum.getPartialEnum(homework.getPartial()));
                setStatus(homework.getStatus());
                setIndicationsFile(homework.getIndicationsFile());
            }
        };
        homeworkRepository.save(homeworkModel);
        response.put("message", "Homework created");
        return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CREATED);
    }

}
