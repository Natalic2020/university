package ua.com.foxminded.service;

import java.util.ArrayList;
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
        studentDao.addStudent(studentConverter.convertDtoToEntity((StudentDto) studentDto
                .setIdStudent(UUID.randomUUID())
                .setIdPerson(UUID.randomUUID())));
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
        return studentDao.findStudent(text).stream()
                .map(studentConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<StudentDto> findAllStudent() {
//        return studentDao.findAllStudent().stream()
//                .map(studentConverter::convertEntityToDto)
//                .collect(Collectors.toList());
//    }
    
  @Override
  public List<StudentDto> findAllStudent() {
      List<StudentDto> students = new ArrayList<>();
      students.add((StudentDto) new StudentDto().setIdStudent(UUID.fromString("a1f520ba-06fe-11eb-adc1-0242ac120002")).setFirstName("Nata").setLastName("Ivanov58"));
      students.add((StudentDto) new StudentDto().setIdStudent(UUID.fromString("71ec8ca1-a435-4bcf-bf48-1d239a5145a6")).setFirstName("Ira").setLastName("Petrov25"));
      return students;
  }
}
