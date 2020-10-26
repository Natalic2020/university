package ua.com.foxminded.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.interfaces.StudentDao;
import ua.com.foxminded.dao.mappers.StudentMapper;
import ua.com.foxminded.util.HibernateSessionFactoryUtil;

import static java.lang.String.format;

@Repository
@Qualifier("studentDao")
public class StudentDaoHimImpl implements StudentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StudentMapper studentMapper;

    Logger logger = LoggerFactory.getLogger("SampleLogger");

    @Override
    public void addStudent(Student student) {

        String personId = student.getIdPerson();
        logger.info(format("Add person with UUID = %s", personId));
        
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(student);
        tx1.commit();
        session.close();
    }

    @Override
    public void editStudent(Student student) {

        String personId = student.getIdPerson();
        logger.info(format("Edit person with UUID = %s", personId));

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(student);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteStudent(String personId) {
        logger.info(format("Delete student with UUID = %s", personId));

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(session.get(Student.class,personId));
        tx1.commit();
        session.close();
    }

    @Override
    public Student findStudent(String personId) {
        logger.info(format("Find student with id  = %s", personId));
        Student student = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Student.class, personId);
        return student;
    }

    @Override
    public List<Student> findAllStudent() {
        logger.info("Find all students. ");
        List<Student> students = HibernateSessionFactoryUtil.getSessionFactory()
                                                            .openSession()
                                                            .createQuery("From Student")
                                                            .list();
        
        
        return students;
    }
}
