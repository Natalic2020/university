package ua.com.foxminded.service.interfaces;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.TeacherDto;

public interface TeacherService {
    public void addTeacher(TeacherDto teacher);
    public void editTeacher(TeacherDto teacher);
    public void deleteTeacher(UUID uuid);
    public TeacherDto findTeacher(UUID uuid);
    public List<TeacherDto> findAllTeacher(); 
}
