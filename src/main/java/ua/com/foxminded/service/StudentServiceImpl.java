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
    StudentConverter studentTransformer;
    
    @Override
    public void addStudent(StudentDto student) {
        studentDao.addStudent(studentTransformer.convertDtoToEntity(student));
    }

    @Override
    public void editStudent(StudentDto student, UUID id) {
        studentDao.editStudent(studentTransformer.convertDtoToEntity(student), id.toString());
    }

    @Override
    public void deleteStudent(UUID id) {
        studentDao.deleteStudent(id.toString());
    }

    @Override
    public StudentDto findStudent(UUID id) {
        return studentTransformer.convertEntityToDto(studentDao.findStudent(id.toString()));
    }

    @Override
    public List<StudentDto> findAllStudent() {
        return studentDao.findAllStudent().stream().map(studentTransformer::convertEntityToDto).collect(Collectors.toList());
    }
}
