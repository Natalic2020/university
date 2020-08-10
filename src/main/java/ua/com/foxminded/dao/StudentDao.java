package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.foxminded.model.dto.Student;


public interface StudentDao {
    
    public void insert(Student student);
    
    public List<Student> receiveAllStudents();

}
