package ua.com.foxminded.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Student2Controller {
//  @Autowired
//  StudentService studentService;
  
  Logger logger = LoggerFactory.getLogger("SampleLogger");
  
  @GetMapping("/students2")
  public ModelAndView findAllStudent() {

      logger.info("--------------------- I am in ModelAndView                      viewStudents");
      
//      List<StudentDto> students = studentService.findAllStudent();
     
      String message = "Natalochka";
      
      ModelAndView studentMV = new ModelAndView("students2", "message", message);
//      studentMV.addObject("students", students);
   
      return studentMV;   
  }
    
}
