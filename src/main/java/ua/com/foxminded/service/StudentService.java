package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.StudentDto;

public interface StudentService {

    public void addStudent(StudentDto student);
    public void editStudent(StudentDto student, UUID id);
    public void deleteStudent(UUID id);
    public List<StudentDto> findStudent(UUID id);
    public List<StudentDto> findAllStudent();  
}
