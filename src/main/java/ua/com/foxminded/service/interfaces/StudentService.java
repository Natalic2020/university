package ua.com.foxminded.service.interfaces;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.StudentDto;

public interface StudentService {

    public void addStudent(StudentDto student);
    public void editStudent(StudentDto student);
    public void deleteStudent(UUID uuid);
    public StudentDto findStudent(UUID uuid);
    public List<StudentDto> findAllStudent();
    public List<StudentDto> findListStudent();
      
}
