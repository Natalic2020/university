package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.StudentDto;

public interface StudentDao {

    public void addStudent(StudentDto student);
    public void editStudent(StudentDto student, UUID id);
    public void deleteStudent(UUID id);
    public StudentDto findStudent(UUID id);
    public List<StudentDto> findAllStudent();
    
}
