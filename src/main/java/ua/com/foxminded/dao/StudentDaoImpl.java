package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.model.dto.PersonDto;
import ua.com.foxminded.model.dto.StudentDto;

@Repository
@Qualifier("studentDao")
public class StudentDaoImpl implements StudentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addStudent(Student student) {
        jdbcTemplate.update("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)",
                student.getPerson().getId(), student.getPerson().getFirstName(), student.getPerson().getLastName());

        jdbcTemplate.update(
                "INSERT INTO uni.students (id, id_person, study_status, start_of_study, citizenship , grants  ) values (?, ?, ?, ?, ?, ?)",
                UUID.randomUUID().toString(), student.getId(), student.getStudyStatus(),
                student.getStartOfStudy(), student.getCitizenship(), student.getGrant());
        System.out.println("Student Added!!");
    }

    @Override
    public void editStudent(Student student, String id) {
//        jdbcTemplate.update("UPDATE uni.students s SET citizenship = ? , startOfStudy = ?  WHERE s.id_person = ? ",
//                student.getCitizenship(), student.getStartOfStudy(), id.toString());
//            System.out.println("Person Updated!!");
        jdbcTemplate.update("UPDATE uni.students s SET citizenship = ? , startOfStudy = ?   WHERE s.id_person = ? ",
                student.getCitizenship(), student.getStartOfStudy(), id);
        System.out.println("Student Updated!!");
    }

    @Override
    public void deleteStudent(String id) {
        jdbcTemplate.update("DELETE from uni.students s WHERE s.id_person = ? ", id.toString());
        System.out.println("Student Deleted!!");
    }

    @Override
    public Student findStudent(String id) {
        Student student = (Student) jdbcTemplate.queryForObject(
                "SELECT * FROM uni.students s where s.id_person = ? ",
                new Object[] { id }, new BeanPropertyRowMapper(Student.class));
        return student;
    }

    @Override
    public List<Student> findAllStudent() {
        List<Student> students = jdbcTemplate.query("SELECT * FROM uni.students",
                new BeanPropertyRowMapper(Student.class));
        return students;
    }
}
