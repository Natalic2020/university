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

import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.interfaces.TeacherDao;

@Repository
@Qualifier("teacherDaoHim")
public class TeacherDaoHimImpl implements TeacherDao {

    Logger logger = LoggerFactory.getLogger("SampleLogger");

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public void addTeacher(Teacher teacher)  {
        String personId = teacher.getIdPerson();
        logger.info(format("Add person with UUID = %s", personId));
        entityManager.persist(teacher);
    }
    
    @Override
    @Transactional
    public void editTeacher(Teacher teacher) {
        String personId = teacher.getIdPerson();
        logger.info(format("Edit person with UUID = %s", personId));
        entityManager.merge(teacher);
    }
    
    @Override
    @Transactional
    public void deleteTeacher(Teacher teacher) {
        String personId = teacher.getIdPerson();
        logger.info(format("Delete teacher with UUID = %s", personId));
        if (entityManager.contains(teacher)) {
            entityManager.remove(teacher);
        } else {
            entityManager.remove(entityManager.merge(teacher));
        }   
    }

    @Override
    @Transactional
    public Teacher findTeacher(String personId) {
        logger.info(format("Find teacher with id  = %s", personId));
        return  (Teacher) entityManager.find(Teacher.class, personId);
    }

    @Override
    @Transactional
    public List<Teacher> findAllTeacher() {
        logger.info("Find all teachers. ");
        return entityManager.createQuery("From Teacher").getResultList();
    }   
}
