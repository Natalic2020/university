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

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ua.com.foxminded.dao.entity.Group;
import ua.com.foxminded.dao.entity.Room;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.entity.Subject;
import ua.com.foxminded.dao.entity.TimeSlot;
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

   static List<StudentDto> students;
   
   MockMvc mockMvc;
   
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

       mockMvc = MockMvcBuilders.standaloneSetup(studentsRestController).build();
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
