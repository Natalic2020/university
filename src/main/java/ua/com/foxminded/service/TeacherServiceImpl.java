package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.converter.TeacherConverter;
import ua.com.foxminded.dao.TeacherDao;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.model.dto.TeacherDto;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherDao teacherDao;
    
    @Autowired
    TeacherConverter teacherConverter;
    
    @Override
    public void addTeacher(TeacherDto teacherDto) {
        teacherDao.addTeacher(teacherConverter.convertDtoToEntity(teacherDto));
    }

    @Override
    public void editTeacher(TeacherDto teacherDto, UUID id) {
        teacherDao.editTeacher(teacherConverter.convertDtoToEntity(teacherDto), id.toString());
    }

    @Override
    public void deleteTeacher(UUID id) {
        teacherDao.deleteTeacher(id.toString());
    }

    @Override
    public TeacherDto findTeacher(UUID id) {
        return teacherConverter.convertEntityToDto(teacherDao.findTeacher(id.toString()));
    }

    @Override
    public List<TeacherDto> findAllTeacher() {
        return teacherDao.findAllTeacher().stream().map(teacher -> teacherConverter.convertEntityToDto(teacher)).collect(Collectors.toList());
    }
}
