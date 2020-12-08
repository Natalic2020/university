package ua.com.foxminded.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ua.com.foxminded.converter.GroupConverter;
import ua.com.foxminded.converter.StudentConverter;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.interfaces.GroupDao;
import ua.com.foxminded.dao.interfaces.StudentDao;
import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.service.interfaces.GroupService;
import ua.com.foxminded.service.interfaces.StudentService;

@Service("studentService")
@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    @Qualifier("studentDao")
    private final StudentDao studentDao;
    
    @Autowired
    @Qualifier("groupDao")
    GroupDao groupDao;
    
    @Autowired
    StudentConverter studentConverter;
    
    @Autowired
    GroupConverter groupConverter;
    
    @Autowired
    GroupService groupService;
    
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Boolean addStudent(StudentDto studentDto) {
        String groupName = Optional.ofNullable(studentDto.getGroup())
                .map(gr -> gr.getName())
                .orElse("");
       studentDto.getContactInfo().setId(UUID.randomUUID());
        
        GroupDto groupDto = groupService.findGroupByName(groupName);
        studentDao.save(studentConverter.convertDtoToEntity((StudentDto) studentDto
                .setGroup(groupDto)
                .setIdPerson(UUID.randomUUID())));
        return !findStudent(studentDto.getIdPerson()).equals(new StudentDto());
    }

    @Override
    public void editStudent(StudentDto studentDto, UUID uuid) {
        String groupName = Optional.ofNullable(studentDto.getGroup())
                .map(gr -> gr.getName())
                .orElse("");
        GroupDto groupDto = groupService.findGroupByName(groupName);
        studentDao.save(studentConverter.convertDtoToEntity(((StudentDto) studentDto.setIdPerson(uuid))
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
}