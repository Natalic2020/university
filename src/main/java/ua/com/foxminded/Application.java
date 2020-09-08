package ua.com.foxminded;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import ua.com.foxminded.config.ApplicationConfig;
import ua.com.foxminded.dao.DatabaseInitializer;
import ua.com.foxminded.dao.TablesInitializer;
import ua.com.foxminded.service.ScheduleGenerator;

@Component
public class Application {

    @Autowired
    ScheduleGenerator scheduleGenerator;
    
    @Autowired
    Report report;
    
    @Autowired
    TablesInitializer tableInitializer;
    
    @Autowired
    DatabaseInitializer dbInitializer;
    
    public static void main(String args[]) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        Application bean = context.getBean(Application.class);
        bean.createDB();
        bean.run();
        context.close();
    }
    
    public void run() {

        scheduleGenerator.testStudent();
        scheduleGenerator.testTeacher();
        scheduleGenerator.fillSchedule();

        System.out.println("********TeacherSchedule******************");
        System.out.println("Ivaniv");
        report.outputTeacherSchedule( "Ivaniv", LocalDate.of(2019, 9, 12));
        System.out.println("***********************************");
        System.out.println("********StudentSchedule******************");
        System.out.println("Vano");
        report.outputStudentSchedule( "Vano", LocalDate.of(2019, 9, 4));
    }  
    
    public void createDB() {
        tableInitializer.createTables();
        tableInitializer.fillTables();
    }
}
