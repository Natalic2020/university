package ua.com.foxminded.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
@Qualifier("studentDaoHim")
public class StudentDaoHimImpl implements StudentDao {

    Logger logger = LoggerFactory.getLogger("SampleLogger");

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void addStudent(Student student) {

        String personId = student.getIdPerson();
        logger.info(format("Add person with UUID = %s", personId));
        
     
//        entityManager.getTransaction().begin();
        entityManager.persist(student);
//        entityManager.getTransaction().commit();
        
        
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.save(student);
//        tx1.commit();
//        session.close();
    }

    @Override
    public void editStudent(Student student) {

        String personId = student.getIdPerson();
        logger.info(format("Edit person with UUID = %s", personId));

        entityManager.merge(student);
        
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.update(student);
//        tx1.commit();
//        session.close();
    }

    @Override
    public void deleteStudent(Student student) {
        String personId = student.getIdPerson();
        logger.info(format("Delete student with UUID = %s", personId));

        if (entityManager.contains(student)) {
            entityManager.remove(student);
        } else {
            entityManager.remove(entityManager.merge(student));
        }
        
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.delete(session.get(Student.class,personId));
//        tx1.commit();
//        session.close();
    }

    @Override
    public Student findStudent(String personId) {
        logger.info(format("Find student with id  = %s", personId));
        Student response = (Student) entityManager.find(Student.class, personId);
        return response;
//        Student student = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Student.class, personId);
//        return student;
    }

    @Override
    public List<Student> findAllStudent() {
        // TODO Auto-generated method stub
        return null;
    }

//    @Override
//    public List<Student> findAllStudent() {
//        logger.info("Find all students. ");
//        List<Student> students = HibernateSessionFactoryUtil.getSessionFactory()
//                                                            .openSession()
//                                                            .createQuery("From Student")
//                                                            .list();
//        
//        
//        return students;
//    }
}
