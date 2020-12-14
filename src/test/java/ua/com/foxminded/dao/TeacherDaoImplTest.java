package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//import ua.com.foxminded.config.ApplicationConfigTest;
import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.interfaces.TeacherDao;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.service.interfaces.TeacherService;
import ua.com.foxminded.util.FileParser;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { ApplicationConfigTest.class })
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class TeacherDaoImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FileParser file;

    @Autowired
    @Qualifier("teacherDao")
    TeacherDao teacherDao;

    private String personUUID = "2d44565a-fb14-11ea-adc1-0242ac120002";
    private Teacher teacher;

    @BeforeAll
    void Init() throws Exception {
        creatDB();
        teacher = new Teacher()
                               .setDegree("DOCENT")
                               .setDepartment("MATHEMATICS")
                               .setPermanent(true)
                               .setSalary(new BigDecimal(9999));
        teacher.setIdPerson(personUUID)
               .setFirstName("Maria")
               .setLastName("Ivanovna");
    }

    public void creatDB() {
        jdbcTemplate.batchUpdate(file.readFileToLines("sql_test.script"));
    }
}
