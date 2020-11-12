package ua.com.foxminded.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        
        List<String> months = new ArrayList<>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        
        teacherMV.addObject("months", months);
        return teacherMV;   
    }
    @GetMapping("/new")
    public ModelAndView addNewTeacher(@ModelAttribute("teacher") TeacherDto teacher) {

        ModelAndView teacherMV = new ModelAndView("newTeacher");

        return teacherMV;
    }
    
    @GetMapping("/{uuid}")
    public ModelAndView showTeacher(@PathVariable("uuid") String uuid) {

        TeacherDto teacher = teacherService.findTeacher(UUID.fromString(uuid));
        ModelAndView teacherMV = new ModelAndView("editTeacher");
        teacherMV.addObject("teacher", teacher);

        return teacherMV;
    }
    
    @PostMapping()
    public ModelAndView createTeacher(@ModelAttribute("teacher") TeacherDto teacher) {

        teacherService.addTeacher(teacher);
        ModelAndView teacherMV = new ModelAndView("redirect:/teachers");
 
        return teacherMV;
    }
    
    @PostMapping("/edit")
    public ModelAndView editTeacher(@ModelAttribute("teacher") TeacherDto teacher) {

        teacherService.editTeacher(teacher);
        ModelAndView teacherMV = new ModelAndView("redirect:/teachers");
 
        return teacherMV;
    }
    
    @GetMapping("/delete/{uuid}")
    public ModelAndView deleteTeacher(@PathVariable("uuid") String uuid) {

        teacherService.deleteTeacher(UUID.fromString(uuid));
        ModelAndView teacherMV = new ModelAndView("redirect:/teachers");
 
        return teacherMV;
    }  
}

