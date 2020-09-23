package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ua.com.foxminded.converter.TeacherConverter;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.interfaces.TeacherDao;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.service.interfaces.TeacherService;

@Service("teacherService")
@Component
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherDao teacherDao;
    
    @Autowired
    TeacherConverter teacherConverter;
    
    @Override
    public void addTeacher(TeacherDto teacherDto) {
        teacherDao.addTeacher(teacherConverter.convertDtoToEntity(teacherDto.setIdTeacher(UUID.randomUUID())));
    }

    @Override
    public void editTeacher(TeacherDto teacherDto) {
        teacherDao.editTeacher(teacherConverter.convertDtoToEntity(teacherDto));
    }

    @Override
    public void deleteTeacher(UUID id) {
        teacherDao.deleteTeacher(id.toString());
    }

    @Override
    public List<TeacherDto> findTeacher(UUID id) {
        return teacherDao.findTeacher(id.toString()).stream().map(teacher -> teacherConverter.convertEntityToDto(teacher)).collect(Collectors.toList());
    }

    @Override
    public List<TeacherDto> findAllTeacher() {
        return teacherDao.findAllTeacher().stream().map(teacher -> teacherConverter.convertEntityToDto(teacher)).collect(Collectors.toList());
    }
}
