package ua.com.foxminded.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    
    @Override
    public void addTeacher(Teacher teacher) {
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
                  
             System.out.println("Teacher Added!!");
        } catch (DataAccessException e) {
            System.out.println("Teacher didn't add!!  Reason: " + e.getMessage());
        } 
    }

    @Override
    public void editTeacher(Teacher teacher) {
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
                System.out.println("Teacher Update");
        } catch (DataAccessException e) {
            System.out.println("Teacher didn't update!!  Reason: " + e.getMessage());
        }
    }

    @Override
    public void deleteTeacher(String id) {
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
            System.out.println("Teacher Deleted!!");
        } catch (DataAccessException e) {
            System.out.println("Teacher didn't delete!!  Reason: " + e.getMessage());
        }
    }

    @Override
    public List<Teacher> findTeacher(String id) {
        List<Teacher> teacher = new ArrayList<>();
        try {
            teacher = jdbcTemplate.query("Select * from uni.teachers t, uni.persons p " + 
                    "  where t.id_person = p.id_person and t.id_teacher = ? ", 
                    teacherMapper, id);
        } catch (DataAccessException e) {
            System.out.println(" I can't find the teacher id = " + id + 
                    ". Reason: " + e.getMessage());
        }
            return teacher;
    }

    @Override
    public List<Teacher> findAllTeacher() {
        List<Teacher> teachers = new ArrayList<>();
        try {
            teachers = jdbcTemplate.query("Select * from uni.teachers t, uni.persons p " + 
                    "where t.id_person = p.id_person", teacherMapper);
        } catch (DataAccessException e) {
            System.out.println(" I can't find all teachers . Reason: " + e.getMessage());
        }
        return teachers; 
    }
}
