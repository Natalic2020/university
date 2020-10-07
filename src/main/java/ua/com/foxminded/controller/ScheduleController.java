package ua.com.foxminded.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.service.interfaces.ScheduleService;
import ua.com.foxminded.service.interfaces.StudentService;
import ua.com.foxminded.service.interfaces.TeacherService;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;
    
    @Autowired
    StudentService studentService;
    
    @Autowired
    TeacherService teacherService;

    @GetMapping()
    public ModelAndView showScheduleStudent(@RequestParam(required = false) String studentUuid,
            @RequestParam(required = false) String teacherUuid) {

        String person = "";
        
        List<ScheduleItemDto> schedule = new ArrayList<>();

        if (studentUuid != null) {
            StudentDto studentDto = studentService.findStudent(UUID.fromString(studentUuid)); 
            person = String.format("student:     %s  %s%n", studentDto.getFirstName(), studentDto.getLastName());
            schedule = scheduleService.findWeekScheduleStudent(UUID.fromString(studentUuid));
        } else if (teacherUuid != null) {
            TeacherDto teachertDto = teacherService.findTeacher(UUID.fromString(teacherUuid)); 
            person = String.format("teacher:     %s  %s%n", teachertDto.getFirstName(), teachertDto.getLastName());
            schedule = scheduleService.findWeekScheduleTeacher(UUID.fromString(teacherUuid));
        }
        ModelAndView scheduleMV = new ModelAndView("schedule" , "person", person);
        scheduleMV.addObject("schedule", schedule);

        return scheduleMV;
    }
}
