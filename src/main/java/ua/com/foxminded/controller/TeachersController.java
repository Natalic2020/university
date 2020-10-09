package ua.com.foxminded.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.service.interfaces.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeachersController {
    
    @Autowired
    TeacherService teacherService;
    
    @GetMapping()
    public ModelAndView findAllTeachers() {
 
        List<TeacherDto> teachers = teacherService.findAllTeacher();
       
        ModelAndView teacherMV = new ModelAndView("teachers");
        teacherMV.addObject("teachers", teachers);
     
        return teacherMV;   
    }
}
