package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.TeacherDto;

public interface TeacherService {

    public void addTeacher(TeacherDto teacher);
    public void editTeacher(TeacherDto teacher, UUID id);
    public void deleteTeacher(UUID id);
    public TeacherDto findTeacher(UUID id);
    public List<TeacherDto> findAllTeacher();
    
}
