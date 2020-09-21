package ua.com.foxminded;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import ua.com.foxminded.config.ApplicationConfig;
import ua.com.foxminded.dao.DatabaseInitializer;
import ua.com.foxminded.dao.TablesInitializer;

@Component
public class Application {
  
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

        report.testStudent();
        report.testTeacher();

    }  
    
    public void createDB() {
        tableInitializer.createTables();
        tableInitializer.fillTables();
    }
}
