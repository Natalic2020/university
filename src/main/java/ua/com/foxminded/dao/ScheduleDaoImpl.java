package ua.com.foxminded.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Schedule;
import ua.com.foxminded.dao.entity.ScheduleItem;

@Repository
@Qualifier("scheduleDao")
public class ScheduleDaoImpl implements ScheduleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addSchedule(Schedule schedule) {
        jdbcTemplate.update("INSERT INTO uni.shedule (id_period, id_schedule_items_teacher ) values (?, ?)",
               schedule.getPeriod().getId(), schedule.getScheduleItemTeacher().getId());
         System.out.println("Schedule Added!!");
        
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
        List<ScheduleItem> schedule = jdbcTemplate.query("SELECT * FROM uni.teachers where s.id_person = ? ",
                new BeanPropertyRowMapper(ScheduleItem.class));
        return schedule;
    }    
}
