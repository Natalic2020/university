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

import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.interfaces.StudentDao;
import ua.com.foxminded.dao.mappers.StudentMapper;

@Repository
@Qualifier("studentDao")
public class StudentDaoImpl implements StudentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StudentMapper studentMapper;
    
    @Override
    public void addStudent(Student student) {
        
        try {
            jdbcTemplate.execute("INSERT INTO uni.persons (id_person, first_name, last_name) values (?, ?, ?)",
                    new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                        ps.setString(1,student.getIdPerson());  
                        ps.setString(2,student.getFirstName());
                        ps.setString(3,student.getLastName());
                    
                
                    return ps.execute();  
                }  
                });  

            jdbcTemplate.execute(
                    "INSERT INTO uni.students (id_student, id_person, study_status, start_of_study, citizenship , grants  ) values (?, ?, ?, ?, ?, ?)",
                    new PreparedStatementCallback<Boolean>(){
                        @Override  
                        public Boolean doInPreparedStatement(PreparedStatement ps)  
                                throws SQLException, DataAccessException {                 
                            ps.setString(1,student.getIdStudent());  
                            ps.setString(2,student.getIdPerson());
                            ps.setString(3,student.getStudyStatus());
                            ps.setDate(4,java.sql.Date.valueOf(student.getStartOfStudy()));
                            ps.setString(5,student.getCitizenship());
                            ps.setBigDecimal(6,student.getGrant());
                            return ps.execute();  
                        }  
                        });  
            System.out.println("Student Added!!");
        } catch (DataAccessException e) {
            System.out.println("Student didn't add!!  Reason: " + e.getMessage());
        }
    }

    @Override
    public void editStudent(Student student) {
        try {
            jdbcTemplate.execute("UPDATE uni.students s SET citizenship = ?, study_status = ?, grants = ?, start_of_study = ?  WHERE s.id_student = ? ",
                    new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,student.getCitizenship());  
                    ps.setString(2,student.getStudyStatus()); 
                    ps.setBigDecimal(3,student.getGrant()); 
                    ps.setDate(4, java.sql.Date.valueOf(student.getStartOfStudy())); 
                    ps.setString(5,student.getIdStudent()); 
                    return ps.execute();  
                }  
                });  
            System.out.println("Student Updated!!");
        } catch (DataAccessException e) {
            System.out.println("Student didn't update!!  Reason: " + e.getMessage());
        }
    }

    @Override
    public void deleteStudent(String id) {
        try {
            jdbcTemplate.execute("DELETE from uni.students s WHERE s.id_student = ? ", new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,id);  
                    return ps.execute();  
                }  
                });  
      
            System.out.println("Student Deleted!!");
        } catch (DataAccessException e) {
            System.out.println("Student didn't delete!!  Reason: " + e.getMessage());
        }
    }

    @Override
    public List<Student> findStudent(String lastName) {
        List<Student> students = new ArrayList<>();
        try {
            students = jdbcTemplate.query("Select * from uni.students s, uni.persons p " + 
                    " where s.id_person = p.id_person and p.last_name = ? ", studentMapper , lastName);
        } catch (DataAccessException e) {
            System.out.println(" I can't find the student id = " + lastName +". Reason: " + e.getMessage());
        }
        return students;
    }

    @Override
    public List<Student> findAllStudent() {
        List<Student> students = new ArrayList<>();
        try {
            students = jdbcTemplate.query("Select * from uni.students s, uni.persons p " + 
                    " where s.id_person = p.id_person", studentMapper);
        } catch (DataAccessException e) {
            System.out.println(" I can't find all students. Reason: " + e.getMessage());
        }
        return students;
    }
}
