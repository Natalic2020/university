package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.Application;
import ua.com.foxminded.config.ApplicationConfigTest;
import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.interfaces.StudentDao;
import ua.com.foxminded.model.enums.StudyStatus;
import ua.com.foxminded.util.FileParser;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfigTest.class })
class StudentDaoImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FileParser file;
    
    @Autowired
    StudentDao studentDao;
    
    AbstractApplicationContext context;
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void init() {
//       context = new AnnotationConfigApplicationContext(ApplicationConfigTest.class);
//        StudentDaoImplTest bean = context.getBean(StudentDaoImplTest.class);
        createDB();
//        bean.run();  
    }

    @AfterEach
    void tearDown() throws Exception {
//        context.close();
    }

    @Test
    void test() {   
        studentDao.addStudent(new Student()
                .setId("20")
                .setStudyStatus(StudyStatus.FINISHED.toString())
                .setCitizenship("German")
                .setGrant(new BigDecimal(100))
                .setStartOfStudy(LocalDate.of(2015, 12, 31))
                .setPerson(new Person()
                        .setId("20")
                        .setFirstName("Nina")
                        .setLastName("Ivan")));
    }
    
    private void createDB() {
        jdbcTemplate.batchUpdate( file.readFileToLines("sql_test.script"));
    }

}
