package ua.com.foxminded.converter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.model.enums.Degree;
import ua.com.foxminded.model.enums.Department;

@Component
public class TeacherConverter {

    public Teacher convertDtoToEntity(TeacherDto teacherDto) {  
        Teacher teacher = new Teacher();
        Optional.ofNullable(teacherDto.getIdTeacher()).ifPresent(ss -> teacher.setIdTeacher(ss.toString()));
        Optional.ofNullable(teacherDto.getIdPerson()).ifPresent(ss -> teacher.setIdTeacher(ss.toString()));
                  
        teacher.setFirstName(teacherDto.getFirstName())
                .setLastName(teacherDto.getLastName());            
        teacher.setDegree(teacherDto.getDegree().name())
        .setDepartment(teacherDto.getDepartment().name())
        .setPermanent(teacherDto.isPermanent())
        .setSalary(teacherDto.getSalary());
        return teacher;
    }

    public TeacherDto convertEntityToDto(Teacher teacher) {
        return ((TeacherDto) new TeacherDto().setIdTeacher(UUID.fromString(teacher.getIdTeacher()))
                                             .setIdPerson(UUID.fromString(teacher.getIdPerson()))
                                             .setFirstName(teacher.getFirstName())
                                             .setLastName(teacher.getLastName()))
                                                                                             .setDegree(Degree.valueOf(
                                                                                                     teacher.getDegree()))
                                                                                             .setDepartment(
                                                                                                     Department.valueOf(
                                                                                                             teacher.getDepartment()))
                                                                                             .setPermanent(
                                                                                                     teacher.isPermanent())
                                                                                             .setSalary(
                                                                                                     teacher.getSalary());
    }
}
