package ua.com.foxminded;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import ua.com.foxminded.model.dto.PeriodDto;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.model.dto.WeekScheduleDto;
import ua.com.foxminded.model.enums.Degree;
import ua.com.foxminded.model.enums.Department;
import ua.com.foxminded.model.enums.StudyStatus;
import ua.com.foxminded.service.StudentServiceImpl;
import ua.com.foxminded.service.interfaces.ScheduleItemService;
import ua.com.foxminded.service.interfaces.ScheduleService;
import ua.com.foxminded.service.interfaces.TeacherService;

@Component
public class Report {

    @Autowired
    ScheduleItemService scheduleItemService;

    @Autowired
    StudentServiceImpl studentService;
    
    @Autowired
    TeacherService teacherService;
    
    public void outputTeacherSchedule(String lastName, LocalDate startPeriod) {
        
        scheduleItemService.addScheduleItem(new ScheduleItemDto()
                .setId(UUID.fromString("5e9330ba-162f-45ea-b9de-605ae734f585")));
        scheduleItemService.addScheduleItem(new ScheduleItemDto()
                .setId(UUID.fromString("a8ee0b44-e269-424f-8c33-74d145aaf8d1")));
        scheduleItemService.addScheduleItem(new ScheduleItemDto()
                .setId(UUID.fromString("92f6742f-ab90-4975-9f50-bbf4a3a99c74")));
        scheduleItemService.addScheduleItem(new ScheduleItemDto()
                .setId(UUID.fromString("c38685d8-5c63-46af-8584-6001273a775a")));
                  
 
        System.out.println("--------------Week------------------");
        List<ScheduleItemDto> schedule1 = scheduleItemService
                .findWeekScheduleTeacher(lastName, startPeriod);
                
        schedule1.forEach(e -> System.out.println(e.toString()));
       
    }

    public void outputStudentSchedule(String lastName, LocalDate startPeriod) {

        System.out.println("--------------Week------------------");
        List<ScheduleItemDto> schedule1 = scheduleItemService
                .findWeekScheduleStudent(lastName, startPeriod);
        schedule1.forEach(e -> System.out.println(e.toString()));
       
    }
    
    public void testStudent() {
        System.out.println("Find All");
        studentService.findAllStudent().forEach(System.out::println);

        studentService.addStudent(((StudentDto) new StudentDto()
                .setIdStudent(UUID.fromString("ea9f0dc7-a81f-44ba-a032-b4c3dde4fe18"))
                .setCitizenship("Russia")
                .setIdPerson(UUID.fromString("5c42a0c0-fc26-11ea-adc1-0242ac120002"))
                .setFirstName("efsfdesf")
                .setLastName("dfdsgfd"))
                                           .setStudyStatus(StudyStatus.FINISHED)
                                           .setGrant(new BigDecimal(10))
                                           .setStartOfStudy(LocalDate.of(1999, 02, 02)));
        
        System.out.println("Delete person Id = 3");
        studentService.deleteStudent(UUID.fromString("47357b8a-1971-419e-bdad-1c34bb9c6a26"));

        System.out.println("Update person Id = 1");
        studentService.editStudent(new StudentDto()
                .setIdStudent(UUID.fromString("a17f83c5-a85a-4420-8423-23b86d0463c6"))
                .setCitizenship("Russia")
                                           .setStudyStatus(StudyStatus.FINISHED)
                                           .setGrant(new BigDecimal(10))
                                           .setStartOfStudy(LocalDate.of(1999, 02, 02)));

        System.out.println("Find person Id = 2");
        studentService.findStudent("Loza").forEach(System.out::println);

        System.out.println("Find All Again");
        studentService.findAllStudent().forEach(System.out::println); 
    }
    
    public void testTeacher() {
        
        System.out.println("Find All");
        teacherService.findAllTeacher().forEach(System.out::println);

        System.out.println("Delete teacher Id = 3");
        teacherService.deleteTeacher(UUID.fromString("fd65ef4a-2b85-4730-869e-a70a3364031e"));

        System.out.println("Update person Id = 1");
        teacherService.editTeacher(new TeacherDto()
                .setIdTeacher(UUID.fromString("95d5a598-4fa1-4937-acf7-382a878d19fa"))
                .setDegree(Degree.PROFESSOR)
                .setDepartment(Department.ARCHITECTURE));

        System.out.println("Find person Id = 2");
        teacherService.findTeacher(UUID.fromString("c64e0102-c812-441b-9ef9-c9f478e18745"))
        .forEach(System.out::println);

        System.out.println("Find All Again");
        teacherService.findAllTeacher().forEach(System.out::println);
    }
}
