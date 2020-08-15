package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.TeacherDto;

public interface TeacherDao {

    public void addTeacher(TeacherDto teacher);
    public void editTeacher(TeacherDto teacher, UUID id);
    public void deleteTeacher(UUID id);
    public TeacherDto findTeacher(UUID id);
    public List<TeacherDto> findAllTeacher();
    
}
