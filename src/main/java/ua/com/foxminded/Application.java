package ua.com.foxminded;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import ua.com.foxminded.config.ApplicationConfig;
import ua.com.foxminded.converter.StudentConverter;
import ua.com.foxminded.dao.TablesInitializer;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.service.StudentService;
import ua.com.foxminded.service.TeacherService;

public class Application {

//    @Autowired
//    StudentTransformer studentTransformer;
    
    public static void main(String args[]) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

//      DatabaseInitializer dbInitializer = context.getBean("databaseInitializer", DatabaseInitializer.class);
//      dbInitializer.createDB();  

        TablesInitializer tableInitializer = context.getBean("tablesInitializer", TablesInitializer.class);
        tableInitializer.createTables();
//
        StudentService studentService = (StudentService) context.getBean("studentService");
        
        TestStudent(studentService);
        
        TeacherService teacherService = (TeacherService) context.getBean("teacherService");
        TestTeacher(teacherService);
        
        context.close();
    }

    private static void TestTeacher(TeacherService teacherService) {
        
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();

        TeacherDto teacher1 = new TeacherDto().setId(uuid1).setFirstName("Yashwant").setLastName("Chavan");
        TeacherDto teacher2 = new TeacherDto().setId(uuid2).setFirstName("Mahesh").setLastName("Patil");
        TeacherDto teacher3 = new TeacherDto().setId(uuid3).setFirstName("Vishal").setLastName("Naik");

        teacherService.addTeacher(teacher1);
        teacherService.addTeacher(teacher2);
        teacherService.addTeacher(teacher3);

        System.out.println("Find All");
        teacherService.findAllTeacher().forEach(System.out::println);

        System.out.println("Delete theacher Id = 3");
        teacherService.deleteTeacher(uuid3);

//        System.out.println("Update person Id = 1");
//        teacherService.editTeacher(new TeacherDto(teacher2), uuid1);

//        System.out.println("Find person Id = 2");
//        TeacherDto theacher = teacherService.findTeacher(uuid2);
//        System.out.println(theacher);

        System.out.println("Find All Again");
        teacherService.findAllTeacher().forEach(System.out::println);
    }
    
    
    private static void TestStudent(StudentService studentService) {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();

        StudentDto student1 = new StudentDto().setId(uuid1).setFirstName("Yashwant").setLastName("Chavan");
        StudentDto student2 = new StudentDto().setId(uuid2).setFirstName("Mahesh").setLastName("Patil");
        StudentDto student3 = new StudentDto().setId(uuid3).setFirstName("Vishal").setLastName("Naik");

        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);

        System.out.println("Find All");
        studentService.findAllStudent().forEach(System.out::println);

        System.out.println("Delete person Id = 3");
        studentService.deleteStudent(uuid3);

        System.out.println("Update person Id = 1");
        studentService.editStudent(new StudentDto(student2).setCitizenship("Russia"), uuid1);

        System.out.println("Find person Id = 2");
        StudentDto student = studentService.findStudent(uuid2);
        System.out.println(student);

        System.out.println("Find All Again");
        studentService.findAllStudent().forEach(System.out::println);
    } 
}
