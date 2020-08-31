package ua.com.foxminded.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.com.foxminded.dao.entity.Group;
import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Room;
import ua.com.foxminded.dao.entity.Schedule;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.entity.ScheduleItemTeacher;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.entity.Subject;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.entity.TimeSlot;

public class ScheduleMapper implements RowMapper<Schedule> {

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person().setFirstName(rs.getString("first_name")).setLastName(rs.getString("last_name"));
        Teacher teacher = new Teacher().setPerson(person);
        
        Group group = new Group().setName(rs.getString("name_group"));
        Room room = new Room().setName(rs.getString("name_room"));
        Subject subject = new Subject().setName(rs.getString("name_subject"));
        TimeSlot timeSlot = new TimeSlot().setSerialNumber(rs.getInt("serial_number"));
        ScheduleItem scheduleItem = new ScheduleItem().setDayOfWeek(rs.getString("day_of_week"))
        .setGroup(group).setRoom(room).setSubject(subject).setTimeSlot(timeSlot);
        ScheduleItemTeacher scheduleItemTeacher = new ScheduleItemTeacher().setScheduleItem(scheduleItem).setTeacher(teacher);
        Schedule schedule = new Schedule().setScheduleItemTeacher(scheduleItemTeacher);
        return schedule;
    }
}
