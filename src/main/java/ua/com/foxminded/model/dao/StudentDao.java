package ua.com.foxminded.model.dao;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.Student;

public interface StudentDao {
    
    public void insert(Student student);
    public List<Student> receiveAllStudents();

}
