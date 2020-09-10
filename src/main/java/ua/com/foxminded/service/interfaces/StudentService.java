package ua.com.foxminded.service.interfaces;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.StudentDto;

public interface StudentService {

    public void addStudent(StudentDto student);
    public void editStudent(StudentDto student);
    public void deleteStudent(UUID id);
    public List<StudentDto> findStudent(String text);
    public List<StudentDto> findAllStudent();
      
}
