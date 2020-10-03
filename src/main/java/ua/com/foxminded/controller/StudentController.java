package ua.com.foxminded.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.service.interfaces.StudentService;

@Controller
public class StudentController {
    
    @Autowired
    StudentService studentService;
    
    @RequestMapping("/students")
    public ModelAndView findAllStudent() {
 
        System.out.println("--------------------- I am in ModelAndView                      viewStudents");
        
        List<StudentDto> students = studentService.findAllStudent();
       
        ModelAndView studentMV = new ModelAndView("students");
        studentMV.addObject("students", students);
     
        return studentMV;   
    }

}
