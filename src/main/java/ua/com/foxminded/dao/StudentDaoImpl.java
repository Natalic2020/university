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
            jdbcTemplate.execute("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)",
                    new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,student.getPerson().getId());  
                    ps.setString(2,student.getPerson().getFirstName());
                    ps.setString(3,student.getPerson().getLastName());
                    return ps.execute();  
                }  
                });  

            jdbcTemplate.execute(
                    "INSERT INTO uni.students (id, id_person, study_status, start_of_study, citizenship , grants  ) values (?, ?, ?, ?, ?, ?)",
                    new PreparedStatementCallback<Boolean>(){
                        @Override  
                        public Boolean doInPreparedStatement(PreparedStatement ps)  
                                throws SQLException, DataAccessException {                 
                            ps.setString(1,student.getId());  
                            ps.setString(2,student.getPerson().getId());
                            ps.setString(3,student.getStudyStatus());
                            ps.setDate(4,java.sql.Date.valueOf(student.getStartOfStudy()));
                            ps.setString(5,student.getCitizenship());
                            ps.setBigDecimal(6,student.getGrant());
                            return ps.execute();  
                        }  
                        });  
            System.out.println("Student Added!!");
        } catch (DataAccessException e) {
            System.out.println("Student didn't add!!");
            e.printStackTrace();
        }
    }

    @Override
    public void editStudent(Student student) {
        try {
            jdbcTemplate.execute("UPDATE uni.students s SET citizenship = ?, study_status = ?, grants = ?, start_of_study = ?  WHERE s.id = ? ",
                    new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,student.getCitizenship());  
                    ps.setString(2,student.getStudyStatus()); 
                    ps.setBigDecimal(3,student.getGrant()); 
                    ps.setDate(4, java.sql.Date.valueOf(student.getStartOfStudy())); 
                    ps.setString(5,student.getId()); 
                    return ps.execute();  
                }  
                });  
            System.out.println("Student Updated!!");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(String id) {
        try {
            jdbcTemplate.execute("DELETE from uni.students s WHERE s.id_person = ? ", new PreparedStatementCallback<Boolean>(){
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)  
                        throws SQLException, DataAccessException {                 
                    ps.setString(1,id);  
                    return ps.execute();  
                }  
                });  
      
            System.out.println("Student Deleted!!");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findStudent(String id) {
        List<Student> students = jdbcTemplate.query("Select * from uni.students s, uni.persons p " + 
                " where s.id_person = p.id and s.id = ? ", studentMapper , id);
        return students;
    }

    @Override
    public List<Student> findAllStudent() {
        List<Student> students = jdbcTemplate.query("Select * from uni.students s, uni.persons p " + 
                " where s.id_person = p.id", studentMapper);
        return students;
    }
}
