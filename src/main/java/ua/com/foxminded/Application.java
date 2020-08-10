package ua.com.foxminded;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.com.foxminded.dao.StudentDao;
import ua.com.foxminded.model.dto.Student;

public class Application {

//    @Autowired
//    private static Student student;
    
    public static void main(String[] args) {
        
      
        
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");
            
        
            Student student = new Student(UUID.randomUUID(), "mkyong", "kuku");
            StudentDao studentDao= (StudentDao) context.getBean("studentDao");
            
            System.out.println(student.toString());
            studentDao.insert(student);
            
            List<Student> students = studentDao.receiveAllStudents();
            students.stream().forEach(student1 -> System.out.println(student1.toString()));
    }

}
