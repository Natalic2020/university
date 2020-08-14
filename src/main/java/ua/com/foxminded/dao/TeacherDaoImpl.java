package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.Teacher;

@Repository
@Qualifier("teacherDao")
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addTeacher(Teacher teacher) {
        jdbcTemplate.update("INSERT INTO uni.teachers (id, id_person,  salary  ) values (?, ?, ?)",
                UUID.randomUUID(), teacher.getId(), teacher.getSalary());
         System.out.println("Teacher Added!!"); 
    }

    @Override
    public void editTeacher(Teacher teacher, UUID id) {
        jdbcTemplate.update("UPDATE uni.teachers t SET salary = ?   WHERE t.id = ? ",
                teacher.getSalary(), id.toString());
            System.out.println("Teacher Update");
    }

    @Override
    public void deleteTeacher(UUID id) {
        jdbcTemplate.update("DELETE from uni.teachers s WHERE s.id = ? ", id.toString());
        System.out.println("Teacher Deleted!!");
    }

    @Override
    public Teacher findTeacher(UUID id) {
        Teacher teacher = (Teacher) jdbcTemplate.queryForObject("SELECT * FROM uni.teachers s where s.id_person = ? ",
                new Object[] { id.toString() }, new BeanPropertyRowMapper(Teacher.class));
            return teacher;
    }

    @Override
    public List<Teacher> findAllTeacher() {
        List <Teacher> teachers = jdbcTemplate.query("SELECT * FROM uni.teachers", new BeanPropertyRowMapper(Teacher.class));
        return teachers;
    }

}
