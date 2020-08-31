package ua.com.foxminded.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import ua.com.foxminded.dao.entity.Group;
import ua.com.foxminded.dao.entity.Period;
import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Room;
import ua.com.foxminded.dao.entity.Schedule;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.entity.ScheduleItemTeacher;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.entity.Subject;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.entity.TimeSlot;
import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.model.dto.RoomDto;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.dto.SubjectDto;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.model.dto.TimeSlotDto;
import ua.com.foxminded.model.dto.WeekScheduleDto;
import ua.com.foxminded.model.enums.DayOfWeek;
import ua.com.foxminded.model.enums.StudyStatus;

@Component
public class ScheduleConverter {

    public static List<Schedule> convertDtoToEntity(WeekScheduleDto weekScheduleDto) { 
        
        List<Schedule> schedule = new ArrayList<Schedule>();
        
        Period period = new Period().setId(weekScheduleDto.getPeriod().getId().toString())
                .setStartDate(weekScheduleDto.getPeriod().getStartDate())
                .setFinishDate(weekScheduleDto.getPeriod().getFinishDate());        
        
        for (ScheduleItemDto  scheduleItemDto : weekScheduleDto.getSchedules()) {
            
           List<Student> students = new ArrayList<>();
           
           scheduleItemDto.getGroup().getStudents().forEach(studentDto -> {
               Person person = new Person().setId(studentDto.getIdPerson().toString())
                       .setFirstName(studentDto.getFirstName())
                       .setLastName(studentDto.getLastName());
               Student student = new Student().setId(studentDto.getIdStudent().toString()).setPerson(person);
               students.add(student);
           });
           
            Group group = new Group().setId(scheduleItemDto.getGroup().getId().toString())
                    .setName(scheduleItemDto.getGroup().getName()).setStudents(students);
            
            Room room = new Room().setId(scheduleItemDto.getRoom().getId().toString())
                    .setName(scheduleItemDto.getRoom().getName());
            
            Subject subject = new Subject().setId(scheduleItemDto.getSubject().getId().toString())
                    .setName(scheduleItemDto.getSubject().getName());
            
            TimeSlot timeSlot = new TimeSlot().setId(scheduleItemDto.getTimeSlot().getId().toString())
                    .setSerialNumber(scheduleItemDto.getTimeSlot().getSerialNumber())
                    .setStartTime(scheduleItemDto.getTimeSlot().getStartTime())
                    .setFinishTime(scheduleItemDto.getTimeSlot().getFinishTime());
            
            ScheduleItem scheduleItem = new ScheduleItem().setId(scheduleItemDto.getId().toString())
                    .setDayOfWeek(scheduleItemDto.getDayOfWeek().name())
                    .setGroup(group)
                    .setRoom(room)
                    .setSubject(subject)
                    .setTimeSlot(timeSlot);
            
            for (TeacherDto teacherDto : scheduleItemDto.getTeachers()) {
                
                Person person = new Person().setId(teacherDto.getIdPerson().toString())
                        .setFirstName(teacherDto.getFirstName())
                        .setLastName(teacherDto.getLastName());
                
                Teacher teacher = new Teacher().setId(teacherDto.getIdTeacher().toString())
                        .setPerson(person);
                        
                ScheduleItemTeacher scheduleItemTeacher = new ScheduleItemTeacher().setId(UUID.randomUUID().toString())
                        .setScheduleItem(scheduleItem).setTeacher(teacher);
                
                schedule.add(new Schedule().setPeriod(period).setScheduleItemTeacher(scheduleItemTeacher));
            }   
        }
        return schedule;
    }

    public static ScheduleItemDto convertEntityToDto(Schedule schedule) {

        ScheduleItemDto scheduleItemDto = new ScheduleItemDto();
        
        if (schedule == null) {
            return scheduleItemDto;
        }
        
        Optional.ofNullable(schedule.getScheduleItemTeacher().getScheduleItem().getDayOfWeek()).ifPresent(ss -> scheduleItemDto.setDayOfWeek(DayOfWeek.valueOf(ss))); 
        scheduleItemDto.setGroup(new GroupDto().setName(schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getName()));
        scheduleItemDto.setRoom(new RoomDto().setName(schedule.getScheduleItemTeacher().getScheduleItem().getRoom().getName()));
        scheduleItemDto.setSubject(new SubjectDto().setName(schedule.getScheduleItemTeacher().getScheduleItem().getSubject().getName()));       
        scheduleItemDto.setTimeSlot(new TimeSlotDto().setSerialNumber(schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getSerialNumber()));
        
        return scheduleItemDto;
        }
    }
