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
   }
}
