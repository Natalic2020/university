package ua.com.foxminded.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.service.interfaces.ScheduleService;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping()
    public ModelAndView showScheduleStudent(@RequestParam(required = false) String studentUuid,
            @RequestParam(required = false) String teacherUuid) {

        List<ScheduleItemDto> schedule = new ArrayList<>();

        if (studentUuid != null) {
            schedule = scheduleService.findWeekScheduleStudent(UUID.fromString(studentUuid));
        } else if (teacherUuid != null) {
            schedule = scheduleService.findWeekScheduleTeacher(UUID.fromString(teacherUuid));
        }
        ModelAndView scheduleMV = new ModelAndView("schedule");
        scheduleMV.addObject("schedule", schedule);

        return scheduleMV;
    }
}
