package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.dao.PersonDao;
import ua.com.foxminded.dao.TeacherDao;
import ua.com.foxminded.model.dto.TeacherDto;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherDao teacherDao;
    
    @Override
    public void addTeacher(TeacherDto teacher) {
        teacherDao.addTeacher(teacher);
    }

    @Override
    public void editTeacher(TeacherDto teacher, UUID id) {
        teacherDao.editTeacher(teacher, id);
    }

    @Override
    public void deleteTeacher(UUID id) {
        teacherDao.deleteTeacher(id);
    }

    @Override
    public TeacherDto findTeacher(UUID id) {
        return teacherDao.findTeacher(id);
    }

    @Override
    public List<TeacherDto> findAllTeacher() {
        return teacherDao.findAllTeacher();
    }
}
