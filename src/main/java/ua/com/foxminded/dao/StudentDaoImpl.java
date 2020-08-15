package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.PersonDto;
import ua.com.foxminded.model.dto.StudentDto;

@Repository
@Qualifier("studentDao")
public class StudentDaoImpl implements StudentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addStudent(StudentDto student) {
        jdbcTemplate.update("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)",
                student.getId().toString(), student.getFirstName(), student.getLastName());

        jdbcTemplate.update(
                "INSERT INTO uni.students (id, id_person, study_status, start_of_study, citizenship , grants  ) values (?, ?, ?, ?, ?, ?)",
                UUID.randomUUID().toString(), student.getId().toString(), student.getStudyStatus(),
                student.getStartOfStudy(), student.getCitizenship(), student.getGrant());
        System.out.println("Student Added!!");
    }

    @Override
    public void editStudent(StudentDto student, UUID id) {
//        jdbcTemplate.update("UPDATE uni.students s SET citizenship = ? , startOfStudy = ?  WHERE s.id_person = ? ",
//                student.getCitizenship(), student.getStartOfStudy(), id.toString());
//            System.out.println("Person Updated!!");
        jdbcTemplate.update("UPDATE uni.students s SET citizenship = ?    WHERE s.id_person = ? ",
                student.getCitizenship(),  id.toString());
        System.out.println("Student Updated!!");
    }

    @Override
    public void deleteStudent(UUID id) {
        jdbcTemplate.update("DELETE from uni.students s WHERE s.id_person = ? ", id.toString());
        System.out.println("Student Deleted!!");
    }

    @Override
    public StudentDto findStudent(UUID id) {
        StudentDto student = (StudentDto) jdbcTemplate.queryForObject(
                "SELECT * FROM uni.students s where s.id_person = ? ",
                new Object[] { id.toString() }, new BeanPropertyRowMapper(StudentDto.class));
        return student;
    }

    @Override
    public List<StudentDto> findAllStudent() {
        List<StudentDto> students = jdbcTemplate.query("SELECT * FROM uni.students",
                new BeanPropertyRowMapper(StudentDto.class));
        return students;
    }
}
