package ua.com.foxminded.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.service.interfaces.StudentService;

@Controller
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    StudentService studentService;

    @GetMapping()
    public ModelAndView findAllStudent() {

        System.out.println("Nata");
        List<StudentDto> students = studentService.findAllStudent();

        ModelAndView studentMV = new ModelAndView("students");
        studentMV.addObject("students", students);

        return studentMV;
    }

    @GetMapping("/new")
    public ModelAndView addNewStudent(@ModelAttribute("student") StudentDto student) {

        ModelAndView studentMV = new ModelAndView("newStudent");

        return studentMV;
    }
    
    @PostMapping()
    public ModelAndView createStudent(@ModelAttribute("student") StudentDto student) {

        studentService.addStudent(student);
        ModelAndView studentMV = new ModelAndView("redirect:/students");
 
        return studentMV;
    }
    
    @GetMapping("/{uuid}")
    public ModelAndView showStudent(@PathVariable("uuid") String uuid) {

        StudentDto student = studentService.findStudent(UUID.fromString(uuid));
        ModelAndView studentMV = new ModelAndView("editStudent");
        studentMV.addObject("student", student);

        return studentMV;
    }
    
    @PostMapping("/edit")
    public ModelAndView editStudent(@ModelAttribute("student") StudentDto student) {

        studentService.editStudent(student);
        ModelAndView studentMV = new ModelAndView("redirect:/students");
 
        return studentMV;
    }
    
    @GetMapping("/delete/{uuid}")
    public ModelAndView deleteStudent(@PathVariable("uuid") String uuid) {

        studentService.deleteStudent(UUID.fromString(uuid));
        ModelAndView studentMV = new ModelAndView("redirect:/students");
 
        return studentMV;
    }    
}
