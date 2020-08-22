package ua.com.foxminded.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Schedule;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.entity.Student;

@Repository
@Qualifier("scheduleDao")
public class ScheduleDaoImpl implements ScheduleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addSchedule(List<Schedule> schedule) {    
        addScheduleItem(schedule.get(0));
//        schedule.forEach(this::addScheduleItem);   
    }    
   
    public void addScheduleItem(Schedule schedule) {
        jdbcTemplate.update("INSERT INTO uni.rooms (id, name ) values (?, ?)",
                schedule.getScheduleItemTeacher().getScheduleItem().getRoom().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getRoom().getName());
        
        jdbcTemplate.update("INSERT INTO uni.subjects (id, name ) values (?, ?)",
                schedule.getScheduleItemTeacher().getScheduleItem().getSubject().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getSubject().getName());
        
        jdbcTemplate.update("INSERT INTO uni.groups (id, name ) values (?, ?)",
                schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getName());
        
        jdbcTemplate.update("INSERT INTO uni.time_slots (id, serial_number, start_time, finish_time ) values (?, ?, ?, ?) " ,
                schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getSerialNumber(),
                schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getStartTime(), schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getFinishTime());
        
        jdbcTemplate.update("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)",
                schedule.getScheduleItemTeacher().getTeacher().getPerson().getId(), schedule.getScheduleItemTeacher().getTeacher().getPerson().getFirstName(),schedule.getScheduleItemTeacher().getTeacher().getPerson().getLastName());
        
        jdbcTemplate.update("INSERT INTO uni.teachers (id, id_person ) values (?, ?)",
                schedule.getScheduleItemTeacher().getTeacher().getId(), schedule.getScheduleItemTeacher().getTeacher().getPerson().getId());
        
        jdbcTemplate.update("INSERT INTO uni.schedule_items (id, id_group, id_subject, id_room, id_time_slot, day_of_week) values (?, ?, ?, ?, ?, ? )",
                schedule.getScheduleItemTeacher().getScheduleItem().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getId(),
                schedule.getScheduleItemTeacher().getScheduleItem().getSubject().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getRoom().getId(),
                schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getDayOfWeek());
        
        jdbcTemplate.update("INSERT INTO uni.schedule_items_teachers (id, id_schedule_item, id_teacher ) values (?, ?, ?)",
                schedule.getScheduleItemTeacher().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getId(), schedule.getScheduleItemTeacher().getTeacher().getId());
        
        jdbcTemplate.update("INSERT INTO uni.periods (id, start_date, finish_date ) values (?, ?, ?)",
                schedule.getPeriod().getId(), schedule.getPeriod().getStartDate(), schedule.getPeriod().getFinishDate());
        
        jdbcTemplate.update("INSERT INTO uni.schedule (id_period, id_schedule_items_teacher ) values (?, ?)",
               schedule.getPeriod().getId(), schedule.getScheduleItemTeacher().getId());
         System.out.println("Schedule Added!!");      
    }

    @Override
    public void fillTable() { 
        
        jdbcTemplate.update("INSERT INTO uni.rooms (id, name ) values (?, ?)",
                '1', "room1");
        jdbcTemplate.update("INSERT INTO uni.subjects (id, name ) values (?, ?)",
                '1', "maths");
        jdbcTemplate.update("INSERT INTO uni.groups (id, name ) values (?, ?)",
                '1', "gr_1");
        jdbcTemplate.update("INSERT INTO uni.groups (id, name ) values (?, ?)",
                '1', "gr_1");
        jdbcTemplate.update("INSERT INTO uni.time_slots (id, serial_number, start_time, finish_time ) values (?, ?, ?, ?) " ,
                 "1","1", LocalTime.of(8,0,0), LocalTime.of(9,30,0));
        jdbcTemplate.update("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)",
                '1',"Vanja","Ivanov");
        jdbcTemplate.update("INSERT INTO uni.teachers (id, id_person ) values (?, ?)",
                '1', '1');
        jdbcTemplate.update("INSERT INTO uni.schedule_items (id, id_group, id_subject, id_room, id_time_slot, day_of_week)" +
               " values (?, ?, ?, ?, ? )", '1', '1', '1', '1', '1', "Monday");
        jdbcTemplate.update("INSERT INTO uni.schedule_items_teachers (id, id_schedule_item, id_teacher ) values (?, ?, ?)",
                '1', '1', '1');
        jdbcTemplate.update("INSERT INTO uni.periods (id, start_date, finish_date ) values (?, ?, ?)",
                '1', LocalDate.of(2019, 1, 1), LocalDate.of(2019, 6, 1));
        jdbcTemplate.update("INSERT INTO uni.schedule (id_period, id_schedule_items_teacher ) values (?, ?)",
               '1', '1');
         System.out.println("Table Schedule Added!!");   
    }
    
    
    @Override
    public void editItemSchedule(ScheduleItem scheduleItem, String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteItemSchedule(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<ScheduleItem> findScheduleStudent(String id, String startDate, String finishDate) {
        List<ScheduleItem> schedule = jdbcTemplate.query("SELECT * FROM uni.students where s.id_person = ? ",
                new BeanPropertyRowMapper(ScheduleItem.class));
        return schedule;
    }

    @Override
    public List<ScheduleItem> findScheduleTeacher(String id, String startDate, String finishDate) {
        List<ScheduleItem> schedule = jdbcTemplate.query("Select * " + 
                "from uni.persons p," + 
                "uni.teachers t," + 
                "uni.groups g," + 
                "uni.subjects su," + 
                "uni.rooms r," + 
                "uni.time_slots ts," + 
                "uni.schedule_items si," + 
                "uni.schedule_items_teachers sit," + 
                "uni.periods per," + 
                "uni.schedule sch" + 
                "where sch.id_period = per.id" + 
                "and sch.id_schedule_items_teacher = sit.id" + 
                "and sit.id_teacher = t.id" + 
                "and t.id_person = p.id" + 
                "and sit.id_schedule_item = si.id" + 
                "and si.id_group = g.id" + 
                "and si.id_subject = su.id" + 
                "and si.id_room = r.id" + 
                "and si.id_time_slot = ts.id",
                new ScheduleMapper());
        return schedule;
    }
}
