package ua.com.foxminded.dao;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Schedule;
import ua.com.foxminded.dao.interfaces.ScheduleDao;
import ua.com.foxminded.dao.mappers.ScheduleMapper;

@Repository
@Qualifier("scheduleDao")
public class ScheduleDaoImpl implements ScheduleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    ScheduleMapper scheduleMapper;
    
    @Override
    public void addSchedule(List<Schedule> schedule) {   
        schedule.forEach(this::addScheduleItem); 
    }    
   
    public void addScheduleItem(Schedule schedule) {
        try {
          
            jdbcTemplate.update("INSERT INTO uni.schedule_items_teachers (id, id_schedule_item, id_teacher ) values (?, ?, ?)", 
                    schedule.getScheduleItemTeacher().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getId(), schedule.getScheduleItemTeacher().getTeacher().getId());
           
            
            jdbcTemplate.update("INSERT INTO uni.schedule (id_period, id_schedule_items_teacher ) values (?, ?)",
                    schedule.getPeriod().getId(), schedule.getScheduleItemTeacher().getId());
             System.out.println("Schedule Added!!");
        } catch (DataAccessException e) {
            System.out.println("Schedule didn't add!!  Reason: " + e.getMessage());
        }      
    }
    
    @Override
    public List<Schedule> findScheduleTeacher(String lastName, LocalDate date) {
        List<Schedule> schedule = new ArrayList<>();
        try {
            schedule = jdbcTemplate.query("Select * " + 
                    "from uni.persons p,  uni.teachers t, uni.groups g, uni.subjects su, uni.rooms r, uni.time_slots ts," + 
                    " uni.schedule_items si, uni.schedule_items_teachers sit, uni.periods per, uni.schedule sch" + 
                    " where sch.id_period = per.id  and sch.id_schedule_items_teacher = sit.id " + 
                    " and sit.id_teacher = t.id  and t.id_person = p.id  and sit.id_schedule_item = si.id " + 
                    " and si.id_group = g.id  and si.id_subject = su.id  and si.id_room = r.id  and si.id_time_slot = ts.id " +
                    " and p.last_name = ?  and "+ " '" + date.toString() + "' " +" between per.start_date and per.finish_date " +
                    " and si.day_of_week = " + " '" + date.getDayOfWeek() + "' " +
                    " order by ts.serial_number ",
                    scheduleMapper, lastName);
        } catch (DataAccessException e) {
            System.out.println(" I can't find schedule teacher . Last name " + lastName + " . Date " + date + ". Reason: " + e.getMessage());
        }
        return schedule;
    }
    
    @Override
    public List<Schedule> findScheduleStudent(String lastName, LocalDate date) {
        List<Schedule> schedule = new ArrayList<>();
        try {
            schedule = jdbcTemplate.query("Select * " + 
                    "from uni.persons p,  uni.teachers t,  uni.groups g, uni.students st, uni.subjects su, uni.rooms r, uni.time_slots ts," + 
                    " uni.schedule_items si, uni.schedule_items_teachers sit, uni.periods per, uni.schedule sch" + 
                    " where sch.id_period = per.id  and sch.id_schedule_items_teacher = sit.id  and sit.id_teacher = t.id " + 
                    " and st.id_person = p.id  and sit.id_schedule_item = si.id  and si.id_group = g.id  and g.id = st.id_group " +
                    " and si.id_subject = su.id  and si.id_room = r.id  and si.id_time_slot = ts.id  and p.last_name = ? " +
                    " and "+ " '" + date.toString() + "' " +" between per.start_date and per.finish_date " +
                    " and si.day_of_week = " + " '" + date.getDayOfWeek() + "' " +
                    " order by ts.serial_number ",
                    scheduleMapper, lastName);
        } catch (DataAccessException e) {
            System.out.println(" I can't find schedule student . Last name " + lastName + " . Date " + date + ". Reason: " + e.getMessage());
        }
        return schedule;
    }
}
