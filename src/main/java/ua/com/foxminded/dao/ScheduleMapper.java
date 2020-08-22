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
import ua.com.foxminded.dao.entity.TimeSlot;

public class ScheduleMapper implements RowMapper<Schedule> {

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Schedule schedule = new Schedule();
        ScheduleItemTeacher scheduleItemTeacher = new ScheduleItemTeacher();
        Group group = new Group().setName(rs.getString("name"));
        Room room = new Room().setName(rs.getString("name"));
        Subject subject = new Subject().setName(rs.getString("name"));
        TimeSlot timeSlot = new TimeSlot().setSerialNumber(rs.getInt("serialNumber"));
        ScheduleItem scheduleItem = new ScheduleItem().setDayOfWeek(rs.getString("day_of_week"))
        .setGroup(group).setRoom(room).setSubject(subject).setTimeSlot(timeSlot);
        
        return schedule;
    }

}
