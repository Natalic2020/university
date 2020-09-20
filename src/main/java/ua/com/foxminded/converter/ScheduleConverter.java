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

    public static  Schedule convertDtoToEntity(WeekScheduleDto weekScheduleDto) {

        
        List<ScheduleItem> scheduleItems = new ArrayList<>();

        Period period = new Period();
        Optional.ofNullable(weekScheduleDto.getPeriod().getId()).ifPresent(ss -> period.setId(ss.toString()));

        period.setStartDate(weekScheduleDto.getPeriod().getStartDate())
              .setFinishDate(weekScheduleDto.getPeriod().getFinishDate());

        for (ScheduleItemDto scheduleItemDto : weekScheduleDto.getSchedules()) {

            List<Student> students = new ArrayList<>();

            Group group = new Group();
            if (scheduleItemDto.getGroup() != null) {
            scheduleItemDto.getGroup().getStudents().forEach(studentDto ->
                {
                    Person person = new Person();
                    Optional.ofNullable(studentDto.getIdPerson()).ifPresent(ss -> person.setId(ss.toString()));

                    person.setFirstName(studentDto.getFirstName())
                          .setLastName(studentDto.getLastName());
                    Student student = new Student();
                    Optional.ofNullable(studentDto.getIdStudent()).ifPresent(ss -> student.setId(ss.toString()));
                    student.setPerson(person);
                    students.add(student);
                });

            
            Optional.ofNullable(scheduleItemDto.getGroup().getId()).ifPresent(ss -> group.setId(ss.toString()));
            group.setName(scheduleItemDto.getGroup().getName())
                 .setStudents(students);
            }
            
            Room room = new Room();
            if (scheduleItemDto.getRoom()!= null) {
                Optional.ofNullable(scheduleItemDto.getRoom().getId()).ifPresent(ss -> room.setId(ss.toString()));
                room.setName(scheduleItemDto.getRoom().getName());
            }
            
            Subject subject = new Subject();
            
            if (scheduleItemDto.getSubject()!= null) {
                Optional.ofNullable(scheduleItemDto.getSubject().getId()).ifPresent(ss -> subject.setId(ss.toString()));
                subject.setName(scheduleItemDto.getSubject().getName());
            }
            
            TimeSlot timeSlot = new TimeSlot();
            
            if (scheduleItemDto.getTimeSlot() != null) {
                Optional.ofNullable(scheduleItemDto.getTimeSlot().getId())
                        .ifPresent(ss -> timeSlot.setId(ss.toString()));
                timeSlot.setSerialNumber(scheduleItemDto.getTimeSlot().getSerialNumber())
                        .setStartTime(scheduleItemDto.getTimeSlot().getStartTime())
                        .setFinishTime(scheduleItemDto.getTimeSlot().getFinishTime());
            }
            
            Person person = new Person();
            Optional.ofNullable(scheduleItemDto.getTeacher().getIdPerson()).ifPresent(ss -> person.setId(ss.toString()));

            person.setFirstName(scheduleItemDto.getTeacher().getFirstName())
                  .setLastName(scheduleItemDto.getTeacher().getLastName());
            

            Teacher teacher = new Teacher();
            Optional.ofNullable(scheduleItemDto.getTeacher().getIdTeacher()).ifPresent(ss -> teacher.setId(ss.toString()));
            teacher.setPerson(person);
            
            
            ScheduleItem scheduleItem = new ScheduleItem();
            
            Optional.ofNullable(scheduleItemDto.getId()).ifPresent(ss -> scheduleItem.setId(ss.toString()));
            
            Optional.ofNullable(scheduleItemDto.getDayOfWeek()).ifPresent(ss -> scheduleItem.setDayOfWeek(ss.name()));
            scheduleItem.setGroup(group)
                        .setRoom(room)
                        .setSubject(subject)
                        .setTimeSlot(timeSlot)
                        .setTeacher(teacher);

            scheduleItems.add(scheduleItem);
        }
        
        return new Schedule().setPeriod(period).setScheduleItems(scheduleItems);
    }

    public static  WeekScheduleDto convertEntityToDto(Schedule schedule) {

        WeekScheduleDto weekScheduleDto = new WeekScheduleDto();
        List<ScheduleItemDto> scheduleItemsDto = new ArrayList<>(); 

        if (schedule == null) {
            return weekScheduleDto;
        }
        ScheduleItemDto scheduleItemDto = new ScheduleItemDto();
        schedule.getScheduleItems().forEach(scheduleItem -> {
        
        Optional.ofNullable(scheduleItem.getDayOfWeek())
                .ifPresent(ss -> scheduleItemDto.setDayOfWeek(DayOfWeek.valueOf(ss)));
        scheduleItemDto.setGroup(
                new GroupDto().setName(scheduleItem.getGroup().getName()));
        scheduleItemDto.setRoom(
                new RoomDto().setName(scheduleItem.getRoom().getName()));
        scheduleItemDto.setSubject(
                new SubjectDto().setName(scheduleItem.getSubject().getName()));
        scheduleItemDto.setTimeSlot(new TimeSlotDto().setSerialNumber(
                scheduleItem.getTimeSlot().getSerialNumber()));

        scheduleItemsDto.add(scheduleItemDto);
        });
        
        return weekScheduleDto.setSchedules(scheduleItemsDto);
    }
}
