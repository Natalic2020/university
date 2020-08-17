package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.dao.entity.Student;

public interface StudentDao {

    public void addStudent(Student student);
    public void editStudent(Student student, String id);
    public void deleteStudent(String id);
    public Student findStudent(String id);
    public List<Student> findAllStudent();    
}