package ua.com.foxminded.transformer;

import java.util.UUID;

import org.springframework.stereotype.Component;

import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.model.enums.Degree;
import ua.com.foxminded.model.enums.Department;

@Component
public class TheacherTransformer {

    public Teacher convertDtoToEntity(TeacherDto teacherDto) {    
        return new Teacher().setIdPerson(teacherDto.getId().toString())
        .setDegree(teacherDto.getDegree().toString())
        .setDepartment(teacherDto.getDepartment().toString())
        .setPermanent(teacherDto.isPermanent())
        .setSalary(teacherDto.getSalary());
    }

    public TeacherDto convertEntityToDto(Teacher teacher) {
        return new TeacherDto().setId(UUID.fromString(teacher.getIdPerson()))
        .setDegree(Degree.valueOf(teacher.getDegree()))
        .setDepartment(Department.valueOf(teacher.getDepartment()))
        .setPermanent(teacher.isPermanent())
        .setSalary(teacher.getSalary());
    }
}
