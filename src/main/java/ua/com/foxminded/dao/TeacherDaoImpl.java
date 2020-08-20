package ua.com.foxminded.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.model.dto.TeacherDto;

@Repository
@Qualifier("teacherDao")
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addTeacher(Teacher teacher) {
        jdbcTemplate.update("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)",
                teacher.getPerson().getId(), teacher.getPerson().getFirstName(), teacher.getPerson().getLastName());
        
        jdbcTemplate.update("INSERT INTO uni.teachers (id, id_person,  salary  ) values (?, ?, ?)",
                UUID.randomUUID(), teacher.getId(), teacher.getSalary());
         System.out.println("Teacher Added!!"); 
    }

    @Override
    public void editTeacher(Teacher teacher, String id) {
        jdbcTemplate.update("UPDATE uni.teachers t SET salary = ?   WHERE t.id = ? ",
                teacher.getSalary(), id);
            System.out.println("Teacher Update");
    }

    @Override
    public void deleteTeacher(String id) {
        jdbcTemplate.update("DELETE from uni.teachers s WHERE s.id = ? ", id);
        System.out.println("Teacher Deleted!!");
    }

    @Override
    public Teacher findTeacher(String id) {
        Teacher teacher = (Teacher) jdbcTemplate.queryForObject("SELECT * FROM uni.teachers t where t.id = ? ",
                new Object[] { id }, new BeanPropertyRowMapper(Teacher.class));
            return teacher;
    }

    @Override
    public List<Teacher> findAllTeacher() {
        List <Teacher> teachers = jdbcTemplate.query("SELECT * FROM uni.teachers", new TeacherMapper());
        return teachers; 
    }
}
