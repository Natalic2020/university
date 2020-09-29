package ua.com.foxminded.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.interfaces.ScheduleItemDao;
import ua.com.foxminded.dao.mappers.ScheduleMapper;

@Repository
@Qualifier("scheduleItemDao")
public class ScheduleItemDaoImpl implements ScheduleItemDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ScheduleMapper scheduleMapper;
    
    Logger logger = LoggerFactory.getLogger("SampleLogger");

    @Override
    public void addScheduleItem(ScheduleItem scheduleItem) {
        logger.info("Add schedule with UUID  = " + scheduleItem.getId());
        try {
            jdbcTemplate.update(
                    "INSERT INTO uni.schedule_items (id, id_group, id_subject, id_room, id_time_slot, " + 
                            " id_teacher, day_of_week) values (?, ?, ?, ?, ?, ?, ? )",
                    scheduleItem.getId(), scheduleItem.getGroup().getId(),
                    scheduleItem.getSubject().getId(), scheduleItem.getRoom()
                                                                   .getId(),
                    scheduleItem.getTimeSlot().getId(), scheduleItem.getTeacher().getIdTeacher(),
                    scheduleItem.getDayOfWeek());

            logger.info("ScheduleItem Added!!");
        } catch (DataAccessException e) {
            logger.debug("Scheduleitem didn't add!!  Reason: " + e.getMessage());
        }
    }

    @Override
    public void editScheduleItem(ScheduleItem scheduleItem) {
        logger.info("Edit schedule with UUID  = " + scheduleItem.getId());
        try {
            jdbcTemplate.update(
                    "UPDATE  uni.schedule_items sch  SET sch.id_group= ?, sch.id_subject= ?, sch.id_room= ?,  " +
                            " sch.id_time_slot= ?, sch.id_teacher= ?, sch.day_of_week= ? WHERE sch.id = ? ",
                    scheduleItem.getGroup().getId(),
                    scheduleItem.getSubject().getId(), scheduleItem.getRoom().getId(),
                    scheduleItem.getTimeSlot().getId(), scheduleItem.getTeacher().getIdTeacher(),
                    scheduleItem.getDayOfWeek(), scheduleItem.getId());

            logger.info("ScheduleItem Updated!!");
        } catch (DataAccessException e) {
            logger.debug("Scheduleitem didn't update!!  Reason: " + e.getMessage());
        }

    }

    @Override
    public void deleteScheduleItem(String id) {
        logger.info("Delete schedule with UUID  = " + id);
        try {
            jdbcTemplate.update("DELETE  from uni.schedule_items s where s.id = ? ", id);
            logger.info("ScheduleItem Updated!!");
        } catch (DataAccessException e) {
            logger.debug("Scheduleitem didn't update!!  Reason: " + e.getMessage());
        }

    }

    @Override
    public List<ScheduleItem> findScheduleTeacher(String lastName) {
        logger.info("Find schedule for teacher with lastName  = " + lastName);
        List<ScheduleItem> scheduleItems = new ArrayList<>();
        try {
            scheduleItems = jdbcTemplate.query("Select * " +
                    " from uni.persons p,  uni.teachers t,  uni.groups g,  uni.subjects su, uni.rooms r, " +
                    " uni.time_slots ts, uni.schedule_items si " +
                    " where si.id_teacher = t.id_teacher and t.id_person = p.id_person  " + 
                    " and si.id_group = g.id    and si.id_subject = su.id "  +
                    " and si.id_room = r.id  and si.id_time_slot = ts.id  and p.last_name = ?  " +
                    " order by ts.serial_number ",
                    scheduleMapper, lastName);
        } catch (DataAccessException e) {
            logger.debug(
                    " I can't find schedule teacher . Last name " + lastName + ". Reason: " + e.getMessage());
        }
        return scheduleItems;
    }

    @Override
    public List<ScheduleItem> findScheduleStudent(String lastName) {
        logger.info("Find schedule for student with lastName = " + lastName);
        List<ScheduleItem> scheduleItems = new ArrayList<>();
        try {
            scheduleItems = jdbcTemplate.query("Select * " +
                    "from uni.persons p,  uni.teachers t,  uni.groups g, uni.students st, uni.subjects su, " + 
                    " uni.rooms r, uni.time_slots ts, uni.schedule_items si " +
                    " where  si.id_teacher = t.id_teacher  and st.id_person = p.id_person  " + 
                    " and si.id_group = g.id  and g.id = st.id_group  " +
                    " and si.id_subject = su.id  and si.id_room = r.id  " + 
                    " and si.id_time_slot = ts.id  and p.last_name = ? " +
                    " order by ts.serial_number ",
                    scheduleMapper, lastName);
        } catch (DataAccessException e) {
            logger.debug(
                    " I can't find schedule student . Last name " + lastName + ". Reason: " + e.getMessage());
        }
        return scheduleItems;
    }

    @Override
    public List<ScheduleItem> findWeekScheduleTeacher(UUID id) {
        logger.info("Find schedule for teacher with id  = " + id);
        List<ScheduleItem> scheduleItems = new ArrayList<>();
        try {
            scheduleItems = jdbcTemplate.query("Select * " +
                    " from uni.persons p,  uni.teachers t,  uni.groups g,  uni.subjects su, " + 
                    " uni.rooms r, uni.time_slots ts, uni.schedule_items si "  +
                    " where si.id_teacher = t.id_teacher and t.id_person = p.id_person " + 
                    " and si.id_group = g.id    and si.id_subject = su.id "   +
                    " and si.id_room = r.id  and si.id_time_slot = ts.id  and t.id_teacher = ?  " +
                    " order by ts.serial_number ",
                    scheduleMapper, id);
        } catch (DataAccessException e) {
            logger.debug(" I can't find schedule teacher . id " + id + ". Reason: " + e.getMessage());
        }
        return scheduleItems;
    }

    @Override
    public List<ScheduleItem> findWeekScheduleStudent(UUID id) {
        logger.info("Find schedule for student with id  = " + id);
        List<ScheduleItem> scheduleItems = new ArrayList<>();
        try {
            scheduleItems = jdbcTemplate.query("Select * " +
                    "from uni.persons p,  uni.teachers t,  uni.groups g, uni.students st, " +
                    " uni.subjects su, uni.rooms r, uni.time_slots ts, uni.schedule_items si " +
                    " where  si.id_teacher = t.id_teacher  and st.id_person = p.id_person  " + 
                    " and si.id_group = g.id  and g.id = st.id_group  " +
                    " and si.id_subject = su.id  and si.id_room = r.id  " +
                    " and si.id_time_slot = ts.id  and st.id_student = ? " +
                    " order by ts.serial_number ",
                    scheduleMapper, id);
        } catch (DataAccessException e) {
            logger.debug(" I can't find schedule student . id " + id + ". Reason: " + e.getMessage());
        }
        return scheduleItems;
    }
}
