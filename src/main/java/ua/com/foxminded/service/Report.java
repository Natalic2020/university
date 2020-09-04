package ua.com.foxminded.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.service.interfaces.ScheduleService;

@Component
public class Report {
    
    @Autowired
    ScheduleService scheduleService;
    
    public void outputTeacherSchedule( String lastName, LocalDate startPeriod, LocalDate finishPeriod) {
        
//        ScheduleService scheduleService = (ScheduleService) context.getBean("scheduleService");
        for (LocalDate date = startPeriod; date.isBefore(finishPeriod); date = date.plusDays(1)) {
            outputTeacherSchedule(   lastName,  date);
        }
        
        List<ScheduleItemDto> schedule = scheduleService.findScheduleTeacher("Ivaniv", LocalDate.of(2019, 9, 2));
        schedule.forEach(e -> System.out.println(e.toString())); 
    }
    
    public void outputTeacherSchedule( String lastName, LocalDate startPeriod) {
        
//        ScheduleService scheduleService = (ScheduleService) context.getBean("scheduleService");
        List<ScheduleItemDto> schedule = scheduleService.findScheduleTeacher(lastName, startPeriod);
        if(schedule.size()>0) {
            System.out.println(startPeriod);
        }
        
        schedule.forEach(e -> System.out.println(e.toString())); 
    }

   public void outputStudentSchedule( String lastName, LocalDate startPeriod, LocalDate finishPeriod) {
    
//    ScheduleService scheduleService = (ScheduleService) context.getBean("scheduleService");
    for (LocalDate date = startPeriod; date.isBefore(finishPeriod); date = date.plusDays(1)) {
        outputStudentSchedule(  lastName,  date);
    }
    
    List<ScheduleItemDto> schedule = scheduleService.findScheduleStudent("Ivaniv", LocalDate.of(2019, 9, 2));
    schedule.forEach(e -> System.out.println(e.toString())); 
}

   public void outputStudentSchedule( String lastName, LocalDate startPeriod) {
    
//    ScheduleService scheduleService = (ScheduleService) context.getBean("scheduleService");
    List<ScheduleItemDto> schedule = scheduleService.findScheduleStudent(lastName, startPeriod);
    if(schedule.size()>0) {
        System.out.println(startPeriod);
    }
    
    schedule.forEach(e -> System.out.println(e.toString())); 
}



}
