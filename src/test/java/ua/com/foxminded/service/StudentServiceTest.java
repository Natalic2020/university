package ua.com.foxminded.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import ua.com.foxminded.TestConfig;
import ua.com.foxminded.converter.StudentConverter;
import ua.com.foxminded.dao.entity.ContactInfo;
import ua.com.foxminded.dao.entity.Group;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.interfaces.StudentDao;
import ua.com.foxminded.model.dto.ContactInfoDto;
import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.enums.Specialty;
import ua.com.foxminded.model.enums.StudyStatus;
import ua.com.foxminded.service.interfaces.GroupService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = {TestConfig.class})
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentDao studentDao;
    
    @InjectMocks
    StudentServiceImpl studentServise;

    @Spy
    StudentConverter studentConverter = new StudentConverter();

    @Spy
    GroupService groupService; // = new GroupServiceImpl();

    String uuid = "1bfc7ee3-28de-4e7d-b068-8dccd095d655";

    Student validStudent;

    @BeforeEach
    void setUp()  {

        validStudent =  ((Student) new Student().setIdPerson(uuid)
                .setFirstName("Maria")
                .setLastName("Kokoshka")
                .setContactInfo(new ContactInfo().setId(UUID.randomUUID().toString())
                        .setIndex(80339)
                        .setCountry("China")
                        .setCity("Hong Kong")
                        .setStreet("Mau the dum")
                        .setHouse("1/1")
                        .setApartment(1)
                        .setEmail("kuku@ru.muku")
                        .setPhone1("123456789")
                        .setPhone2("98741236547")))
                .setCitizenship("ChinaMensch")
                .setGroup(new Group().setId(UUID.randomUUID().toString()).setName("gggr").setSpecialty("ECONOMY"))
                .setStudyStatus("FINISHED")
                .setStartOfStudy(LocalDate.now());
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

//        when(studentDao.findById(any())).thenReturn( Optional.ofNullable(validStudent));



    }
    
    @Test
    void findStudentTest() {
        Optional<Student> student = Optional.ofNullable(validStudent);
        Mockito.when(studentDao.findById(any()))
        .thenReturn(student);

        StudentDto studentFound = studentServise.findStudent(UUID.fromString(validStudent.getIdPerson()));
        
        assertThat(studentFound).isNotNull();
        verify(studentDao).findById(uuid);
    }

    @Test
    void addStudentTest() {
        Optional<Student> student = Optional.ofNullable(validStudent);
        when(studentDao.save(org.mockito.ArgumentMatchers.any(Student.class))).thenReturn(new Student());
        when(studentDao.findById(any())).thenReturn( Optional.ofNullable(validStudent));

        boolean isAddStudent = studentServise.addStudent(new StudentDto());

        assertTrue(isAddStudent);

    }

    @Test
    void editStudentTest() {
        Optional<Student> student = Optional.ofNullable(validStudent);
        when(studentDao.save(org.mockito.ArgumentMatchers.any(Student.class))).thenReturn(new Student());

        boolean isEditStudent = studentServise.editStudent(new StudentDto(), UUID.fromString(uuid));
        assertTrue(isEditStudent);
    }

    @Test
    void findAllTest() {
        StudentDto student = new StudentDto();
        List<Student> students = new ArrayList<>();
        students.add(validStudent);
        given(studentDao.findAll()).willReturn(students);

        List<StudentDto> studentsFound = studentServise.findAllStudent();

       assertThat(studentsFound).hasSize(1);
    }
    
   

    @Test
    void deleteStudent() {
        StudentDto student = new StudentDto();
        List<Student> students = new ArrayList<>();
        students.add(validStudent);
//        given(studentDao.deleteById(Mockito.any())).willReturn(students);
        studentDao.deleteById(uuid.toString());

        studentServise.deleteStudent(UUID.randomUUID());
    }
    
}
