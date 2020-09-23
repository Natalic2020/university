package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ua.com.foxminded.converter.StudentConverter;
import ua.com.foxminded.dao.interfaces.StudentDao;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.service.interfaces.StudentService;

@Service("studentService")
@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;
    
    @Autowired
    StudentConverter studentConverter;
    
    @Override
    public void addStudent(StudentDto studentDto) {
        studentDao.addStudent(studentConverter.convertDtoToEntity((StudentDto) studentDto.setIdStudent(UUID.randomUUID()).setIdPerson(UUID.randomUUID())));
    }

    @Override
    public void editStudent(StudentDto studentDto) {
        studentDao.editStudent(studentConverter.convertDtoToEntity(studentDto));
    }

    @Override
    public void deleteStudent(UUID id) {
        studentDao.deleteStudent(id.toString());
    }

    @Override
    public List<StudentDto> findStudent(String text) {
        return studentDao.findStudent(text).stream().map(studentConverter::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> findAllStudent() {
        return studentDao.findAllStudent().stream().map(studentConverter::convertEntityToDto).collect(Collectors.toList());
    }
}
