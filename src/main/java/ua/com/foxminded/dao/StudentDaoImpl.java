package ua.com.foxminded.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            Boolean isPersonInserted = jdbcTemplate.execute(
                    "INSERT INTO uni.persons (id_person, first_name, last_name) " +
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
//            if (Objects.nonNull(isPersonInserted) && !isPersonInserted) {
//                throw new DbObjectNotInsertedException(student);
//            }
            logger.info(format("Person with UUID = %s added successfully.", personId));
            logger.info(format("Add student with UUID = %s", studentId));
            Boolean isStudentInserted = jdbcTemplate.execute(
                    "INSERT INTO uni.students (id_student, id_person, study_status, " +
                            "start_of_study, citizenship , grants  ) values (?, ?, ?, ?, ?, ?)",
                            (PreparedStatementCallback<Boolean>) ps -> {
                            ps.setString(1, student.getIdStudent());
                            ps.setString(2, student.getIdPerson());
                            ps.setString(3, student.getStudyStatus());
                            ps.setDate(4, Date.valueOf(student.getStartOfStudy()));
                            ps.setString(5, student.getCitizenship());
                            ps.setBigDecimal(6, student.getGrant());
                            return ps.execute();
                    });
            if (Objects.nonNull(isStudentInserted) && !isStudentInserted) {
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
        logger.info(format("Edit student with UUID = %s", studentId));
        try {
            Boolean isUpdated = jdbcTemplate.execute("UPDATE uni.students s SET citizenship = ?, study_status = ?, " +
                    " grants = ?, start_of_study = ?  WHERE s.id_student = ? ",
                    (PreparedStatementCallback<Boolean>) ps ->
                        {
                            ps.setString(1, student.getCitizenship());
                            ps.setString(2, student.getStudyStatus());
                            ps.setBigDecimal(3, student.getGrant());
                            ps.setDate(4, Date.valueOf(student.getStartOfStudy()));
                            ps.setString(5, studentId);
                            return ps.execute();
                        });
//            if (Objects.nonNull(isUpdated) && !isUpdated) {
//                throw new NoSuchStudentException(studentId);
//            }
            logger.info(format("Student with UUID = %s updated sucessfully.", studentId));
        } catch (DataAccessException e) {
            logger.debug(format("Student with UUID = %s was not updated.  Reason: %s", studentId,
                    e.getMessage()));
        } catch (NoSuchStudentException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void deleteStudent(String studentId) {
        logger.info(format("Delete student with UUID = %s", studentId));
        try {
            Boolean isDeleted = jdbcTemplate.execute("DELETE from uni.students s WHERE s.id_student = ? ",
                    (PreparedStatementCallback<Boolean>) ps ->
                        {
                            ps.setString(1, studentId);
                            return ps.execute();
                        });
//            if (Objects.nonNull(isDeleted) && !isDeleted) {
//                throw new NoSuchStudentException(studentId);
//            }
            logger.info(format("Student with UUID = %s deleted sucessfully.", studentId));
        } catch (DataAccessException e) {
            logger.debug(format("Student with UUID= %s was not delete.  Reason: %s", studentId,
                    e.getMessage()));
        } catch (NoSuchStudentException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public List<Student> findStudent(String studentId) {
        logger.info(format("Find student with id  = %s", studentId));
        List<Student> students = new ArrayList<>();
        try {
            students = jdbcTemplate.query("Select * from uni.students s, uni.persons p " +
                    " where s.id_person = p.id_person and s.id_student = ? ",
                    studentMapper, studentId);

            if (students.size() > 0) {
                logger.info(format("Student with UUID = %s found sucessfully.", studentId));
            } else {
                throw new NoSuchStudentException(studentId);
            }
        } catch (DataAccessException e) {
            logger.debug(format("Student with UUID = %s was not found.  Reason: %s", studentId,
                    e.getMessage()));
        } catch (NoSuchStudentException e) {
            logger.debug(e.getMessage());
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
        } catch (DataAccessException e) {
            logger.debug("No database connection established or no data access. Reason: %s", e.getMessage());
        } catch (NoStudentsFoundException e) {
            logger.debug(e.getMessage());
        }

        return students;
    }
}
