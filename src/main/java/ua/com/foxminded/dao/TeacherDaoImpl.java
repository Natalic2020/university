package ua.com.foxminded.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.TeacherDto;

@Repository
@Qualifier("teacherDao")
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addTeacher(TeacherDto teacher) {
        jdbcTemplate.update("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)",
                teacher.getId().toString(), teacher.getFirstName(), teacher.getLastName());
        
        jdbcTemplate.update("INSERT INTO uni.teachers (id, id_person,  salary  ) values (?, ?, ?)",
                UUID.randomUUID(), teacher.getId(), teacher.getSalary());
         System.out.println("Teacher Added!!"); 
    }

    @Override
    public void editTeacher(TeacherDto teacher, UUID id) {
        jdbcTemplate.update("UPDATE uni.teachers t SET salary = ?   WHERE t.id_person = ? ",
                teacher.getSalary(), id.toString());
            System.out.println("Teacher Update");
    }

    @Override
    public void deleteTeacher(UUID id) {
        jdbcTemplate.update("DELETE from uni.teachers s WHERE s.id_person = ? ", id.toString());
        System.out.println("Teacher Deleted!!");
    }

    @Override
    public TeacherDto findTeacher(UUID id) {
        TeacherDto teacher = (TeacherDto) jdbcTemplate.queryForObject("SELECT * FROM uni.teachers t where t.id_person = ? ",
                new Object[] { id.toString() }, new BeanPropertyRowMapper(TeacherDto.class));
            return teacher;
    }

    @Override
    public List<TeacherDto> findAllTeacher() {
//        List <TeacherDto> teachers = jdbcTemplate.query("SELECT * FROM uni.teachers", new BeanPropertyRowMapper(TeacherDto.class));
//        return teachers;
      return   new ArrayList<>();
    }
}
