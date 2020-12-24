package ua.com.foxminded.model.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.foxminded.model.dto.ContactInfoDto;
import ua.com.foxminded.model.dto.ContactInfoDto;
import ua.com.foxminded.model.dto.TeacherDto;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactInfoValidatorTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private ContactInfoDto contactInfoDto;

    String uuidPerson = "d4d8b36a-4435-11eb-b378-0242ac130002";
    String uuidContactInfo = "2a6e5b4a-4436-11eb-b378-0242ac130002";

    @BeforeEach
    void initEach() {
        contactInfoDto = new ContactInfoDto()
                        .setId(UUID.fromString(uuidContactInfo))
                        .setIndex(80339)
                        .setCountry("China")
                        .setCity("Hong Kong")
                        .setStreet("Mau the dum")
                        .setHouse("1/1")
                        .setApartment(1)
                        .setEmail("kuku@ru.muku")
                        .setPhone1("123456789")
                        .setPhone2("98741236547");
    }

    @Test
    public void contactInfoValidIndex_whenIndexNull_thenReturnIndexErrorValidationNotBlank() throws Exception {
        contactInfoDto.setIndex(1);

        Set<ConstraintViolation<ContactInfoDto>> constraintViolations = validator
                .validate(contactInfoDto);
        System.out.println(constraintViolations.size());
        assertTrue(constraintViolations.size() == 1);
        List<ConstraintViolation> constraintViolation = constraintViolations.stream().collect(Collectors.toList());

        assertTrue(constraintViolation.get(0).getMessageTemplate().equals("{javax.validation.constraints.Min.message}"));
        assertTrue(constraintViolation.get(0).getPropertyPath().toString().equals("index"));
        assertTrue(constraintViolation.get(0).getInvalidValue().equals(1));
    }

    @Test
    public void contactInfoValidCountry_whenCountryNull_thenReturnCountryErrorValidationNotBlank() throws Exception {
        contactInfoDto.setCountry(null);

        Set<ConstraintViolation<ContactInfoDto>> constraintViolations = validator
                .validate(contactInfoDto);
        System.out.println(constraintViolations.size());
        assertTrue(constraintViolations.size() == 1);
        List<ConstraintViolation>   constraintViolation = constraintViolations.stream().collect(Collectors.toList());

        assertTrue(constraintViolation.get(0).getMessageTemplate().equals("{javax.validation.constraints.NotBlank.message}"));
        assertTrue(constraintViolation.get(0).getPropertyPath().toString().equals("country"));
        assertTrue(constraintViolation.get(0).getInvalidValue() == null);
    }

    @Test
    public void contactInfoValidCity_whenCityNull_thenReturnCityErrorValidationNotBlank() throws Exception {
        contactInfoDto.setCity(null);

        Set<ConstraintViolation<ContactInfoDto>> constraintViolations = validator
                .validate(contactInfoDto);
        System.out.println(constraintViolations.size());
        assertTrue(constraintViolations.size() == 1);
        List<ConstraintViolation>   constraintViolation = constraintViolations.stream().collect(Collectors.toList());

        assertTrue(constraintViolation.get(0).getMessageTemplate().equals("{javax.validation.constraints.NotBlank.message}"));
        assertTrue(constraintViolation.get(0).getPropertyPath().toString().equals("city"));
        assertTrue(constraintViolation.get(0).getInvalidValue() == null);
    }

    @Test
    public void contactInfoValidStreet_whenStreetNull_thenReturnStreetErrorValidationNotBlank() throws Exception {
        contactInfoDto.setStreet(null);

        Set<ConstraintViolation<ContactInfoDto>> constraintViolations = validator
                .validate(contactInfoDto);
        System.out.println(constraintViolations.size());
        assertTrue(constraintViolations.size() == 1);
        List<ConstraintViolation>   constraintViolation = constraintViolations.stream().collect(Collectors.toList());

        assertTrue(constraintViolation.get(0).getMessageTemplate().equals("{javax.validation.constraints.NotBlank.message}"));
        assertTrue(constraintViolation.get(0).getPropertyPath().toString().equals("street"));
        assertTrue(constraintViolation.get(0).getInvalidValue() == null);
    }

    @Test
    public void contactInfoValidHouse_whenHouseNull_thenReturnHouseErrorValidationNotBlank() throws Exception {
        contactInfoDto.setHouse(null);

        Set<ConstraintViolation<ContactInfoDto>> constraintViolations = validator
                .validate(contactInfoDto);
        System.out.println(constraintViolations.size());
        assertTrue(constraintViolations.size() == 1);
        List<ConstraintViolation>   constraintViolation = constraintViolations.stream().collect(Collectors.toList());

        assertTrue(constraintViolation.get(0).getMessageTemplate().equals("{javax.validation.constraints.NotBlank.message}"));
        assertTrue(constraintViolation.get(0).getPropertyPath().toString().equals("house"));
        assertTrue(constraintViolation.get(0).getInvalidValue() == null);
    }

    @Test
    public void contactInfoValidApartment_whenApartmentNull_thenReturnApartmentErrorValidationPositive() throws Exception {
        contactInfoDto.setApartment(-25);

        Set<ConstraintViolation<ContactInfoDto>> constraintViolations = validator
                .validate(contactInfoDto);
        System.out.println(constraintViolations.size());
        assertTrue(constraintViolations.size() == 1);
        List<ConstraintViolation> constraintViolation = constraintViolations.stream().collect(Collectors.toList());

        assertTrue(constraintViolation.get(0).getMessageTemplate().equals("{javax.validation.constraints.Positive.message}"));
        assertTrue(constraintViolation.get(0).getPropertyPath().toString().equals("apartment"));
        assertTrue(constraintViolation.get(0).getInvalidValue().equals(-25));
    }

    @Test
    public void contactInfoValidPhone1_whenPhone1Null_thenReturnPhone1ErrorValidationPattern() throws Exception {
        contactInfoDto.setPhone1("25H");

        Set<ConstraintViolation<ContactInfoDto>> constraintViolations = validator
                .validate(contactInfoDto);
        System.out.println(constraintViolations.size());
        assertTrue(constraintViolations.size() == 1);
        List<ConstraintViolation>   constraintViolation = constraintViolations.stream().collect(Collectors.toList());

        assertTrue(constraintViolation.get(0).getMessageTemplate().equals("{javax.validation.constraints.Pattern.message}"));
        assertTrue(constraintViolation.get(0).getPropertyPath().toString().equals("phone1"));
        assertTrue(constraintViolation.get(0).getInvalidValue().equals("25H"));
    }

    @Test
    public void contactInfoValidPhone2_whenPhone2Null_thenReturnPhone2ErrorValidationPattern() throws Exception {
        contactInfoDto.setPhone2("25H");

        Set<ConstraintViolation<ContactInfoDto>> constraintViolations = validator
                .validate(contactInfoDto);
        System.out.println(constraintViolations.size());
        assertTrue(constraintViolations.size() == 1);
        List<ConstraintViolation>   constraintViolation = constraintViolations.stream().collect(Collectors.toList());

        assertTrue(constraintViolation.get(0).getMessageTemplate().equals("{javax.validation.constraints.Pattern.message}"));
        assertTrue(constraintViolation.get(0).getPropertyPath().toString().equals("phone2"));
        assertTrue(constraintViolation.get(0).getInvalidValue().equals("25H"));
    }
    
    @Test
    public void contactInfoValidEmail_whenEmailNull_thenReturnEmailErrorValidationEmail() throws Exception {
        contactInfoDto.setEmail("25H");

        Set<ConstraintViolation<ContactInfoDto>> constraintViolations = validator
                .validate(contactInfoDto);
        System.out.println(constraintViolations.size());
        assertTrue(constraintViolations.size() == 1);
        List<ConstraintViolation>   constraintViolation = constraintViolations.stream().collect(Collectors.toList());

        assertTrue(constraintViolation.get(0).getMessageTemplate().equals("{javax.validation.constraints.Email.message}"));
        assertTrue(constraintViolation.get(0).getPropertyPath().toString().equals("email"));
        assertTrue(constraintViolation.get(0).getInvalidValue().equals("25H"));
    }
}
