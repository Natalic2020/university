package ua.com.foxminded.transformer;

import java.util.UUID;

import org.springframework.stereotype.Component;

import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.enums.StudyStatus;

@Component
public class StudentTransformer {
   
    public Student convertDtoToEntity(StudentDto studentDto) {    
        return new Student().setIdPerson(studentDto.getId().toString())
        .setCitizenship(studentDto.getCitizenship())
        .setGrant(studentDto.getGrant())
        .setStartOfStudy(studentDto.getStartOfStudy())
        .setStudyStatus(studentDto.getStudyStatus().toString());
    }

    public StudentDto convertEntityToDto(Student student) {
        return new StudentDto().setId(UUID.fromString(student.getId()))
        .setCitizenship(student.getCitizenship())
        .setGrant(student.getGrant())
        .setStartOfStudy(student.getStartOfStudy())
        .setStudyStatus(StudyStatus.valueOf(student.getStudyStatus()));        
    }
}