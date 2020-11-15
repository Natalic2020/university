package ua.com.foxminded.dao;

import static java.lang.String.format;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.interfaces.ScheduleItemDao;

@Repository
@Qualifier("scheduleItemDaoHim")
public class ScheduleItemDaoHimImpl implements ScheduleItemDao {

    Logger logger = LoggerFactory.getLogger("SampleLogger");
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public void addScheduleItem(ScheduleItem scheduleItem) {
        String scheduleItemId = scheduleItem.getId();
        logger.info(format("Add scheduleItem with UUID  = %s", scheduleItemId));
        entityManager.persist(scheduleItem);
    }
    
    @Override
    @Transactional
    public void editScheduleItem(ScheduleItem scheduleItem) {
        String scheduleItemId = scheduleItem.getId();
        logger.info(format("Edit schedule with UUID  = %s", scheduleItemId));
        entityManager.merge(scheduleItem);
    }
     
    @Override
    @Transactional
    public void deleteScheduleItem(String scheduleItemId) {
        logger.info(format("Delete scheduleItemId with UUID = %s", scheduleItemId));
        if (entityManager.contains(scheduleItemId)) {
            entityManager.remove(scheduleItemId);
        } else {
            entityManager.remove(entityManager.merge(scheduleItemId));
        }  
    }
    
    @Override
    @Transactional
    public List<ScheduleItem> findWeekScheduleTeacher(String personId) {
        logger.info(format("Find scheduleItem for teacher with id  = %s ", personId));
        return   entityManager.createQuery("From ScheduleItem where id_person = ?1")
                .setParameter(1, personId)
                .getResultList();
    }

    @Override
    @Transactional
    public List<ScheduleItem> findWeekScheduleStudent(String idGroup) {
        return    entityManager.createQuery("From ScheduleItem where id_group = :idGroup")
                .setParameter("idGroup", idGroup)
                .getResultList();
    }
}
