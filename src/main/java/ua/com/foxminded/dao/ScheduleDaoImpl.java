package ua.com.foxminded.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Schedule;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.entity.Student;
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
        jdbcTemplate.execute("INSERT INTO uni.rooms (id, name_room ) values (?, ?)", new PreparedStatementCallback<Boolean>(){
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps)  
                    throws SQLException, DataAccessException {                 
                ps.setString(1,schedule.getScheduleItemTeacher().getScheduleItem().getRoom().getId()); 
                ps.setString(2,schedule.getScheduleItemTeacher().getScheduleItem().getRoom().getName());
                return ps.execute();  
            }  
            });  
//                schedule.getScheduleItemTeacher().getScheduleItem().getRoom().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getRoom().getName());
        
        jdbcTemplate.execute("INSERT INTO uni.subjects (id, name_subject ) values (?, ?)", new PreparedStatementCallback<Boolean>(){
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps)  
                    throws SQLException, DataAccessException {                 
                ps.setString(1,schedule.getScheduleItemTeacher().getScheduleItem().getSubject().getId());  
                ps.setString(2,schedule.getScheduleItemTeacher().getScheduleItem().getSubject().getName());
                return ps.execute();  
            }  
            });  
//                schedule.getScheduleItemTeacher().getScheduleItem().getSubject().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getSubject().getName());
        
        jdbcTemplate.execute("INSERT INTO uni.groups (id, name_group ) values (?, ?)", new PreparedStatementCallback<Boolean>(){
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps)  
                    throws SQLException, DataAccessException {                 
                ps.setString(1,schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getId());  
                ps.setString(2,schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getName()); 
                return ps.execute();  
            }  
            });  
//                schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getName());
        
        jdbcTemplate.execute("INSERT INTO uni.time_slots (id, serial_number, start_time, finish_time ) values (?, ?, ?, ?) " , new PreparedStatementCallback<Boolean>(){
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps)  
                    throws SQLException, DataAccessException {                 
                ps.setString(1,schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getId());  
                ps.setInt(2,schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getSerialNumber());
                ps.setTime(3, java.sql.Time.valueOf(schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getStartTime()));
                ps.setTime(4, java.sql.Time.valueOf(schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getFinishTime()));
                return ps.execute();  
            }  
            });  
//                schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getSerialNumber(),
//                schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getStartTime(), schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getFinishTime());
        
        String idGroup = schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getId();
        
        schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getStudents().forEach(student -> {
            jdbcTemplate.execute("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)", new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,student.getPerson().getId());
                    ps.setString(2,student.getPerson().getFirstName());
                    ps.setString(3,student.getPerson().getLastName());
                    return ps.execute();  
                }  
                });  
//                    student.getPerson().getId(),
//                    student.getPerson().getFirstName(), student.getPerson().getLastName());
            
            jdbcTemplate.update(
                    "INSERT INTO uni.students (id, id_person, study_status, start_of_study, citizenship , grants, id_group  ) values (?, ?, ?, ?, ?, ?, ?)",                  
                    student.getId(), student.getPerson().getId(), student.getStudyStatus(),
                    student.getStartOfStudy(), student.getCitizenship(), student.getGrant(), idGroup);
        });
        
                
        jdbcTemplate.execute("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)", new PreparedStatementCallback<Boolean>(){
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps)  
                    throws SQLException, DataAccessException {                 
                ps.setString(1,schedule.getScheduleItemTeacher().getTeacher().getPerson().getId());
                ps.setString(2,schedule.getScheduleItemTeacher().getTeacher().getPerson().getFirstName());
                ps.setString(3,schedule.getScheduleItemTeacher().getTeacher().getPerson().getLastName());
                return ps.execute();  
            }  
            });  
//                schedule.getScheduleItemTeacher().getTeacher().getPerson().getId(), schedule.getScheduleItemTeacher().getTeacher().getPerson().getFirstName(),schedule.getScheduleItemTeacher().getTeacher().getPerson().getLastName());
        
        jdbcTemplate.execute("INSERT INTO uni.teachers (id, id_person ) values (?, ?)", new PreparedStatementCallback<Boolean>(){
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps)  
                    throws SQLException, DataAccessException {                 
                ps.setString(1,schedule.getScheduleItemTeacher().getTeacher().getId());  
                ps.setString(2,schedule.getScheduleItemTeacher().getTeacher().getPerson().getId());
                return ps.execute();  
            }  
            });  
//                schedule.getScheduleItemTeacher().getTeacher().getId(), schedule.getScheduleItemTeacher().getTeacher().getPerson().getId());
        
        jdbcTemplate.execute("INSERT INTO uni.schedule_items (id, id_group, id_subject, id_room, id_time_slot, day_of_week) values (?, ?, ?, ?, ?, ? )",new PreparedStatementCallback<Boolean>(){
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps)  
                    throws SQLException, DataAccessException {                 
                ps.setString(1,schedule.getScheduleItemTeacher().getScheduleItem().getId());  
                ps.setString(2,schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getId());
                ps.setString(3,schedule.getScheduleItemTeacher().getScheduleItem().getSubject().getId());
                ps.setString(4,schedule.getScheduleItemTeacher().getScheduleItem().getRoom().getId());
                ps.setString(5,schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getId());
                ps.setString(6,schedule.getScheduleItemTeacher().getScheduleItem().getDayOfWeek());
                return ps.execute();  
            }  
            });  
//                schedule.getScheduleItemTeacher().getScheduleItem().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getGroup().getId(),
//                schedule.getScheduleItemTeacher().getScheduleItem().getSubject().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getRoom().getId(),
//                schedule.getScheduleItemTeacher().getScheduleItem().getTimeSlot().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getDayOfWeek());
        
        jdbcTemplate.execute("INSERT INTO uni.schedule_items_teachers (id, id_schedule_item, id_teacher ) values (?, ?, ?)", new PreparedStatementCallback<Boolean>(){
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps)  
                    throws SQLException, DataAccessException {                 
                ps.setString(1,schedule.getScheduleItemTeacher().getId());  
                ps.setString(2,schedule.getScheduleItemTeacher().getScheduleItem().getId());
                ps.setString(3,schedule.getScheduleItemTeacher().getTeacher().getId());
                return ps.execute();  
            }  
            });  
//                schedule.getScheduleItemTeacher().getId(), schedule.getScheduleItemTeacher().getScheduleItem().getId(), schedule.getScheduleItemTeacher().getTeacher().getId());
        
        jdbcTemplate.update("INSERT INTO uni.periods (id, start_date, finish_date ) values (?, ?, ?)", 
                schedule.getPeriod().getId(), schedule.getPeriod().getStartDate(), schedule.getPeriod().getFinishDate());
        
        jdbcTemplate.update("INSERT INTO uni.schedule (id_period, id_schedule_items_teacher ) values (?, ?)",
                schedule.getPeriod().getId(), schedule.getScheduleItemTeacher().getId());
         System.out.println("Schedule Added!!");      
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
