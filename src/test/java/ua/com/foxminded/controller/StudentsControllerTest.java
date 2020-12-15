package ua.com.foxminded.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ua.com.foxminded.model.dto.ContactInfoDto;
import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.enums.Specialty;
import ua.com.foxminded.model.enums.StudyStatus;
import ua.com.foxminded.service.interfaces.StudentService;

@ExtendWith(MockitoExtension.class)
class StudentsControllerTest {

    @InjectMocks
    private StudentsRestController studentsRestController;

    @Mock
    private StudentService studentService;

    List<StudentDto> students = new ArrayList<StudentDto>();

    MockMvc mockMvc;

    StudentDto validStudent;

    @BeforeEach
    void setUpBeforeClass() {

        ContactInfoDto contactInfo = new ContactInfoDto()
                .setId(UUID.randomUUID())
                .setIndex(80339)
                .setCountry("China")
                .setCity("Hong Kong")
                .setStreet("Mau the dum")
                .setHouse("1/1")
                .setApartment(1)
                .setEmail("kuku@ru.muku")
                .setPhone1("123456789")
                .setPhone2("98741236547");
        
        validStudent = ((StudentDto) new StudentDto()
                                                     .setIdPerson(UUID.randomUUID())
                                                     .setFirstName("Maria")
                                                     .setLastName("Kokoshka")
                                                     .setContactInfo(contactInfo));
        
        validStudent.setCitizenship("ChinaMensch")
                    .setStudyStatus(StudyStatus.FINISHED)
                    .setStartOfStudy(LocalDate.now())                                                                                             
                    .setGroup(new GroupDto().setId( UUID.randomUUID())
                            .setName("gggr")
                            .setSpecialty(Specialty.ECONOMY));
                                                                                                                  
        students.add(validStudent);

        mockMvc = MockMvcBuilders.standaloneSetup(studentsRestController).build();
    }

    @Test
    void findAllStudent() throws Exception {
        given(studentService.findAllStudent()).willReturn(students);

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createStudent() throws Exception {
        given(studentService.addStudent(any(StudentDto.class))).willReturn(true);

        mockMvc.perform(post("/student").content(asJsonString(validStudent))
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());
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
    
    @Test
    void editStudent() throws Exception {
        given(studentService.editStudent(any(StudentDto.class), any())).willReturn(true);
        given(studentService.findStudent(any())).willReturn(validStudent);
        
        mockMvc.perform(put("/student/" + validStudent.getIdPerson())
                .content(asJsonString(validStudent))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }   
    
    @Test
    void deleteStudent() throws Exception {
        given(studentService.findStudent(any())).willReturn(validStudent);
        given(studentService.deleteStudent(any())).willReturn(true);
        
        mockMvc.perform(delete("/student/" + validStudent.getIdPerson()))
                .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
