package ua.com.foxminded.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ua.com.foxminded.converter.TeacherConverter;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.interfaces.TeacherDao;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.service.interfaces.TeacherService;

@Service("teacherService")
@Component
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    @Qualifier("teacherDao")
    TeacherDao teacherDao;
    
    @Autowired
    TeacherConverter teacherConverter;
    
    @Override
    public Boolean addTeacher(TeacherDto teacherDto) {
        teacherDto.getContactInfo().setId(UUID.randomUUID());
        teacherDao.save(teacherConverter.convertDtoToEntity((TeacherDto) teacherDto
                .setIdTeacher(UUID.randomUUID())
                .setIdPerson(UUID.randomUUID())));
        return !findTeacher(teacherDto.getIdPerson()).equals(new TeacherDto());
    }

    @Override
    public void editTeacher(TeacherDto teacherDto, UUID uuid) {
        teacherDao.save(teacherConverter.convertDtoToEntity((TeacherDto) teacherDto.setIdPerson(uuid)));
     }

    @Override
    public void deleteTeacher(UUID uuid) {
        teacherDao.deleteById(uuid.toString());
    }

    @Override
    public TeacherDto findTeacher(UUID uuid) {
        return teacherConverter.convertEntityToDto(teacherDao.findById(uuid.toString()).orElse(new Teacher()));

    }

    @Override
    public List<TeacherDto> findAllTeacher() {
        List<TeacherDto> teachers = new ArrayList<TeacherDto>();
        teacherDao.findAll().forEach(teacher -> {
            teachers.add(teacherConverter.convertEntityToDto(teacher));
        });
        return teachers;   
    }
}

