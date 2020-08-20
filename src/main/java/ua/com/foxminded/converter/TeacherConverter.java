package ua.com.foxminded.converter;

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
        return new Teacher().setId(teacherDto.getIdTeacher().toString())
                .setPerson(new Person().setId(teacherDto.getIdPerson().toString())
                .setFirstName(teacherDto.getFirstName())
                .setLastName(teacherDto.getLastName()))            
        .setDegree(teacherDto.getDegree().toString())
        .setDepartment(teacherDto.getDepartment().toString())
        .setPermanent(teacherDto.isPermanent())
        .setSalary(teacherDto.getSalary());
    }

    public TeacherDto convertEntityToDto(Teacher teacher) {
        return ((TeacherDto) new TeacherDto().setIdTeacher(UUID.fromString(teacher.getId()))
                .setIdPerson(UUID.fromString(teacher.getPerson().getId()))
                .setFirstName(teacher.getPerson().getFirstName())
                .setLastName(teacher.getPerson().getLastName()))       
        .setDegree(Degree.valueOf(teacher.getDegree()))
        .setDepartment(Department.valueOf(teacher.getDepartment()))
        .setPermanent(teacher.isPermanent())
        .setSalary(teacher.getSalary());
    }
}
