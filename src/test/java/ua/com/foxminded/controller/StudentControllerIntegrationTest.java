package ua.com.foxminded.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.com.foxminded.TestConfig;
import ua.com.foxminded.model.dto.ContactInfoDto;
import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.enums.Specialty;
import ua.com.foxminded.model.enums.StudyStatus;
import ua.com.foxminded.service.interfaces.StudentService;

@ContextConfiguration(classes = {TestConfig.class})
@WebMvcTest(StudentsRestController.class)
public class StudentControllerIntegrationTest {
    
    @MockBean
    private StudentService studentService;
    
    @Autowired
    private MockMvc mockMvc;

    private WebTestClient webClient;
    
    StudentDto validStudent;
    
    @BeforeEach
    void setUpBeforeClass()  {

        validStudent =  ((StudentDto) new StudentDto().setIdPerson(UUID.randomUUID())
                .setFirstName("Maria")
                .setLastName("Kokoshka")
                .setContactInfo(new ContactInfoDto().setId(UUID.randomUUID())
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
                .setGroup(new GroupDto().setId(UUID.randomUUID()).setName("gggr").setSpecialty(Specialty.ECONOMY))
                .setStudyStatus(StudyStatus.FINISHED)
                .setStartOfStudy(LocalDate.now());
        
         when(studentService.findStudent(any())).thenReturn(validStudent);   
//         webClient = MockMvcWebClientBuilder.mockMvcSetup(mockMvc).useMockMvcForHosts("").build();
    }
    
    @AfterEach
    void tearDown() {
        reset(studentService);
    }

    @Test
    void showStudent2() throws Exception {
//       given(studentService.findStudent(any())).willReturn(validStudent); 
       
       mockMvc.perform(get("/student/" + validStudent.getIdPerson().toString()))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.idPerson", is(validStudent.getIdPerson().toString())))
               .andExpect(jsonPath("$.firstName", is("Maria")));
    }
    
    
    @Test
    void showStudent() throws Exception {
       given(studentService.findStudent(any())).willReturn(validStudent); 
       
       mockMvc.perform(get("/student/" + validStudent.getIdPerson()))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.idPerson", is(validStudent.getIdPerson().toString())))
               .andExpect(jsonPath("$.firstName", is("Maria")));
    }
}



