package ua.com.foxminded.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("teacherDao")
    TeacherDao teacherDao;
    
    @Autowired
    TeacherConverter teacherConverter;
    
    @Override
    public void addTeacher(TeacherDto teacherDto) {
        teacherDto.getContactInfo().setId(UUID.randomUUID());
        teacherDao.save(teacherConverter.convertDtoToEntity((TeacherDto) teacherDto
                .setIdTeacher(UUID.randomUUID())
                .setIdPerson(UUID.randomUUID())));
    }

    @Override
    public void editTeacher(TeacherDto teacherDto) {
        UUID idContactInfo = Optional.ofNullable(teacherDto.getContactInfo().getId())
                .map(s -> s).orElse(UUID.randomUUID());
        teacherDto.getContactInfo().setId(idContactInfo);
        teacherDao.save(teacherConverter.convertDtoToEntity((TeacherDto) teacherDto ));
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

