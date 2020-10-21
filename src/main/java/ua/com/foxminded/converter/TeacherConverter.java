package ua.com.foxminded.converter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.model.enums.Degree;
import ua.com.foxminded.model.enums.Department;
import ua.com.foxminded.model.enums.StudyStatus;

@Component
public class TeacherConverter {

    public Teacher convertDtoToEntity(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
//        Optional.ofNullable(teacherDto.getIdTeacher())
//                .ifPresent(ss -> teacher.setIdTeacher(ss.toString()));
        Optional.ofNullable(teacherDto.getIdPerson())
                .ifPresent(ss -> teacher.setIdPerson(ss.toString()));

        teacher.setFirstName(teacherDto.getFirstName())
               .setLastName(teacherDto.getLastName());

        Optional.ofNullable(teacherDto.getDegree())
                .ifPresent(ss -> teacher.setDegree(ss.name()));

        Optional.ofNullable(teacherDto.getDepartment())
                .ifPresent(ss -> teacher.setDepartment(ss.name()));

        teacher.setPermanent(teacherDto.isPermanent())
               .setSalary(teacherDto.getSalary());
        return teacher;
    }

    public TeacherDto convertEntityToDto(Teacher teacher) {
        
        TeacherDto teacherDto =
        ((TeacherDto) new TeacherDto().setIdPerson(UUID.fromString(teacher.getIdPerson()))
                                             .setFirstName(teacher.getFirstName())
                                             .setLastName(teacher.getLastName()))
        .setPermanent(teacher.isPermanent())
        .setSalary(teacher.getSalary());   
        Optional.ofNullable(teacher.getDegree())
        .ifPresent(ss -> teacherDto.setDegree(Degree.valueOf(ss))); 
        Optional.ofNullable(teacher.getDepartment())
        .ifPresent(ss -> teacherDto.setDepartment(Department.valueOf(ss)));     
        return teacherDto;
    }
}
