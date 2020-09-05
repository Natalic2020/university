package ua.com.foxminded.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.WeekScheduleDto;
import ua.com.foxminded.service.interfaces.ScheduleService;

@Component
public class Report {

    @Autowired
    ScheduleService scheduleService;

    public void outputTeacherSchedule(String lastName, LocalDate startPeriod) {

        System.out.println("--------------Week------------------");
        List<WeekScheduleDto> schedule1 = scheduleService.findWeekScheduleTeacher(lastName, startPeriod);
        schedule1.forEach(e -> System.out.println(e.toString()));
        System.out.println("--------------Month------------------");
        List<WeekScheduleDto> schedule = scheduleService.findMonthScheduleTeacher(lastName, startPeriod);
        schedule.forEach(e -> System.out.println(e.toString()));
    }

    public void outputStudentSchedule(String lastName, LocalDate startPeriod) {

        System.out.println("--------------Week------------------");
        List<WeekScheduleDto> schedule1 = scheduleService.findWeekScheduleStudent(lastName, startPeriod);
        schedule1.forEach(e -> System.out.println(e.toString()));
        System.out.println("--------------Month------------------");
        List<WeekScheduleDto> schedule = scheduleService.findMonthScheduleStudent(lastName, startPeriod);
        schedule.forEach(e -> System.out.println(e.toString()));
    }
}
