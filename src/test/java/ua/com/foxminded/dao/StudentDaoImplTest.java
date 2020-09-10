package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
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
class StudentDaoImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FileParser file;

    @Autowired
    StudentDao studentDao;

    AbstractApplicationContext context;

    @BeforeEach
    void init() {
        jdbcTemplate.batchUpdate(file.readFileToLines("sql_test.script"));
    }

    @Test
    public void addStudent_shouldThrowNullPointerException_whenInputStudentOnlyWithID() {
        assertThrows(NullPointerException.class, () -> studentDao.addStudent(new Student()));
    }

    @Test
    void addStudent_schoudReturnStudent_whenInputStudent() {

        Student student = new Student()
                                       .setId("20")
                                       .setStudyStatus(StudyStatus.FINISHED.toString())
                                       .setCitizenship("German")
                                       .setGrant(new BigDecimal(100))
                                       .setStartOfStudy(LocalDate.of(2015, 12, 31))
                                       .setPerson(new Person()
                                                              .setId("20")
                                                              .setFirstName("Nina")
                                                              .setLastName("Ivan"));

        studentDao.addStudent(student);

        List<Student> expected = new ArrayList<>();
        expected.add(student);

        List<Student> actual = studentDao.findStudent("Ivan");
        assertEquals(expected, actual);
    }
}
