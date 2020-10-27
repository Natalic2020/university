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

import ua.com.foxminded.dao.entity.Group;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.interfaces.ScheduleItemDao;
import ua.com.foxminded.dao.mappers.ScheduleMapper;
import ua.com.foxminded.exception.DbObjectNotInsertedException;
import ua.com.foxminded.exception.NoSuchScheduleItemException;
import ua.com.foxminded.util.HibernateSessionFactoryUtil;

@Repository
//@Qualifier("scheduleItemDaoHim")
public class ScheduleItemDaoHimImpl implements ScheduleItemDao {

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
                    scheduleItem.getRoom().getId(), scheduleItem.getTimeSlot().getId()
//                    ,
//                    scheduleItem.getTeacher().getIdTeacher(), scheduleItem.getDayOfWeek()
                    );

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
//                    scheduleItem.getTimeSlot().getId(), scheduleItem.getTeacher().getIdTeacher(),
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
    public List<ScheduleItem> findWeekScheduleTeacher(String personId) {
        logger.info(format("Find scheduleItem for teacher with id  = %s ", personId));
        List<ScheduleItem> scheduleItems =  HibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("From ScheduleItem where id_person = :personId")
                .setParameter("personId", personId)
                .list();
        return scheduleItems;
    }

    @Override
    public List<ScheduleItem> findWeekScheduleStudent(String personId) {
        logger.info(format("Find scheduleItem for student with id  = %s ", personId));
        Group group = new StudentDaoHimImpl().findStudent(personId).getGroup();
        List<ScheduleItem> scheduleItems = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("From ScheduleItem where id_group = :idGroup")
//                .setProperties(group)
                .setParameter("idGroup", group.getId())
                .list();
        return scheduleItems;
    }
}
