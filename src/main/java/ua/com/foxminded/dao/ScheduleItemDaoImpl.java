package ua.com.foxminded.dao;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.interfaces.ScheduleItemDao;
import ua.com.foxminded.dao.mappers.ScheduleMapper;
import ua.com.foxminded.exception.DbObjectNotInsertedException;
import ua.com.foxminded.exception.NoSuchScheduleItemException;

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
        String scheduleItemId = scheduleItem.getId();
        logger.info(format("Add scheduleItem with UUID  = %s", scheduleItemId));
        try {
            int countInserted = jdbcTemplate.update(
                    "INSERT INTO uni.schedule_items (id, id_group, id_subject, id_room, id_time_slot, " +
                            " id_teacher, day_of_week) values (?, ?, ?, ?, ?, ?, ? )",
                    scheduleItem.getId(), scheduleItem.getGroup().getId(), scheduleItem.getSubject().getId(),
                    scheduleItem.getRoom().getId(), scheduleItem.getTimeSlot().getId(),
                    scheduleItem.getTeacher().getIdTeacher(), scheduleItem.getDayOfWeek());

            if (countInserted == 0) {
                throw new DbObjectNotInsertedException(scheduleItem);
            }
            logger.info(format("ScheduleItem with UUID   = %s added successfully.", scheduleItemId));
        } catch (DataAccessException e) {
            logger.debug(format("Scheduleitem with UUID = %s was not added.  Reason: %s", scheduleItemId,
                    e.getMessage()));
        } catch (DbObjectNotInsertedException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void editScheduleItem(ScheduleItem scheduleItem) {
        String scheduleItemId = scheduleItem.getId();
        logger.info(format("Edit schedule with UUID  = %s", scheduleItemId));
        try {
            int countUpdated = jdbcTemplate.update(
                    "UPDATE  uni.schedule_items sch  SET sch.id_group= ?, sch.id_subject= ?, sch.id_room= ?,  " +
                            " sch.id_time_slot= ?, sch.id_teacher= ?, sch.day_of_week= ? WHERE sch.id = ? ",
                    scheduleItem.getGroup().getId(), scheduleItem.getSubject().getId(), scheduleItem.getRoom().getId(),
                    scheduleItem.getTimeSlot().getId(), scheduleItem.getTeacher().getIdTeacher(),
                    scheduleItem.getDayOfWeek(), scheduleItem.getId());

            if (countUpdated == 0) {
                throw new NoSuchScheduleItemException(scheduleItemId);
            }
            logger.info(format("ScheduleItem with UUID = %s updated sucessfully.", scheduleItemId));
        } catch (DataAccessException e) {
            logger.debug(format("Scheduleitem with UUID = %s was not updated.  Reason: %s", scheduleItemId,
                    e.getMessage()));
        } catch (NoSuchScheduleItemException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void deleteScheduleItem(String scheduleItemId) {
        logger.info(format("Delete scheduleItemId with UUID = %s", scheduleItemId));
        try {
            int countDeleted = jdbcTemplate.update("DELETE  from uni.schedule_items s where s.id = ? ",
                    scheduleItemId);
            if (countDeleted == 0) {
                throw new NoSuchScheduleItemException(scheduleItemId);
            }
            logger.info(format("ScheduleItem with UUID = %s deleted sucessfully.", scheduleItemId));
        } catch (DataAccessException e) {
            logger.debug(format("Scheduleitem with UUID= %s was not delete.  Reason: %s", scheduleItemId,
                    e.getMessage()));
        } catch (NoSuchScheduleItemException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public List<ScheduleItem> findWeekScheduleTeacher(String teacherId) {
        logger.info(format("Find scheduleItem for teacher with id  = %s ", teacherId));
        List<ScheduleItem> scheduleItems = new ArrayList<>();
        try {
            scheduleItems = jdbcTemplate.query("Select * " +
                    " from uni.persons p,  uni.teachers t,  uni.groups g,  uni.subjects su, " +
                    " uni.rooms r, uni.time_slots ts, uni.schedule_items si " +
                    " where si.id_teacher = t.id_teacher and t.id_person = p.id_person " +
                    " and si.id_group = g.id    and si.id_subject = su.id " +
                    " and si.id_room = r.id  and si.id_time_slot = ts.id  and t.id_teacher = ?  " +
                    " order by ts.serial_number ",
                    scheduleMapper, teacherId);
            if (scheduleItems.size() == 0) {
                throw new NoSuchScheduleItemException(teacherId);
            }
            logger.info(format("ScheduleItem for teacher with UUID = %s found sucessfully.", teacherId));
        } catch (DataAccessException e) {
            logger.debug(format("ScheduleItem for teacher with UUID = %s was not found.  Reason: %s", teacherId,
                    e.getMessage()));
        } catch (NoSuchScheduleItemException e) {
            logger.debug(e.getMessage());
        }
        return scheduleItems;
    }

    @Override
    public List<ScheduleItem> findWeekScheduleStudent(String studentId) {
        logger.info(format("Find scheduleItem for student with id  = %s ", studentId));
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
                    scheduleMapper, studentId);
            if (scheduleItems.size() == 0) {
                throw new NoSuchScheduleItemException(studentId);
            }
            logger.info(format("ScheduleItem for teacher with UUID = %s found sucessfully.", studentId));
        } catch (DataAccessException e) {
            logger.debug(format("ScheduleItem for student with UUID = %s was not found.  Reason: %s", studentId,
                    e.getMessage()));
        } catch (NoSuchScheduleItemException e) {
            logger.debug(e.getMessage());
        }
        return scheduleItems;
    }
}
