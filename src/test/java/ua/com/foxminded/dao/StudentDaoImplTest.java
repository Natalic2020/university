package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.config.ApplicationConfigTest;
import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.interfaces.StudentDao;
import ua.com.foxminded.model.enums.StudyStatus;
import ua.com.foxminded.util.FileParser;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfigTest.class })
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
class StudentDaoImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FileParser file;

    @Autowired
    StudentDao studentDao;

    private String studentUUID = "961a9d3c-fb10-11ea-adc1-0242ac120002";
    private String personUUID = "69c4623a-fb11-11ea-adc1-0242ac120002";
    private Student student;
    
    @BeforeAll
    void Init() throws Exception {
        creatDB();
        student = new Student()
                .setIdStudent(studentUUID)
                .setStudyStatus(StudyStatus.FINISHED.toString())
                .setCitizenship("German")
                .setGrant(new BigDecimal(100))
                .setStartOfStudy(LocalDate.of(2015, 12, 31));
        student.setIdPerson(personUUID)
                                       .setFirstName("Nina")
                                       .setLastName("Ivan");
    }

    public void creatDB() {
        jdbcTemplate.batchUpdate( file.readFileToLines("sql_test.script")); 
    }

    @Test
    @Order(1)
    void addStudent_shouldReturnStudent_whenAddStudent() {

        List<Student> expected = new ArrayList<>();
        expected.add(student);
        
        studentDao.addStudent(student);

        List<Student> actual = studentDao.findStudent("Ivan");
        assertEquals(expected, actual);
    }
    
    @Test
    @Order(2)
    void findStudent_shouldReturnStudent_whenLookForLastName() {

        List<Student> expected = new ArrayList<>();
        expected.add(student);

        List<Student> actual = studentDao.findStudent("Ivan");
        assertEquals(expected, actual);
    }
    
    @Test
    @Order(2)
    void findStudent_shouldReturnEmpty_whenLookForNonExistentStudent() {

        List<Student> expected = new ArrayList<>();

        List<Student> actual = studentDao.findStudent("Ivan123");
        assertEquals(expected, actual);
    }
    
    @Test
    @Order(2)
    void findStudent_shouldReturnEmpty_whenLookForNull() {

        List<Student> expected = new ArrayList<>();

        List<Student> actual = studentDao.findStudent(null);
        assertEquals(expected, actual);
    }
    
    @Test
    @Order(3)
    void findAllStudent_shouldReturnAllStudent_whenLookForAllStudents() {

        List<Student> expected = new ArrayList<>();
        expected.add(student);

        List<Student> actual = studentDao.findAllStudent();
        assertEquals(expected, actual);
    }
    
    @Test
    @Order(4)
    @DependsOn({"addStudent_shouldReturnStudent_whenAddStudent"})
    void editStudent_shouldReturnStudent_whenEditStudent() {
        List<Student> expected = new ArrayList<>();
        expected.add(student);
        
       student.setCitizenship("Egypt");         
       studentDao.editStudent(student);
       
        List<Student> actual = studentDao.findStudent("Ivan");
        assertEquals(expected, actual);
    }
    
    @Test
    @Order(5)
    @DependsOn({"editStudent_shouldReturnStudent_whenEditStudent"})
    void deleteStudent_shouldReturnEmpty_whenDeleteStudent() {
        List<Student> expected = new ArrayList<>();
        studentDao.deleteStudent(studentUUID);
        
        List<Student> actual = studentDao.findStudent("Ivan");
        assertEquals(expected, actual);   
    }
    
    @Test
    void addStudent_shouldNotChangeListAllStudent_whenInputStudentWithoutParameter() {
        
        List<Student> expected = studentDao.findAllStudent();
        studentDao.addStudent(new Student());
        List<Student> actual = studentDao.findAllStudent();
        
        assertEquals(expected, actual);   
    }   
}
