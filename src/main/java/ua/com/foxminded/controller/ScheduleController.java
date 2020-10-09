package ua.com.foxminded.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/student/{uuid}")
    public ModelAndView showScheduleStudent(@PathVariable("uuid") String uuid,
            @RequestParam(required = false) String month) {

        StudentDto studentDto = studentService.findStudent(UUID.fromString(uuid));

        String typPerson = "student";
        String firstName = studentDto.getFirstName();
        String lastName = studentDto.getLastName();

        ModelAndView scheduleMV;
        if (month == null) {
            List<ScheduleItemDto> scheduleWeek = scheduleService.findWeekScheduleStudent(UUID.fromString(uuid));
            scheduleMV = new ModelAndView("scheduleWeek");
            scheduleMV.addObject("schedule", scheduleWeek);
        } else {
            LocalDate date = LocalDate.of(LocalDate.now().getYear(), Month.valueOf(month.toUpperCase()), 1);
            Map<String, List<ScheduleItemDto>> scheduleMonth = scheduleService.findMonthScheduleStudent(
                    UUID.fromString(uuid), date);
            scheduleMV = new ModelAndView("scheduleMonth");
            scheduleMV.addObject("scheduleMonth", scheduleMonth);
        }

        scheduleMV.addObject("typPerson", typPerson);
        scheduleMV.addObject("firstName", firstName);
        scheduleMV.addObject("lastName", lastName);

        return scheduleMV;
    }

    @GetMapping("/teacher/{uuid}")
    public ModelAndView showScheduleTeacher(@PathVariable("uuid") String uuid,
            @RequestParam(required = false) String month) {
        TeacherDto teachertDto = teacherService.findTeacher(UUID.fromString(uuid));
        String typPerson = "teacher";
        String firstName = teachertDto.getFirstName();
        String lastName = teachertDto.getLastName();

        ModelAndView scheduleMV;
        if (month == null) {
            List<ScheduleItemDto> scheduleWeek = scheduleService.findWeekScheduleTeacher(UUID.fromString(uuid));
            scheduleMV = new ModelAndView("scheduleWeek");
            scheduleMV.addObject("schedule", scheduleWeek);
        } else {
            LocalDate date = LocalDate.of(LocalDate.now().getYear(), Month.valueOf(month.toUpperCase()), 1);
            Map<String, List<ScheduleItemDto>> scheduleMonth = scheduleService.findMonthScheduleTeacher(
                    UUID.fromString(uuid), date);
            scheduleMV = new ModelAndView("scheduleMonth");
            scheduleMV.addObject("scheduleMonth", scheduleMonth);
        }

        scheduleMV.addObject("typPerson", typPerson);
        scheduleMV.addObject("firstName", firstName);
        scheduleMV.addObject("lastName", lastName);

        return scheduleMV;
    }
}
