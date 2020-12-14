package ua.com.foxminded.dao;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//import ua.com.foxminded.config.ApplicationConfigTest;
import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.interfaces.StudentDao;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.enums.StudyStatus;
import ua.com.foxminded.service.interfaces.StudentService;
import ua.com.foxminded.util.FileParser;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class StudentDaoImplTest {

//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    FileParser file;
//
//    @Autowired
//    @Qualifier("studentDao")
//    StudentDao studentDao;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private StudentDao studentDao;
    
    private String personUUID = "69c4623a-fb11-11ea-adc1-0242ac120002";
    private Student student;

    @BeforeEach
    void Init() throws Exception {
        creatDB();
        student = new Student()
                               .setStudyStatus(StudyStatus.FINISHED.toString())
                               .setCitizenship("German")
                               .setGrant(new BigDecimal(100))
                               .setStartOfStudy(LocalDate.of(2015, 12, 31));
        student.setIdPerson(personUUID)
               .setFirstName("Nina")
               .setLastName("Ivan");
    }

    public void creatDB() {
//        jdbcTemplate.batchUpdate(file.readFileToLines("sql_test.script"));
    }
   
    @Test
    public void studentTest() {
     // given
        Student nina = student;
        entityManager.persist(nina);
        entityManager.flush();

        // when
        Optional<Student> found = studentDao.findById(nina.getIdPerson());

        // then
        String nameNina = studentDao.findById(nina.getIdPerson()).orElse(new Student()).getFirstName();
        
        assertThat(nameNina).isEqualTo(nina.getFirstName());
  }
    
    
}
