package ua.com.foxminded.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        logger.info("Get teacher with UUID = " + teacher.getIdTeacher() + " , " 
                + teacher.getLastName());
        try {
            jdbcTemplate.execute("INSERT INTO uni.persons (id_person, first_name, last_name) " +
                    " values (?, ?, ?)",
                    new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,teacher.getIdPerson()); 
                    ps.setString(2,teacher.getFirstName());
                    ps.setString(3,teacher.getLastName());
                    
                    return ps.execute();  
                }  
                });  
            
            jdbcTemplate.execute("INSERT INTO uni.teachers (id_teacher, id_person, degree, " +
              " department, permanent, salary ) values (?, ?, ?, ?, ?, ?)", 
                  new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,teacher.getIdTeacher());  
                    ps.setString(2,teacher.getIdPerson());
                    ps.setString(3,teacher.getDegree());
                    ps.setString(4,teacher.getDepartment());
                    ps.setBoolean(5,teacher.isPermanent());
                    ps.setBigDecimal(6,teacher.getSalary());
                    return ps.execute();  
                }  
                });  
                  
            logger.info("Teacher added sucessfully.");
        } catch (DataAccessException e) {
            logger.debug("Teacher didn't add!!  Reason: " + e.getMessage());
        } 
    }

    @Override
    public void editTeacher(Teacher teacher) {
        logger.info("Edit teacher with UUID = " + teacher.getIdTeacher());
        try {
            jdbcTemplate.execute("UPDATE uni.teachers t SET degree = ?, department = ?, " + 
                    " permanent = ? , salary = ?  WHERE t.id_teacher = ? ",
                    new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,teacher.getDegree());  
                    ps.setString(2,teacher.getDepartment());
                    ps.setBoolean(3,teacher.isPermanent());
                    ps.setBigDecimal(4,teacher.getSalary());
                    ps.setString(5,teacher.getIdTeacher());
                    return ps.execute();  
                }  
                });  
            logger.info("Teacher updated sucessfully.");
        } catch (DataAccessException e) {
            logger.debug("Teacher didn't update!!  Reason: " + e.getMessage());
        }
    }

    @Override
    public void deleteTeacher(String id) {
        logger.info("Delete teacher with UUID = " + id);
        try {
            jdbcTemplate.execute("DELETE from uni.teachers s WHERE s.id_teacher = ? ",
                    new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,id);  
                    return ps.execute();  
                }  
                });  
            logger.info("Teacher deleted sucessfully.");
        } catch (DataAccessException e) {
            logger.debug("Teacher didn't delete!!  Reason: " + e.getMessage());
        }
    }

    @Override
    public List<Teacher> findTeacher(String id) {
        logger.info("Find teacher with id  = " + id);
        List<Teacher> teacher = new ArrayList<>();
        try {
            teacher = jdbcTemplate.query("Select * from uni.teachers t, uni.persons p " + 
                    "  where t.id_person = p.id_person and t.id_teacher = ? ", 
                    teacherMapper, id);
            logger.info("Teacher found sucessfully.");
        } catch (DataAccessException e) {
            logger.debug(" I can't find the teacher id = " + id + 
                    ". Reason: " + e.getMessage());
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
            logger.info("Teachers found sucessfully.");
        } catch (DataAccessException e) {
            logger.debug(" I can't find all teachers . Reason: " + e.getMessage());
        }
        return teachers; 
    }
}
