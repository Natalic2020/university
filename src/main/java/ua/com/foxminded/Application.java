package ua.com.foxminded;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.com.foxminded.dao.DatabaseInitializer;
import ua.com.foxminded.dao.TablesInitializer;
import ua.com.foxminded.model.dto.Group;

public class Application {

    public static void main(String[] args) {
        
        ApplicationContext context =  new ClassPathXmlApplicationContext("Spring-Module.xml");
        
        DatabaseInitializer dbInitializer = context.getBean("databaseInitializer", DatabaseInitializer.class);
        dbInitializer.createDB();       
        
        TablesInitializer tableInitializer   = context.getBean("tablesInitializer", TablesInitializer.class);
        
        tableInitializer.createTables();
        tableInitializer.insertGroup(new Group().setId(1).setName("gr1"));
        
    }
}
