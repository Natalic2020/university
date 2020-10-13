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
        teacherDao.addTeacher(teacherConverter.convertDtoToEntity((TeacherDto) teacherDto
                .setIdTeacher(UUID.randomUUID())
                .setIdPerson(UUID.randomUUID())));
    }

    @Override
    public void editTeacher(TeacherDto teacherDto) {
        teacherDao.editTeacher(teacherConverter.convertDtoToEntity(teacherDto));
    }

    @Override
    public void deleteTeacher(UUID uuid) {
        teacherDao.deleteTeacher(uuid.toString());
    }

    @Override
    public TeacherDto findTeacher(UUID uuid) {
        return teacherConverter.convertEntityToDto(teacherDao.findTeacher(uuid.toString()));

    }

    @Override
    public List<TeacherDto> findAllTeacher() {
        return teacherDao.findAllTeacher().stream()
                .map(teacherConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
