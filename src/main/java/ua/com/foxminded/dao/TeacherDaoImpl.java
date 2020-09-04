package ua.com.foxminded.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            jdbcTemplate.execute("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)", new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,teacher.getPerson().getId()); 
                    ps.setString(2,teacher.getPerson().getFirstName());
                    ps.setString(3,teacher.getPerson().getLastName());
                    
                    return ps.execute();  
                }  
                });  
            
            jdbcTemplate.execute("INSERT INTO uni.teachers (id, id_person, degree, department, permanent, salary ) values (?, ?, ?, ?, ?, ?)",new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,teacher.getId());  
                    ps.setString(2,teacher.getPerson().getId());
                    ps.setString(3,teacher.getDegree());
                    ps.setString(4,teacher.getDepartment());
                    ps.setBoolean(5,teacher.isPermanent());
                    ps.setBigDecimal(6,teacher.getSalary());
                    return ps.execute();  
                }  
                });  
                  
             System.out.println("Teacher Added!!");
        } catch (DataAccessException e) {
            e.printStackTrace();
        } 
    }

    @Override
    public void editTeacher(Teacher teacher) {
        try {
            jdbcTemplate.execute("UPDATE uni.teachers t SET degree = ?, department = ?, permanent = ? , salary = ?  WHERE t.id = ? ",
                    new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,teacher.getDegree());  
                    ps.setString(2,teacher.getDepartment());
                    ps.setBoolean(3,teacher.isPermanent());
                    ps.setBigDecimal(4,teacher.getSalary());
                    ps.setString(5,teacher.getId());
                    return ps.execute();  
                }  
                });  
                System.out.println("Teacher Update");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeacher(String id) {
        try {
            jdbcTemplate.execute("DELETE from uni.teachers s WHERE s.id = ? ", new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,id);  
                    return ps.execute();  
                }  
                });  
            System.out.println("Teacher Deleted!!");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Teacher> findTeacher(String id) {
        List<Teacher> teachers = jdbcTemplate.query("Select * from uni.teachers t, uni.persons p " + 
                "  where t.id_person = p.id and t.id = ? ", teacherMapper, id);
            return teachers;
    }

    @Override
    public List<Teacher> findAllTeacher() {
        List <Teacher> teachers = jdbcTemplate.query("Select * from uni.teachers t, uni.persons p " + 
                "where t.id_person = p.id", teacherMapper);
        return teachers; 
    }
}
