package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.Person;
import ua.com.foxminded.model.dto.Student;

@Repository
@Qualifier("studentDao")
public class StudentDaoImpl implements StudentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addStudent(Student student) {
        jdbcTemplate.update("INSERT INTO uni.students (id, id_person, study_status, start_of_study, citizenship , grants  ) values (?, ?, ?, ?, ?, ?, ?)",
               UUID.randomUUID(), student.getId(), student.getStudyStatus(), student.getStartOfStudy(), 
               student.getCitizenship(), student.getGrant());
        System.out.println("Student Added!!"); 
    }

    @Override
    public void editStudent(Student student, UUID id) {
        jdbcTemplate.update("UPDATE uni.students s SET citizenship = ? , startOfStudy = ?  WHERE p.id = ? ",
                student.getCitizenship(), student.getStartOfStudy(), id.toString());
            System.out.println("Person Updated!!");
    }

    @Override
    public void deleteStudent(UUID id) {
        jdbcTemplate.update("DELETE from uni.students s WHERE s.id = ? ", id.toString());
        System.out.println("Student Deleted!!");
    }

    @Override
    public Student findStudent(UUID id) {
        Student student = (Student) jdbcTemplate.queryForObject("SELECT * FROM uni.students s where s.id_person = ? ",
                new Object[] { id.toString() }, new BeanPropertyRowMapper(Student.class));
            return student;
    }

    @Override
    public List<Student> findAllStudent() {
        List <Student> students = jdbcTemplate.query("SELECT * FROM uni.students", new BeanPropertyRowMapper(Student.class));
        return students;
    }
}
