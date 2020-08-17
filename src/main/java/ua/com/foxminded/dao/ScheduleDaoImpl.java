package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.model.dto.WeekScheduleDto;

@Repository
@Qualifier("weekScheduleDao")
public class ScheduleDaoImpl implements ScheduleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addWeekSchedule(ScheduleItem scheduleItem) {
        jdbcTemplate.update("INSERT INTO uni.teachers (id, id_person,  salary  ) values (?, ?, ?)",
                UUID.randomUUID(), teacher.getId(), teacher.getSalary());
         System.out.println("Teacher Added!!");
        
    }

    @Override
    public void editWeekSchedule(ScheduleItem scheduleItem, String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteWeekSchedule(String id) {
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
