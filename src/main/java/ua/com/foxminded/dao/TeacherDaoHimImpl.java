package ua.com.foxminded.dao;

import static java.lang.String.format;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.interfaces.TeacherDao;
import ua.com.foxminded.dao.mappers.TeacherMapper;
import ua.com.foxminded.util.HibernateSessionFactoryUtil;

@Repository
@Qualifier("teacherDaoHim")
public class TeacherDaoHimImpl implements TeacherDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TeacherMapper teacherMapper;

    Logger logger = LoggerFactory.getLogger("SampleLogger");

    @Override
    public void addTeacher(Teacher teacher)  {

        String personId = teacher.getIdPerson();
        logger.info(format("Add person with UUID = %s", personId));
        
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(teacher);
        tx1.commit();
        session.close();
    }
    
    @Override
    public void editTeacher(Teacher teacher) {
        String personId = teacher.getIdPerson();
        logger.info(format("Edit person with UUID = %s", personId));

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(teacher);
        tx1.commit();
        session.close();
    }
    
    @Override
    public void deleteTeacher(String personId) {
        logger.info(format("Delete teacher with UUID = %s", personId));

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(session.get(Teacher.class, personId));
        tx1.commit();
        session.close();
    }

    @Override
    public Teacher findTeacher(String personId) {
        logger.info(format("Find student with id  = %s", personId));
        Teacher teacher = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Teacher.class, personId);
        return teacher;
    }

    @Override
    public List<Teacher> findAllTeacher() {
        logger.info("Find all teachers. ");
        List<Teacher> teachers = HibernateSessionFactoryUtil.getSessionFactory()
                                                            .openSession()
                                                            .createQuery("From Teacher")
                                                            .list();
        return teachers;
    }   
}
