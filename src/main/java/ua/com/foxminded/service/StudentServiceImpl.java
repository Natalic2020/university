package ua.com.foxminded.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ua.com.foxminded.converter.GroupConverter;
import ua.com.foxminded.converter.StudentConverter;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.interfaces.GroupDao;
import ua.com.foxminded.dao.interfaces.StudentDao;
import ua.com.foxminded.model.dto.ContactInfoDto;
import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.service.interfaces.GroupService;
import ua.com.foxminded.service.interfaces.StudentService;

@Service("studentService")
@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    @Qualifier("studentDao")
    StudentDao studentDao;
    
    @Autowired
    @Qualifier("groupDao")
    GroupDao groupDao;
    
    @Autowired
    StudentConverter studentConverter;
    
    @Autowired
    GroupConverter groupConverter;
    
    @Autowired
    GroupService groupService;
    
    @Override
    public void addStudent(StudentDto studentDto) {
        String groupName = Optional.ofNullable(studentDto.getGroup())
                .map(gr -> gr.getName())
                .orElse("");
       studentDto.getContactInfo().setId(UUID.randomUUID());
        
        GroupDto groupDto = groupService.findGroupByName(groupName);
        studentDao.save(studentConverter.convertDtoToEntity((StudentDto) studentDto
                .setGroup(groupDto)
                .setIdPerson(UUID.randomUUID())));
    }

    @Override
    public void editStudent(StudentDto studentDto, UUID uuid) {
        String groupName = Optional.ofNullable(studentDto.getGroup())
                .map(gr -> gr.getName())
                .orElse("");
        GroupDto groupDto = groupService.findGroupByName(groupName);
        studentDao.save(studentConverter.convertDtoToEntity((StudentDto) studentDto
                .setGroup(groupDto)));
    }

    @Override
    public void deleteStudent(UUID uuid) {
        studentDao.deleteById(uuid.toString());
    }

    @Override
    public StudentDto findStudent(UUID uuid) {
        return studentConverter.convertEntityToDto(studentDao.findById(uuid.toString()).orElse(new Student()));
    }

    @Override
    public List<StudentDto> findAllStudent() {
        List<StudentDto> students = new ArrayList<StudentDto>();
        studentDao.findAll().forEach(student -> {
            students.add(studentConverter.convertEntityToDto(student));
        });
        return students;
    }
    
  @Override
  public List<StudentDto> findListStudent() {
      List<StudentDto> students = new ArrayList<>();
      students.add((StudentDto) new StudentDto().setIdStudent(UUID.fromString("a1f520ba-06fe-11eb-adc1-0242ac120002")).setFirstName("Nata").setLastName("Ivanov58"));
      students.add((StudentDto) new StudentDto().setIdStudent(UUID.fromString("71ec8ca1-a435-4bcf-bf48-1d239a5145a6")).setFirstName("Ira").setLastName("Petrov25"));
      return students;
  }
}