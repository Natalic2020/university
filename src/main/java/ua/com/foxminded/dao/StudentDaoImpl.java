package ua.com.foxminded.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.interfaces.StudentDao;
import ua.com.foxminded.dao.mappers.StudentMapper;
import ua.com.foxminded.exception.DbObjectNotInsertedException;
import ua.com.foxminded.exception.NoStudentsFoundException;
import ua.com.foxminded.exception.NoSuchStudentException;

import static java.lang.String.format;

@Repository
@Qualifier("studentDao")
public class StudentDaoImpl implements StudentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StudentMapper studentMapper;

    Logger logger = LoggerFactory.getLogger("SampleLogger");

    @Override
    public void addStudent(Student student) {
        String studentId = student.getIdStudent();
        String personId = student.getIdPerson();
        logger.info(format("Add person with UUID = %s", personId));

        try {
            int countPersonInserted = jdbcTemplate.update(
                    "INSERT INTO uni.persons (id_person, first_name, last_name) " +
                            " values (?, ?, ?)",
               student.getIdPerson(), student.getFirstName(), student.getLastName());
                           
            if (countPersonInserted == 0) {
                throw new DbObjectNotInsertedException(student);
            }
            logger.info(format("Person with UUID = %s added successfully.", personId));
            
            logger.info(format("Add student with UUID = %s", studentId));
            int countStudentInserted = jdbcTemplate.update(
                    "INSERT INTO uni.students (id_student, id_person, study_status, " +
                            "start_of_study, citizenship , grants  ) values (?, ?, ?, ?, ?, ?)",
                           student.getIdStudent(), student.getIdPerson(), student.getStudyStatus(),
                           Optional.ofNullable(student.getStartOfStudy()) 
                           .map(ss -> Date.valueOf(ss)).orElse(null),
                           student.getCitizenship(), student.getGrant());
            if ( countStudentInserted == 0) {
                throw new DbObjectNotInsertedException(student);
            }
            logger.info(format("Student with UUID = %s added successfully.", studentId));
        } catch (DataAccessException e) {
            logger.debug(format("Student  with UUID = %s was not added.  Reason: %s", studentId,
                    e.getMessage()));
        } catch (DbObjectNotInsertedException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void editStudent(Student student) {
        String studentId = student.getIdStudent();
        String personId = student.getIdPerson();
        logger.info(format("Edit person with UUID = %s", personId));
        try {
            int countPersonUpdated = jdbcTemplate.update(
                    "UPDATE uni.persons p SET  p.first_name= ?, p.last_name= ? " +
                            " WHERE p.id_person = ? ",
               student.getFirstName(), student.getLastName(), personId);  
            
            logger.info(format("Person with UUID = %s updated sucessfully.", personId));
            
            logger.info(format("Edit student with UUID = %s", studentId));
            int countStudentUpdated = jdbcTemplate.update("UPDATE uni.students s SET citizenship = ?, study_status = ?, " +
                    " grants = ?, start_of_study = ?  WHERE s.id_student = ? ",
                    student.getCitizenship(), student.getStudyStatus(), student.getGrant(), 
                    Optional.ofNullable(student.getStartOfStudy()) 
                    .map(ss -> Date.valueOf(ss)).orElse(null),
                    studentId);
                          
            if (countStudentUpdated == 0) {
                throw new NoSuchStudentException(studentId);
            }
            logger.info(format("Student with UUID = %s updated sucessfully.", studentId));
        } catch (DataAccessException e) {
            logger.debug(format("Student with UUID = %s was not updated. m Reason: %s", studentId,
                    e.getMessage()));
        } catch (NoSuchStudentException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void deleteStudent(String studentId) {
        logger.info(format("Delete student with UUID = %s", studentId));
        try {
            int countDeleted = jdbcTemplate.update("DELETE from uni.students s WHERE s.id_student = ? ",
                     studentId);
                         
            if (countDeleted == 0) {
                throw new NoSuchStudentException(studentId);
            }
            logger.info(format("Student with UUID = %s deleted sucessfully.", studentId));
        } catch (DataAccessException e) {
            logger.debug(format("Student with UUID= %s was not delete.  Reason: %s", studentId,
                    e.getMessage()));
        } catch (NoSuchStudentException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public Student findStudent(String studentId) {
        logger.info(format("Find student with id  = %s", studentId));
        List<Student> students = new ArrayList<>();
        try {
            students = jdbcTemplate.query("Select * from uni.students s, uni.persons p " +
                    " where s.id_person = p.id_person and s.id_student = ? ",
                    studentMapper, studentId);

            if (students.size() == 0) {
                throw new NoSuchStudentException(studentId);     
            }  
            logger.info(format("Student with UUID = %s found sucessfully.", studentId));
        } catch (DataAccessException e) {
            logger.debug(format("Student with UUID = %s was not found.  Reason: %s", studentId,
                    e.getMessage()));
        } catch (NoSuchStudentException e) {
            logger.debug(e.getMessage());
        }   
        return students.get(0);
    }

    @Override
    public List<Student> findAllStudent() {
        logger.info("Find all students. ");
        List<Student> students = new ArrayList<>();
        try {
            students = jdbcTemplate.query("Select * from uni.students s, uni.persons p " +
                    " where s.id_person = p.id_person", studentMapper);
            if (students.size() == 0) {
                throw new NoStudentsFoundException();    
            }
            logger.info("Students found sucessfully.");
        } catch (DataAccessException e) {
            logger.debug("No database connection established or no data access. Reason: %s", e.getMessage());
        } catch (NoStudentsFoundException e) {
            logger.debug(e.getMessage());
        }
        return students;
    }
}
