package ua.com.foxminded.dao;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.interfaces.TeacherDao;
import ua.com.foxminded.dao.mappers.TeacherMapper;
import ua.com.foxminded.exception.DbObjectNotInsertedException;
import ua.com.foxminded.exception.NoSuchTeacherException;
import ua.com.foxminded.exception.NoTeachersFoundException;

import static java.lang.String.format;

//@Repository
//@Qualifier("teacherDao")
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TeacherMapper teacherMapper;

    Logger logger = LoggerFactory.getLogger("SampleLogger");

    @Override
    public void addTeacher(Teacher teacher) {
        String personId = teacher.getIdPerson();
        logger.info(format("Add teacher with UUID = %s", personId));
        try {
            int countPersonInserted = jdbcTemplate.update(
                    "INSERT INTO uni.persons (id_person, first_name, last_name) " +
                            " values (?, ?, ?)",
                    teacher.getIdPerson(), teacher.getFirstName(), teacher.getLastName());

            if (countPersonInserted == 0) {
                throw new DbObjectNotInsertedException(teacher);
            }
            logger.info(format("Person with UUID = %s added successfully.", personId));

            logger.info(format("Add teacher with UUID = %s", personId));
            int countTeacherInserted = jdbcTemplate.update(
                    "INSERT INTO uni.teachers ( id_person, degree, " +
                            " department, permanent, salary ) values (?, ?, ?, ?, ?)",
                    teacher.getIdPerson(), teacher.getDegree(),
                    teacher.getDepartment(), teacher.isPermanent(), teacher.getSalary());

            if (countTeacherInserted == 0) {
                throw new DbObjectNotInsertedException(teacher);
            }
            logger.info(format("Teacher with UUID = %s added successfully.", personId));
        } catch (DataAccessException e) {
            logger.debug(
                    format("Teacher with UUID = %s was not added. Reason: %s", personId, e.getMessage()));
        } catch (DbObjectNotInsertedException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void editTeacher(Teacher teacher) {
        String personId = teacher.getIdPerson();
        logger.info(format("Edit person with UUID = %s", personId));
        try {
            int countPersonUpdated = jdbcTemplate.update(
                    "UPDATE uni.persons  SET  first_name= ?, last_name= ? " +
                            " WHERE id_person = ? ",
                    teacher.getFirstName(), teacher.getLastName(), personId);
            
            logger.info(format("Person with UUID = %s updated sucessfully.", personId));

            logger.info(format("Edit teacher with UUID = %s", personId));

            int countStudentUpdated = jdbcTemplate.update("UPDATE uni.teachers  SET degree = ?, department = ?, " +
                    " permanent = ? , salary = ?  WHERE id_person = ? ",
                    teacher.getDegree(), teacher.getDepartment(), teacher.isPermanent(),
                    teacher.getSalary(), personId);

            if (countStudentUpdated == 0) {
                throw new NoSuchTeacherException(personId);
            }
            logger.info(format("Teacher with UUID = %s updated sucessfully.", personId));
        } catch (DataAccessException e) {
            logger.debug(format("Teacher with UUID = %s was not updated.  Reason: %s", personId,
                    e.getMessage()));
        } catch (NoSuchTeacherException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void deleteTeacher(String personId) {
        logger.info(format("Delete teacher with UUID = %s", personId));
        try {
            int countDeleted = jdbcTemplate.update("DELETE from uni.teachers s WHERE s.id_person = ? ",
                    personId);

            if (countDeleted == 0) {
                throw new NoSuchTeacherException(personId);
            }
            logger.info(format("Teacher teacher with UUID = %s deleted sucessfully.", personId));
        } catch (DataAccessException e) {
            logger.debug(format("Teacher teacher with UUID = %s was not delete.  Reason: %s", personId,
                    e.getMessage()));
        } catch (NoSuchTeacherException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public Teacher findTeacher(String personId) {
        logger.info(format("Find teacher with UUID = %s", personId));
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher  = new Teacher();
        try {
            teachers = jdbcTemplate.query("Select * from uni.teachers t, uni.persons p " +
                    "  where t.id_person = p.id_person and t.id_person = ? ",
                    teacherMapper, personId);

            if (teachers.size() == 0) {
                throw new NoSuchTeacherException(personId);
            }
            teacher = teachers.get(0);
            logger.info(format("Teacher with UUID = %s found sucessfully.", personId));
        } catch (DataAccessException e) {
            logger.debug(format("Teacher with UUID = %s was not found.  Reason: %s", personId,
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

            if (teachers.size() == 0) {
                throw new NoTeachersFoundException();
            }
            logger.info("Teachers found sucessfully.");
        } catch (DataAccessException e) {
            logger.debug(format("No database connection established or no data access. Reason: %s", e.getMessage()));
        } catch (NoTeachersFoundException e) {
            logger.debug(e.getMessage());
        }
        return teachers;
    }
}
