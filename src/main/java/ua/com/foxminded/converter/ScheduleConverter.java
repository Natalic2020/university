package ua.com.foxminded.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import ua.com.foxminded.dao.entity.Period;
import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Schedule;
import ua.com.foxminded.dao.entity.ScheduleItemTeacher;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.dto.WeekScheduleDto;
import ua.com.foxminded.model.enums.StudyStatus;

@Component
public class ScheduleConverter {

    public Schedule convertDtoToEntity(WeekScheduleDto weekScheduleDto) {    
        Period period = new Period().setId(weekScheduleDto.getPeriod().getId().toString())
                .setStartDate(weekScheduleDto.getPeriod().getStartDate())
                .setFinishDate(weekScheduleDto.getPeriod().getStartDate());
        
        
        
        for (ScheduleItemDto  scheduleItemDto : weekScheduleDto.getSchedules()) {
           
            ScheduleItemTeacher scheduleItemTeacher = new ScheduleItemTeacher().setId(scheduleItemDto.getId().toString())
                    .setTeacher(scheduleItemDto.getTeachers().stream().findAny().)
                            .;
            return new Schedule().setPeriod(period).setScheduleItemTeacher(scheduleItemTeacher);
        }
        
        
        
//        return new Student().setId(studentDto.getIdStudent().toString())
//                .setPerson(new Person().setId(studentDto.getIdPerson().toString())
//                        .setFirstName(studentDto.getFirstName())
//                        .setLastName(studentDto.getLastName()))
//        .setCitizenship(studentDto.getCitizenship())
//        .setGrant(studentDto.getGrant())
//        .setStartOfStudy(studentDto.getStartOfStudy())
//        .setStudyStatus(studentDto.getStudyStatus().name());
    }

    public ScheduleDto convertEntityToDto(Schedule schedule) {
        StudentDto  studentDto = 
        ((StudentDto) new StudentDto().setIdStudent(UUID.fromString(student.getId()))
                .setIdPerson(UUID.fromString(student.getPerson().getId()))
               .setFirstName(student.getPerson().getFirstName())
                .setLastName(student.getPerson().getLastName()))  
        .setCitizenship(student.getCitizenship())
        .setGrant(student.getGrant())
        .setStartOfStudy(student.getStartOfStudy());
        Optional.ofNullable(student.getStudyStatus()).ifPresent(ss -> studentDto.setStudyStatus(StudyStatus.valueOf(ss)));      
        return studentDto;
    }
    
}
