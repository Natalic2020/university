package ua.com.foxminded.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.interfaces.StudentDao;

import static java.lang.String.format;

@Repository
@Qualifier("studentDaoHim")
public class StudentDaoHimImpl implements StudentDao {

    Logger logger = LoggerFactory.getLogger("SampleLogger");

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public void addStudent(Student student) {
        String personId = student.getIdPerson();
        logger.info(format("Add person with UUID = %s", personId));
        entityManager.persist(student);
    }

    @Override
    @Transactional
    public void editStudent(Student student) {
        String personId = student.getIdPerson();
        logger.info(format("Edit person with UUID = %s", personId));
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Student student) {
        String personId = student.getIdPerson();
        logger.info(format("Delete student with UUID = %s", personId));

        if (entityManager.contains(student)) {
            entityManager.remove(student);
        } else {
            entityManager.remove(entityManager.merge(student));
        }    
    }

    @Override
    @Transactional
    public Student findStudent(String personId) {
        logger.info(format("Find student with id  = %s", personId));
        return  (Student) entityManager.find(Student.class, personId);
        
    }

    @Override
    @Transactional
    public List<Student> findAllStudent() {
        logger.info("Find all students. "); 
        return entityManager.createQuery("From Student").getResultList();
    }
}
