package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.converter.StudentConverter;
import ua.com.foxminded.dao.StudentDao;
import ua.com.foxminded.model.dto.StudentDto;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;
    
    @Autowired
    StudentConverter studentConverter;
    
    @Override
    public void addStudent(StudentDto student) {
        studentDao.addStudent(studentConverter.convertDtoToEntity(student));
    }

    @Override
    public void editStudent(StudentDto student, UUID id) {
        studentDao.editStudent(studentConverter.convertDtoToEntity(student), id.toString());
    }

    @Override
    public void deleteStudent(UUID id) {
        studentDao.deleteStudent(id.toString());
    }

    @Override
    public List<StudentDto> findStudent(UUID id) {
        return studentDao.findStudent(id.toString()).stream().map(studentConverter::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> findAllStudent() {
        return studentDao.findAllStudent().stream().map(studentConverter::convertEntityToDto).collect(Collectors.toList());
    }
}
