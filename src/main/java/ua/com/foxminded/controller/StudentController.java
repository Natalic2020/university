package ua.com.foxminded.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.service.interfaces.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    StudentService studentService;
    
    Logger logger = LoggerFactory.getLogger("SampleLogger");
    
    @GetMapping()
    public ModelAndView findAllStudent() {
 
        logger.info("--------------------- I am in ModelAndView                      viewStudents");
        
       List<StudentDto> students = studentService.findAllStudent();
       
        
        ModelAndView studentMV = new ModelAndView("students");
       studentMV.addObject("students", students);
     
        return studentMV;   
    }
    
    @GetMapping("/2")
    public ModelAndView index() {
 
      logger.info("--------------------- I am in ModelAndView                      viewStudents");
     
      String message = "Natalochka   dfgdfg";
      
      ModelAndView studentMV = new ModelAndView("students2", "message", message);
     
        return studentMV;   
    }
    
    @GetMapping("/1")
    public ModelAndView findListStudent() {
 
        logger.info("--------------------- I am in ModelAndView                      viewStudents");
        
       List<StudentDto> students = studentService.findListStudent();      
        
        ModelAndView studentMV = new ModelAndView("students1");
       studentMV.addObject("students", students);
     
        return studentMV;   
    }

}
