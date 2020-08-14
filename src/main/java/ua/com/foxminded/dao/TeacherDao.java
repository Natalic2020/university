package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.Teacher;

public interface TeacherDao {

    public void addTeacher(Teacher teacher);
    public void editTeacher(Teacher teacher, UUID id);
    public void deleteTeacher(UUID id);
    public Teacher findTeacher(UUID id);
    public List<Teacher> findAllTeacher();
    
}
