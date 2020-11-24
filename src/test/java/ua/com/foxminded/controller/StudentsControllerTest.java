package ua.com.foxminded.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import ua.com.foxminded.config.ScheduleItemDaoImplTestConfiguration;
import ua.com.foxminded.dao.entity.Group;
import ua.com.foxminded.dao.entity.Room;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.entity.Subject;
import ua.com.foxminded.dao.entity.TimeSlot;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.service.interfaces.StudentService;


@ExtendWith(MockitoExtension.class)
class StudentsControllerTest {
    
    @InjectMocks
    private StudentsController studentsController;

   @Mock
   private StudentService studentService;

   static List<StudentDto> students;
   
   @BeforeAll
   public static void setUpBeforeClass() throws Exception {

       students = new ArrayList<>();
       students.add((StudentDto) new StudentDto().setIdStudent(UUID.fromString("a1f520ba-06fe-11eb-adc1-0242ac120002")).setFirstName("Nata").setLastName("Ivanov58"));
       students.add((StudentDto) new StudentDto().setIdStudent(UUID.fromString("71ec8ca1-a435-4bcf-bf48-1d239a5145a6")).setFirstName("Ira").setLastName("Petrov25"));
         
   }
   
    @Test
    void findAllStudent()  {
   
//        when(studentService.findAllStudent()).thenReturn(students);
//        
//        ModelAndView studentMV = studentsController.findAllStudent();
//        List<StudentDto> expected = students;
//        
//        List<StudentDto> actual = (List<StudentDto>) studentMV.getModelMap().get("students");
//        assertEquals(expected, actual);
    }
}
