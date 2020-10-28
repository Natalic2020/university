package ua.com.foxminded.dao;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.Transaction;
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
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.interfaces.ScheduleItemDao;
import ua.com.foxminded.dao.mappers.ScheduleMapper;
import ua.com.foxminded.exception.DbObjectNotInsertedException;
import ua.com.foxminded.exception.NoSuchScheduleItemException;
import ua.com.foxminded.util.HibernateSessionFactoryUtil;

@Repository
@Qualifier("scheduleItemDaoHim")
public class ScheduleItemDaoHimImpl implements ScheduleItemDao {

    Logger logger = LoggerFactory.getLogger("SampleLogger");

    @Override
    public void addScheduleItem(ScheduleItem scheduleItem) {

        String scheduleItemId = scheduleItem.getId();
        logger.info(format("Add scheduleItem with UUID  = %s", scheduleItemId));
        
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(scheduleItem);
        tx1.commit();
        session.close();
    }
    
    @Override
    public void editScheduleItem(ScheduleItem scheduleItem) {
        String scheduleItemId = scheduleItem.getId();
        logger.info(format("Edit schedule with UUID  = %s", scheduleItemId));
        
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(scheduleItem);
        tx1.commit();
        session.close();
    }
     
    @Override
    public void deleteScheduleItem(String scheduleItemId) {
        logger.info(format("Delete scheduleItemId with UUID = %s", scheduleItemId));

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(session.get(ScheduleItem.class, scheduleItemId));
        tx1.commit();
        session.close();
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
                .setParameter("idGroup", group.getId())
                .list();
        return scheduleItems;
    }
}
