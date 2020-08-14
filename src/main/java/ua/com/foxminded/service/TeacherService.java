package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.Teacher;

public interface TeacherService {

    public void addTeacher(Teacher teacher);
    public void editTeacher(Teacher teacher, UUID id);
    public void deleteTeacher(UUID id);
    public Teacher findTeacher(UUID id);
    public List<Teacher> findAllTeacher();
    
}
