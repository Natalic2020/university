package ua.com.foxminded.model.validation;

import org.junit.jupiter.api.Test;
import ua.com.foxminded.dao.entity.ContactInfo;
import ua.com.foxminded.model.dto.ContactInfoDto;
import ua.com.foxminded.model.dto.StudentDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import ua.com.foxminded.model.dto.TeacherDto;

import javax.validation.*;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ValidatorTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private StudentDto student = new StudentDto();
    private TeacherDto teacher = new TeacherDto();
    private ContactInfoDto contactInfo = new ContactInfoDto();


    @Test
    public void studentValid_whenSearchErrorValidation_thenReturn6() throws Exception {
        student.setFirstName(null);

        Set<ConstraintViolation<StudentDto>> constraintViolations = validator
                .validate(student);
        assertTrue(constraintViolations.size() == 6);
    }

    @Test
    public void teacherValid_whenSearchErrorValidation_thenReturn4() throws Exception {
        teacher.setFirstName(null);

        Set<ConstraintViolation<TeacherDto>> constraintViolations = validator
                .validate(teacher);

        assertTrue(constraintViolations.size() == 4);
    }

    @Test
    public void contactInfoValid_whenSearchErrorValidation_thenReturn10() throws Exception {

        Set<ConstraintViolation<ContactInfoDto>> constraintViolations = validator
                .validate(contactInfo);

        assertTrue(constraintViolations.size() == 10);
    }
}
