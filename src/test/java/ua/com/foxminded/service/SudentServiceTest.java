package ua.com.foxminded.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.interfaces.StudentDao;
import ua.com.foxminded.model.dto.StudentDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class SudentServiceTest {

    @Mock
    StudentDao studentDao;
    
    @InjectMocks
    StudentServiceImpl studentServise; 

    @Test
    void findStudentTest() {
        Optional<Student> student = Optional.ofNullable((Student) new Student().setIdPerson("bea86405-4378-4ba3-8224-c7d3173ee7db"));
        
        Mockito.when(studentDao.findById(any()))
        .thenReturn(student);
//        given(studentDao.findById(any())).willReturn(student);

        StudentDto studentFound = studentServise.findStudent(UUID.fromString("bea86405-4378-4ba3-8224-c7d3173ee7db"));
        
        assertThat(studentFound).isNotNull();
        verify(studentDao).findById("1");
        
//      //given
//        List<PetType> petTypeList = new ArrayList<>();
//        given(petRepository.findPetTypes()).willReturn(petTypeList);
//        //when
//        Collection<PetType> returnedPetTypes = service.findPetTypes();
//
//        //then
//        then(petRepository).should().findPetTypes();
//        assertThat(returnedPetTypes).isNotNull();

    }
    
    
    @Test
    void findAllTest() {
        StudentDto student = new StudentDto();
        List<StudentDto> students = new ArrayList<StudentDto>();
        students.add(student);
        
//        when(studentDao.findAll()).thenReturn(students);
        
        List<StudentDto> studentsFound = studentServise.findAllStudent();
          
       verify(studentDao).findAll();
       
       assertThat(studentsFound).hasSize(1);
    }
    
   

    @Test
    void deleteStudent() {
        studentServise.deleteStudent(UUID.randomUUID());
    }
    
}
