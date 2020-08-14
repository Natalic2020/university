package ua.com.foxminded;


import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.com.foxminded.config.ApplicationConfig;
import ua.com.foxminded.dao.DatabaseInitializer;
import ua.com.foxminded.dao.TablesInitializer;
import ua.com.foxminded.model.dto.Group;
import ua.com.foxminded.model.dto.Person;
import ua.com.foxminded.service.GroupService;
import ua.com.foxminded.service.PersonService;
import ua.com.foxminded.service.StudentService;

public class Application {

    public static void main(String args[]) {
        
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        
        TablesInitializer tableInitializer   = context.getBean("tablesInitializer", TablesInitializer.class);    
        tableInitializer.createTables();
      
        PersonService personService = (PersonService) context.getBean("personService");
        GroupService groupService = (GroupService) context.getBean("groupService");
        StudentService studentService = (StudentService) context.getBean("studentService");
        
        
         UUID uuid1 = UUID.randomUUID();
         UUID uuid2 = UUID.randomUUID();
         UUID uuid3 = UUID.randomUUID();
        
        Person yashwant = new Person(uuid1, "Yashwant", "Chavan");
        Person mahesh = new Person(uuid2, "Mahesh", "Patil");
        Person vishal = new Person(uuid3, "Vishal", "Naik");
 
        personService.addPerson(yashwant);
        personService.addPerson(mahesh);
        personService.addPerson(vishal);
 
        System.out.println("Find All");
 
        personService.findAllPerson().forEach(System.out::println);
        
       System.out.println("Delete person Id = 3");

        personService.deletePerson(uuid3);
 
       yashwant.setFirstName("Yashwant - Updated");
       yashwant.setLastName("Chavan - Updated");

       System.out.println("Update person Id = 1");
       personService.editPerson(yashwant, uuid1);
 
       System.out.println("Find person Id = 2");
        Person person = personService.findPerson(uuid2);
        System.out.println(person);
 
        System.out.println("Find All Again");
        
        personService.findAllPerson().forEach(System.out::println);
 
        context.close();
    }
 

    
    
    
    
//    public static void main(String[] args) {
//        
//        ApplicationContext context =  new ClassPathXmlApplicationContext("Spring-Module.xml");
//        
//        DatabaseInitializer dbInitializer = context.getBean("databaseInitializer", DatabaseInitializer.class);
//        dbInitializer.createDB();       
//        
//        TablesInitializer tableInitializer   = context.getBean("tablesInitializer", TablesInitializer.class);
//        
//        tableInitializer.createTables();
//        tableInitializer.insertGroup(new Group().setId(1).setName("gr1"));
//        
//    }
}
