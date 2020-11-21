package ua.com.foxminded.converter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import ua.com.foxminded.dao.entity.ContactInfo;
import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.model.dto.ContactInfoDto;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.model.enums.Degree;
import ua.com.foxminded.model.enums.Department;
import ua.com.foxminded.model.enums.StudyStatus;

@Component
public class TeacherConverter {

    public Teacher convertDtoToEntity(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        Optional.ofNullable(teacherDto.getIdPerson())
                .ifPresent(ss -> teacher.setIdPerson(ss.toString()));

        teacher.setFirstName(teacherDto.getFirstName())
               .setLastName(teacherDto.getLastName());

        Optional.ofNullable(teacherDto.getDegree())
                .ifPresent(ss -> teacher.setDegree(ss.name()));

        Optional.ofNullable(teacherDto.getDepartment())
                .ifPresent(ss -> teacher.setDepartment(ss.name()));

        teacher.setPermanent(teacherDto.isPermanent())
               .setSalary(teacherDto.getSalary());
        
        ContactInfoDto contactInfoDto = teacherDto.getContactInfo();
        ContactInfo contactInfo = new ContactInfo()
                .setIndex(contactInfoDto.getIndex())
                .setCountry(contactInfoDto.getCountry())
                .setCity(contactInfoDto.getCity())
                .setStreet(contactInfoDto.getStreet())
                .setHouse(contactInfoDto.getHouse())
                .setApartment(contactInfoDto.getApartment())
                .setPhone1(contactInfoDto.getPhone1())
                .setPhone2(contactInfoDto.getPhone2())
                .setEmail(contactInfoDto.getEmail())
                .setId(Optional.of(contactInfoDto.getId())
                        .orElse(UUID.randomUUID()).toString());    
        contactInfo.setPerson(teacher);
        teacher.setContactInfo(contactInfo);
        return teacher;
    }

    public TeacherDto convertEntityToDto(Teacher teacher) {
        
        TeacherDto teacherDto =
        ((TeacherDto) new TeacherDto().setIdPerson(UUID.fromString(teacher.getIdPerson()))
                                             .setFirstName(teacher.getFirstName())
                                             .setLastName(teacher.getLastName()))
        .setPermanent(teacher.isPermanent())
        .setSalary(teacher.getSalary());   
        Optional.ofNullable(teacher.getDegree())
        .ifPresent(ss -> teacherDto.setDegree(Degree.valueOf(ss))); 
        Optional.ofNullable(teacher.getDepartment())
        .ifPresent(ss -> teacherDto.setDepartment(Department.valueOf(ss))); 
        
        ContactInfo contactInfo = teacher.getContactInfo();
        ContactInfoDto contactInfoDto = new ContactInfoDto();
        if (contactInfo != null) {
            contactInfoDto
                    .setIndex(contactInfo.getIndex())
                    .setCountry(contactInfo.getCountry())
                    .setCity(contactInfo.getCity())
                    .setStreet(contactInfo.getStreet())
                    .setHouse(contactInfo.getHouse())
                    .setApartment(contactInfo.getApartment())
                    .setPhone1(contactInfo.getPhone1())
                    .setPhone2(contactInfo.getPhone2())
                    .setEmail(contactInfo.getEmail())
                    .setId(UUID.fromString(contactInfo.getId()));    
        }
        teacherDto.setContactInfo(contactInfoDto);
        return teacherDto;
    }
}
