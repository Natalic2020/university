package ua.com.foxminded.dao.interfaces;

import java.util.List;

import ua.com.foxminded.dao.entity.Student;

public interface StudentDao {

    public void addStudent(Student student);
    public void editStudent(Student student);
    public void deleteStudent(String id);
    public List<Student> findStudent(String id);
    public List<Student> findAllStudent();    
}
