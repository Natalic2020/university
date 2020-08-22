package ua.com.foxminded.dao;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Student;

@Repository
@Qualifier("studentDao")
public class StudentDaoImpl implements StudentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addStudent(Student student) {
        
//        jdbcTemplate.update("IINSERT INTO uni.time_slots (id, serial_number, start_time, finish_time ) values (?, ?, ?, ?) " ,
//                "1","1", LocalTime.of(8,0,0), LocalTime.of(9,30,0));
        
//        jdbcTemplate.update("INSERT INTO uni.time_slots (id, serial_number, start_time ) values (?, ?, ?) " ,
//                student.getPerson().getId(), student.getPerson().getId(), LocalTime.of(8,0,0));
        
        jdbcTemplate.update("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)",
                student.getPerson().getId(), student.getPerson().getFirstName(), student.getPerson().getLastName());

        jdbcTemplate.update(
                "INSERT INTO uni.students (id, id_person, study_status, start_of_study, citizenship , grants  ) values (?, ?, ?, ?, ?, ?)",
                student.getId(), student.getPerson().getId(), student.getStudyStatus(),
                student.getStartOfStudy(), student.getCitizenship(), student.getGrant());
        System.out.println("Student Added!!");
    }

    @Override
    public void editStudent(Student student, String id) {
        jdbcTemplate.update("UPDATE uni.students s SET citizenship = ?, study_status = ?, grants = ?, start_of_study = ?  WHERE s.id = ? ",
                student.getCitizenship(), student.getStudyStatus(), student.getGrant(), student.getStartOfStudy(), id);
        System.out.println("Student Updated!!");
    }

    @Override
    public void deleteStudent(String id) {
        jdbcTemplate.update("DELETE from uni.students s WHERE s.id_person = ? ", id);
        System.out.println("Student Deleted!!");
    }

    @Override
    public List<Student> findStudent(String id) {
        List<Student> students = jdbcTemplate.query("Select * from uni.students s, uni.persons p " + 
                " where s.id_person = p.id and s.id = ? ", new StudentMapper() , id);
        return students;
    }

    @Override
    public List<Student> findAllStudent() {
        List<Student> students = jdbcTemplate.query("Select * from uni.students s, uni.persons p " + 
                " where s.id_person = p.id", new StudentMapper());
        return students;
    }
}
