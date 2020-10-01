package ua.com.foxminded.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.interfaces.StudentDao;
import ua.com.foxminded.dao.mappers.StudentMapper;
import ua.com.foxminded.exception.NoStudentsFoundException;
import ua.com.foxminded.exception.NoSuchStudentException;

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
        logger.info("Get student with UUID = " + student.getIdStudent() + " , "
                + student.getLastName());

        try {
            jdbcTemplate.execute("INSERT INTO uni.persons (id_person, first_name, last_name) " +
                    " values (?, ?, ?)",
                    new PreparedStatementCallback<Boolean>() {
                        @Override
                        public Boolean doInPreparedStatement(PreparedStatement ps)
                                throws SQLException, DataAccessException {
                            ps.setString(1, student.getIdPerson());
                            ps.setString(2, student.getFirstName());
                            ps.setString(3, student.getLastName());

                            return ps.execute();
                        }
                    });

            jdbcTemplate.execute(
                    "INSERT INTO uni.students (id_student, id_person, study_status, " +
                            "start_of_study, citizenship , grants  ) values (?, ?, ?, ?, ?, ?)",
                    new PreparedStatementCallback<Boolean>() {
                        @Override
                        public Boolean doInPreparedStatement(PreparedStatement ps)
                                throws SQLException, DataAccessException {
                            ps.setString(1, student.getIdStudent());
                            ps.setString(2, student.getIdPerson());
                            ps.setString(3, student.getStudyStatus());
                            ps.setDate(4, java.sql.Date.valueOf(student.getStartOfStudy()));
                            ps.setString(5, student.getCitizenship());
                            ps.setBigDecimal(6, student.getGrant());
                            return ps.execute();
                        }
                    });
            logger.info("Student with UUID = " + student.getIdStudent() + " added sucessfully.");
        } catch (DataAccessException e) {
            logger.debug("Student  with UUID = " + student.getIdStudent() + " didn't add!!  Reason: "
                    + e.getMessage());
        }
    }

    @Override
    public void editStudent(Student student) {
        logger.info("Edit student with UUID = " + student.getIdStudent());
        try {
            jdbcTemplate.execute("UPDATE uni.students s SET citizenship = ?, study_status = ?, " +
                    " grants = ?, start_of_study = ?  WHERE s.id_student = ? ",
                    new PreparedStatementCallback<Boolean>() {
                        @Override
                        public Boolean doInPreparedStatement(PreparedStatement ps)
                                throws SQLException, DataAccessException {
                            ps.setString(1, student.getCitizenship());
                            ps.setString(2, student.getStudyStatus());
                            ps.setBigDecimal(3, student.getGrant());
                            ps.setDate(4, java.sql.Date.valueOf(student.getStartOfStudy()));
                            ps.setString(5, student.getIdStudent());
                            return ps.execute();
                        }
                    });
            logger.info("Student with UUID = " + student.getIdStudent() + " updated sucessfully.");
        } catch (DataAccessException e) {
            logger.debug("Student with UUID = " + student.getIdStudent() + " didn't update!!  Reason: "
                    + e.getMessage());
        }
    }

    @Override
    public void deleteStudent(String id) {
        logger.info("Delete student with UUID = " + id);
        try {
            jdbcTemplate.execute("DELETE from uni.students s WHERE s.id_student = ? ",
                    new PreparedStatementCallback<Boolean>() {
                        @Override
                        public Boolean doInPreparedStatement(PreparedStatement ps)
                                throws SQLException, DataAccessException {
                            ps.setString(1, id);
                            return ps.execute();
                        }
                    });

            logger.info("Student with UUID = " + id + " deleted sucessfully.");
        } catch (DataAccessException e) {
            logger.debug("Student with UUID = " + id + "  didn't delete!!  Reason: " + e.getMessage());
        }
    }

    @Override
    public List<Student> findStudent(String id) {
        logger.info("Find student with last name  = " + id);
        List<Student> students = new ArrayList<>();
        try {
            students = jdbcTemplate.query("Select * from uni.students s, uni.persons p " +
                    " where s.id_person = p.id_person and s.id_student = ? ",
                    studentMapper, id);

            if (students.size() > 0) {
                logger.info("Student with last name  = " + id + " found sucessfully.");
            } else {
                throw new NoSuchStudentException(id);
            }
        } catch ( NoSuchStudentException e) {
            logger.debug(e.getMessage());
        }
        catch (DataAccessException  e) {
            logger.debug("No database connection established or no data access. " + 
                     ". Reason: " + e.getMessage());
        }
        return students;
    }

    @Override
    public List<Student> findAllStudent() {
        logger.info("Find all students. ");
        List<Student> students = new ArrayList<>();
        try {
            students = jdbcTemplate.query("Select * from uni.students s, uni.persons p " +
                    " where s.id_person = p.id_person", studentMapper);
            if (students.size() > 0) {
                logger.info("Students found sucessfully.");
            } else {
                throw new NoStudentsFoundException();
            }
        } catch (NoStudentsFoundException e) {
            logger.debug(e.getMessage());
        }catch (DataAccessException  e) {
            logger.debug("No database connection established or no data access. Reason: " + e.getMessage());
        }
        
        return students;
    }
}
