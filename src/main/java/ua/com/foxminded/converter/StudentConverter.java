package ua.com.foxminded.converter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.enums.StudyStatus;

@Component
public class StudentConverter {
   
    public Student convertDtoToEntity(StudentDto studentDto) {    
        return new Student().setId(studentDto.getIdStudent().toString())
                .setPerson(new Person().setId(studentDto.getIdPerson().toString())
                        .setFirstName(studentDto.getFirstName())
                        .setLastName(studentDto.getLastName()))
        .setCitizenship(studentDto.getCitizenship())
        .setGrant(studentDto.getGrant())
        .setStartOfStudy(studentDto.getStartOfStudy())
        .setStudyStatus(studentDto.getStudyStatus().name());
    }

    public StudentDto convertEntityToDto(Student student) {
        StudentDto  studentDto = 
        ((StudentDto) new StudentDto().setIdStudent(UUID.fromString(student.getId()))
                .setIdPerson(UUID.fromString(student.getPerson().getId()))
               .setFirstName(student.getPerson().getFirstName())
                .setLastName(student.getPerson().getLastName()))  
        .setCitizenship(student.getCitizenship())
        .setGrant(student.getGrant())
        .setStartOfStudy(student.getStartOfStudy());
        Optional.ofNullable(student.getStudyStatus()).ifPresent(ss -> studentDto.setStudyStatus(StudyStatus.valueOf(ss)));      
        return studentDto;
    }
}