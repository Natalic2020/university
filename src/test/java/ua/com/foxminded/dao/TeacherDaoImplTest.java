package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.config.ApplicationConfigTest;
import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.interfaces.TeacherDao;
import ua.com.foxminded.util.FileParser;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfigTest.class })
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class TeacherDaoImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FileParser file;

    @Autowired
    TeacherDao teacherDao;

    private String teacherUUID = "2901a714-fb14-11ea-adc1-0242ac120002";
    private String personUUID = "2d44565a-fb14-11ea-adc1-0242ac120002";
    private Teacher teacher;
    
    @BeforeAll
    void Init() throws Exception {
        creatDB();
        teacher = new Teacher()
                .setIdTeacher(teacherUUID)
                .setDegree("DOCENT")
                .setDepartment("MATHEMATICS")
                .setPermanent(true)
                .setSalary(new BigDecimal(9999));
                teacher.setIdPerson(personUUID)
                                       .setFirstName("Maria")
                                       .setLastName("Ivanovna");
    }

    public void creatDB() {
        jdbcTemplate.batchUpdate( file.readFileToLines("sql_test.script")); 
    }

    @Test
    @Order(1)
    void addTeacher_shouldReturnTeacher_whenAddTeacher() {
        
        List<Teacher> expected = new ArrayList<>();
        expected.add(teacher);

        teacherDao.addTeacher(teacher);
        
        List<Teacher> actual = teacherDao.findTeacher(teacherUUID);
        assertEquals(expected, actual);
    }
    
    @Test
    @Order(2)
    void findTeacher_shouldReturnTeacher_whenLookForLastName() {

        List<Teacher> expected = new ArrayList<>();
        expected.add(teacher);

        List<Teacher> actual = teacherDao.findTeacher(teacherUUID);
        assertEquals(expected, actual);
    }
    
    @Test
    @Order(2)
    void findTeacher_shouldReturnEmpty_whenLookForNonExistentTeacher() {
        List<Teacher> expected = new ArrayList<>();

        List<Teacher> actual = teacherDao.findTeacher("Ivan123");
        assertEquals(expected, actual);
    }
    
    @Test
    @Order(2)
    void findTeacher_shouldReturnEmpty_whenLookForNull() {
        List<Teacher> expected = new ArrayList<>();

        List<Teacher> actual = teacherDao.findTeacher(null);
        assertEquals(expected, actual);
    }
    
    @Test
    @Order(3)
    void findAllTeacher_shouldReturnAllTeacher_whenLookForAllTeachers() {
        List<Teacher> expected = new ArrayList<>();
        expected.add(teacher);

        List<Teacher> actual = teacherDao.findAllTeacher();
        assertEquals(expected, actual);
    }
    
    
    @Test
    @Order(4)
    @DependsOn({"addTeacher_shouldReturnTeacher_whenAddTeacher"})
    void editTeacher_shouldReturnTeacher_whenEditTeacher() {
        List<Teacher> expected = new ArrayList<>();
        expected.add(teacher);
        
        teacher.setDegree("DOCTOR");
        teacherDao.editTeacher(teacher);

        List<Teacher> actual = teacherDao.findTeacher(teacherUUID);
        assertEquals(expected, actual);
    }
    
    @Test
    @Order(5)
    @DependsOn({"editTeacher_shouldReturnTeacher_whenEditTeacher"})
    void deleteTeacher_shouldReturnEmpty_whenDeleteTeacher() {
        List<Teacher> expected = new ArrayList<>();
        teacherDao.deleteTeacher(teacherUUID);
        
        List<Teacher> actual = teacherDao.findTeacher("Ivanovna");
        assertEquals(expected, actual);   
    } 
    
    @Test
    void addTeacher_shouldNotChangeListAllTeacher_whenInputTeacherWithoutParameter() {
        
        List<Teacher> expected = teacherDao.findAllTeacher();
        teacherDao.addTeacher(new Teacher());
        
        List<Teacher> actual = teacherDao.findAllTeacher();
        
        assertEquals(expected, actual);   
    }   
}
