package ua.com.foxminded.transformer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.enums.StudyStatus;

@Component
public class StudentTransformer {
    
    @Autowired
    private  Student student;
    
    @Autowired
    private  StudentDto studentDto;
  
    public Student convertDtoToEntity(StudentDto studentDto) {
        student.setId(studentDto.getId().toString())
        .setCitizenship(studentDto.getCitizenship())
        .setGrant(studentDto.getGrant())
        .setStartOfStudy(studentDto.getStartOfStudy())
        .setStudyStatus(studentDto.getStudyStatus().toString());
    return student;
    }


    public StudentDto convertEntityToDto(Student student) {
        studentDto.setId(UUID.fromString(student.getId()))
        .setCitizenship(student.getCitizenship())
        .setGrant(student.getGrant())
        .setStartOfStudy(student.getStartOfStudy())
        .setStudyStatus(StudyStatus.valueOf(student.getStudyStatus()));
        return studentDto;          
    }
}