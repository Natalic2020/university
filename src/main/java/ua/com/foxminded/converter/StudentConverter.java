package ua.com.foxminded.converter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.enums.StudyStatus;

@Component
public class StudentConverter {
   
    public Student convertDtoToEntity(StudentDto studentDto) {    
        
        Student student = new Student();
        Optional.ofNullable(studentDto.getIdStudent()).ifPresent(ss -> student.setIdStudent(ss.toString()));
        Optional.ofNullable(studentDto.getIdPerson()).ifPresent(ss -> student.setIdPerson(ss.toString()));        
         student.setFirstName(studentDto.getFirstName())
                        .setLastName(studentDto.getLastName());
        student.setCitizenship(studentDto.getCitizenship())
        .setGrant(studentDto.getGrant())
        .setStartOfStudy(studentDto.getStartOfStudy())
        .setStudyStatus(studentDto.getStudyStatus().name());
      return   student;
        
    }

    public StudentDto convertEntityToDto(Student student) {
        StudentDto  studentDto = 
        ((StudentDto) new StudentDto()
                .setIdStudent(UUID.fromString(student.getIdStudent()))
                .setIdPerson(UUID.fromString(student.getIdPerson()))
                .setFirstName(student.getFirstName())
                .setLastName(student.getLastName()))  
        .setCitizenship(student.getCitizenship())
        .setGrant(student.getGrant())
        .setStartOfStudy(student.getStartOfStudy());
        Optional.ofNullable(student.getStudyStatus()).ifPresent(ss -> studentDto.setStudyStatus(StudyStatus.valueOf(ss)));      
        return studentDto;
    }
}