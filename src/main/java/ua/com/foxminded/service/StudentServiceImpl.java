package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.dao.PersonDao;
import ua.com.foxminded.dao.StudentDao;
import ua.com.foxminded.model.dto.StudentDto;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;
    
    @Override
    public void addStudent(StudentDto student) {
        studentDao.addStudent(student);
    }

    @Override
    public void editStudent(StudentDto student, UUID id) {
        studentDao.editStudent(student, id);
    }

    @Override
    public void deleteStudent(UUID id) {
        studentDao.deleteStudent(id);
    }

    @Override
    public StudentDto findStudent(UUID id) {
        return studentDao.findStudent(id);
    }

    @Override
    public List<StudentDto> findAllStudent() {
        return studentDao.findAllStudent();
    }
}
