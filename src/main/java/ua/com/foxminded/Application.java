package ua.com.foxminded;

import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.com.foxminded.model.dao.StudentDao;
import ua.com.foxminded.model.dto.Student;

public class Application {

    public static void main(String[] args) {
        
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

            StudentDao studentDao = (StudentDao) context.getBean("studentDao");
            Student student = new Student(UUID.randomUUID(), "mkyong", "kuku");
            System.out.println(student.toString());
//            studentDao.insert(student);
            
            List<Student> students = studentDao.receiveAllStudents();
            students.stream().forEach(student1 -> System.out.println(student1.toString()));
    }

}
