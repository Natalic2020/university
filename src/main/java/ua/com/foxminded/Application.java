package ua.com.foxminded;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import ua.com.foxminded.config.ApplicationConfig;
import ua.com.foxminded.dao.TablesInitializer;
import ua.com.foxminded.dao.entity.TimeSlot;
import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.model.dto.PeriodDto;
import ua.com.foxminded.model.dto.RoomDto;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.dto.SubjectDto;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.model.dto.TimeSlotDto;
import ua.com.foxminded.model.dto.WeekScheduleDto;
import ua.com.foxminded.model.enums.DayOfWeek;
import ua.com.foxminded.model.enums.Degree;
import ua.com.foxminded.model.enums.Department;
import ua.com.foxminded.model.enums.Specialty;
import ua.com.foxminded.model.enums.StudyStatus;
import ua.com.foxminded.service.Report;
import ua.com.foxminded.service.ScheduleGenerator;
import ua.com.foxminded.service.ScheduleService;
import ua.com.foxminded.service.StudentService;
import ua.com.foxminded.service.TeacherService;

public class Application {

    public static void main(String args[]) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

//      DatabaseInitializer dbInitializer = context.getBean("databaseInitializer", DatabaseInitializer.class);
//      dbInitializer.createDB();  

        TablesInitializer tableInitializer = context.getBean("tablesInitializer", TablesInitializer.class);
        tableInitializer.createTables();

        ScheduleGenerator scheduleGenerator = new ScheduleGenerator();
        StudentService studentService = (StudentService) context.getBean("studentService");
        
        scheduleGenerator.testStudent(studentService);

        TeacherService teacherService = (TeacherService) context.getBean("teacherService");
        scheduleGenerator.testTeacher(teacherService);
       
       
        scheduleGenerator.fillSchedule(context);
        Report report = new Report();
        System.out.println("********TeacherSchedule******************");
        System.out.println("Ivaniv");
        report.outputTeacherSchedule(context, "Ivaniv", LocalDate.of(2019, 9, 2), LocalDate.of(2019, 10, 2));
        System.out.println("***********************************");
        System.out.println("********StudentSchedule******************");
        System.out.println("Vano");
        report.outputStudentSchedule(context, "Vano", LocalDate.of(2019, 9, 3), LocalDate.of(2019, 10, 2));
        context.close();
    }
}
