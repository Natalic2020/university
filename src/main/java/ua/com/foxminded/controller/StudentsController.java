package ua.com.foxminded.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.enums.Month;
import ua.com.foxminded.service.interfaces.GroupService;
import ua.com.foxminded.service.interfaces.StudentService;

@RestController
@RequestMapping("/students")
public class StudentsController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Autowired
    StudentService studentService;

    @Autowired
    GroupService groupService;

    Logger logger = LoggerFactory.getLogger("SampleLogger");
    
    @GetMapping()
    public ResponseEntity<List<StudentDto>> findAllStudent() {

        List<StudentDto> students = studentService.findAllStudent();

        return students != null &&  !students.isEmpty()
                ? new ResponseEntity<>(students, HttpStatus.OK)
                        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping(produces = { "application/json" } )
        public ResponseEntity<?> createStudent( @RequestBody StudentDto student,
                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info("BINDING RESuLT ERROR");
            bindingResult.getFieldErrors().forEach(error ->
            {
                logger.info(error.getField() + " " + error.getDefaultMessage());
            });
           
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.addStudent(student);
        ModelAndView studentMV = new ModelAndView("redirect:/students");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{uuid}",
            produces = { "application/json" })
    public ResponseEntity<StudentDto> showStudent(@PathVariable("uuid") String uuid) {

        StudentDto student = studentService.findStudent(UUID.fromString(uuid));
        
        return student != null
                ? new ResponseEntity<>(student, HttpStatus.OK)
                        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{uuid}" ,
            produces = { "application/json" })   
    public ResponseEntity<?> editStudent(@PathVariable(name = "uuid") String uuid, @RequestBody StudentDto student ,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info("BINDING RESuLT ERROR");
            bindingResult.getFieldErrors().forEach(error ->
            {
                logger.info(error.getField() + " " + error.getDefaultMessage());
            });
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        studentService.editStudent(student, UUID.fromString(uuid));
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}", 
            produces = { "application/json" } )
    public ResponseEntity<?> deleteStudent(@PathVariable("uuid") String uuid) {

        studentService.deleteStudent(UUID.fromString(uuid));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
