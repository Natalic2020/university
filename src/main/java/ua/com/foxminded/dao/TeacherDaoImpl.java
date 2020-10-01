package ua.com.foxminded.dao;

import static java.lang.String.format;

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

import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.interfaces.TeacherDao;
import ua.com.foxminded.dao.mappers.TeacherMapper;
import ua.com.foxminded.exception.DbObjectNotInsertedException;
import ua.com.foxminded.exception.NoSuchTeacherException;
import ua.com.foxminded.exception.NoTeachersFoundException;

import static java.lang.String.format;

@Repository
@Qualifier("teacherDao")
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TeacherMapper teacherMapper;

    Logger logger = LoggerFactory.getLogger("SampleLogger");

    @Override
    public void addTeacher(Teacher teacher) {
        String teacherId = teacher.getIdTeacher();
        logger.info(format("Add teacher with UUID = %s", teacherId));
        try {
            Boolean isPersonInserted = jdbcTemplate.execute(
                    "INSERT INTO uni.persons (id_person, first_name, last_name) " +
                            " values (?, ?, ?)",
                    (PreparedStatementCallback<Boolean>) ps ->
                        {
                            ps.setString(1, teacher.getIdPerson());
                            ps.setString(2, teacher.getFirstName());
                            ps.setString(3, teacher.getLastName());
                            return ps.execute();
                        });
//            if (Objects.nonNull(isPersonInserted) && !isPersonInserted) {
//                throw new DbObjectNotInsertedException(teacher);
//            }

            Boolean isTeacherInserted = jdbcTemplate.execute(
                    "INSERT INTO uni.teachers (id_teacher, id_person, degree, " +
                            " department, permanent, salary ) values (?, ?, ?, ?, ?, ?)",
                    (PreparedStatementCallback<Boolean>) ps ->
                        {
                            ps.setString(1, teacher.getIdTeacher());
                            ps.setString(2, teacher.getIdPerson());
                            ps.setString(3, teacher.getDegree());
                            ps.setString(4, teacher.getDepartment());
                            ps.setBoolean(5, teacher.isPermanent());
                            ps.setBigDecimal(6, teacher.getSalary());
                            return ps.execute();
                        });
//            if (Objects.nonNull(isTeacherInserted) && !isTeacherInserted) {
//                throw new DbObjectNotInsertedException(teacher);
//            }
            logger.info(format("Teacher with UUID = %s added successfully.", teacherId));
        } catch (DataAccessException e) {
            logger.debug(
                    format("Teacher with UUID = %s was not added. Reason: %s", teacherId, e.getMessage()));
        } catch (DbObjectNotInsertedException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void editTeacher(Teacher teacher) {
        String teacherId = teacher.getIdTeacher();
        logger.info(format("Edit teacher with UUID = %s", teacherId));
        try {
            Boolean isUpdated = jdbcTemplate.execute("UPDATE uni.teachers t SET degree = ?, department = ?, " +
                    " permanent = ? , salary = ?  WHERE t.id_teacher = ? ",
                    (PreparedStatementCallback<Boolean>) ps ->
                        {
                            ps.setString(1, teacher.getDegree());
                            ps.setString(2, teacher.getDepartment());
                            ps.setBoolean(3, teacher.isPermanent());
                            ps.setBigDecimal(4, teacher.getSalary());
                            ps.setString(5, teacher.getIdTeacher());
                            return ps.execute();
                        });
//            if (Objects.nonNull(isUpdated) && !isUpdated) {
//                throw new NoSuchTeacherException(teacherId);
//            }
            logger.info(format("Teacher with UUID = %s updated sucessfully.", teacherId));
        } catch (DataAccessException e) {
            logger.debug(format("Teacher with UUID = %s was not updated.  Reason: %s", teacherId,
                    e.getMessage()));
        } catch (NoSuchTeacherException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void deleteTeacher(String teacherId) {
        logger.info(format("Delete teacher with UUID = %s", teacherId));
        try {
            Boolean isDeleted = jdbcTemplate.execute("DELETE from uni.teachers s WHERE s.id_teacher = ? ",
                    (PreparedStatementCallback<Boolean>) ps ->
                        {
                            ps.setString(1, teacherId);
                            return ps.execute();
                        });
//            if (Objects.nonNull(isDeleted) && !isDeleted) {
//                throw new NoSuchTeacherException(teacherId);
//            }
            logger.info(format("Teacher teacher with UUID = %s deleted sucessfully.", teacherId));
        } catch (DataAccessException e) {
            logger.debug(format("Teacher teacher with UUID = %s was not delete.  Reason: %s", teacherId,
                    e.getMessage()));
        } catch (NoSuchTeacherException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public List<Teacher> findTeacher(String teacherId) {
        logger.info(format("Find teacher with UUID = %s", teacherId));
        List<Teacher> teacher = new ArrayList<>();
        try {
            teacher = jdbcTemplate.query("Select * from uni.teachers t, uni.persons p " +
                    "  where t.id_person = p.id_person and t.id_teacher = ? ",
                    teacherMapper, teacherId);

            if (teacher.size() > 0) {
                logger.info(format("Teacher with UUID = %s found sucessfully.", teacherId));
            } else {
                throw new NoSuchTeacherException(teacherId);
            }
        } catch (DataAccessException e) {
            logger.debug(format("Teacher with UUID = %s was not found.  Reason: %s", teacherId,
                    e.getMessage()));
        } catch (NoSuchTeacherException e) {
            logger.debug(e.getMessage());
        }

        return teacher;
    }

    @Override
    public List<Teacher> findAllTeacher() {
        logger.info("Find all teachers. ");
        List<Teacher> teachers = new ArrayList<>();
        try {
            teachers = jdbcTemplate.query("Select * from uni.teachers t, uni.persons p " +
                    "where t.id_person = p.id_person", teacherMapper);

            if (teachers.size() > 0) {
                logger.info("Teachers found sucessfully.");
            } else {
                throw new NoTeachersFoundException();
            }
        } catch (DataAccessException e) {
            logger.debug(format("No database connection established or no data access. Reason: %s", e.getMessage()));
        } catch (NoTeachersFoundException e) {
            logger.debug(e.getMessage());
        }

        return teachers;
    }
}
