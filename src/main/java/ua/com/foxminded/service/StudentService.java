package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.Student;

public interface StudentService {

    public void addStudent(Student student);
    public void editStudent(Student student, UUID id);
    public void deleteStudent(UUID id);
    public Student findStudent(UUID id);
    public List<Student> findAllStudent();
    
}
