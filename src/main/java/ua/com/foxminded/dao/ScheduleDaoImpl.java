package ua.com.foxminded.dao;


import java.time.LocalDate;
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
        addScheduleItem(schedule.get(0));  
    }    
   
    public void addScheduleItem(Schedule schedule) {
        try {
            jdbcTemplate.update("INSERT INTO uni.rooms (id, name_room ) values (?, ?)",  
                    schedule.getScheduleItemTeacher().getScheduleItem().getRoom().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getRoom().getName());
            
            jdbcTemplate.update("INSERT INTO uni.subjects (id, name_subject ) values (?, ?)",
                    schedule.getScheduleItemTeacher().getScheduleItem().getSubject().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getSubject().getName());
            
            jdbcTemplate.update("INSERT INTO uni.groups (id, name_group ) values (?, ?)", 
                    schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getName());
            
            jdbcTemplate.update("INSERT INTO uni.time_slots (id, serial_number, start_time, finish_time ) values (?, ?, ?, ?) " , 
                    schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getSerialNumber(),
                    schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getStartTime(), schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getFinishTime());
            
            String idGroup = schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getId();
            
            schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getStudents().forEach(student -> {
                jdbcTemplate.update("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)", 
                        student.getPerson().getId(),
                        student.getPerson().getFirstName(), student.getPerson().getLastName());
                
                jdbcTemplate.update(
                        "INSERT INTO uni.students (id, id_person, study_status, start_of_study, citizenship , grants, id_group  ) values (?, ?, ?, ?, ?, ?, ?)",                  
                        student.getId(), student.getPerson().getId(), student.getStudyStatus(),
                        student.getStartOfStudy(), student.getCitizenship(), student.getGrant(), idGroup);
            });
                    
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
        } catch (DataAccessException e) {
            e.printStackTrace();
        }      
    }
    
    @Override
    public List<Schedule> findScheduleTeacher(String lastName, LocalDate date) {
        List<Schedule> schedule = jdbcTemplate.query("Select * " + 
                "from uni.persons p, " + 
                " uni.teachers t," + 
                " uni.groups g," + 
                " uni.subjects su," + 
                " uni.rooms r," + 
                " uni.time_slots ts," + 
                " uni.schedule_items si," + 
                " uni.schedule_items_teachers sit," + 
                " uni.periods per," + 
                " uni.schedule sch" + 
                " where sch.id_period = per.id " + 
                " and sch.id_schedule_items_teacher = sit.id " + 
                " and sit.id_teacher = t.id " + 
                " and t.id_person = p.id " + 
                " and sit.id_schedule_item = si.id " + 
                " and si.id_group = g.id " + 
                " and si.id_subject = su.id " + 
                " and si.id_room = r.id " + 
                " and si.id_time_slot = ts.id " +
                " and p.last_name = ? " +
                " and "+ " '" + date.toString() + "' " +" between per.start_date and per.finish_date " +
                " and si.day_of_week = " + " '" + date.getDayOfWeek() + "' " +
                " order by ts.serial_number ",
                scheduleMapper, lastName);
        return schedule;
    }
    
    @Override
    public List<Schedule> findScheduleStudent(String lastName, LocalDate date) {
        List<Schedule> schedule = jdbcTemplate.query("Select * " + 
                "from uni.persons p, " + 
                " uni.teachers t," + 
                " uni.groups g," + 
                " uni.students st," +
                " uni.subjects su," + 
                " uni.rooms r," + 
                " uni.time_slots ts," + 
                " uni.schedule_items si," + 
                " uni.schedule_items_teachers sit," + 
                " uni.periods per," + 
                " uni.schedule sch" + 
                " where sch.id_period = per.id " + 
                " and sch.id_schedule_items_teacher = sit.id " + 
                " and sit.id_teacher = t.id " + 
                " and st.id_person = p.id " + 
                " and sit.id_schedule_item = si.id " + 
                " and si.id_group = g.id " + 
                " and g.id = st.id_group " +
                " and si.id_subject = su.id " + 
                " and si.id_room = r.id " + 
                " and si.id_time_slot = ts.id " +
                " and p.last_name = ? " +
                " and "+ " '" + date.toString() + "' " +" between per.start_date and per.finish_date " +
                " and si.day_of_week = " + " '" + date.getDayOfWeek() + "' " +
                " order by ts.serial_number ",
                scheduleMapper, lastName);
        return schedule;
    }
}
