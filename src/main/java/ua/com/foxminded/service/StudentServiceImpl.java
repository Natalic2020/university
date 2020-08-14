package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.dao.PersonDao;
import ua.com.foxminded.dao.StudentDao;
import ua.com.foxminded.model.dto.Student;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;
    
    @Override
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    @Override
    public void editStudent(Student student, UUID id) {
        studentDao.editStudent(student, id);
    }

    @Override
    public void deleteStudent(UUID id) {
        studentDao.deleteStudent(id);
    }

    @Override
    public Student findStudent(UUID id) {
        return studentDao.findStudent(id);
    }

    @Override
    public List<Student> findAllStudent() {
        return studentDao.findAllStudent();
    }
}
