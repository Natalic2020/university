package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.*;

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
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.dao.interfaces.StudentDao;

@ExtendWith(MockitoExtension.class)
class SudentServiceTest {

    @Mock
    StudentDao studentDao;
    
    @InjectMocks
    StudentServiceImpl studentServise; 

    @Test
    void deleteStudent() {
        studentServise.deleteStudent(UUID.randomUUID());
    }
    
}
