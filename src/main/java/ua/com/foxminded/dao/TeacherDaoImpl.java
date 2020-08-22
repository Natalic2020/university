package ua.com.foxminded.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Teacher;

@Repository
@Qualifier("teacherDao")
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addTeacher(Teacher teacher) {
        jdbcTemplate.update("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)",
                teacher.getPerson().getId(), teacher.getPerson().getFirstName(), teacher.getPerson().getLastName());
        
        jdbcTemplate.update("INSERT INTO uni.teachers (id, id_person, degree, department, permanent, salary ) values (?, ?, ?, ?, ?, ?)",
              teacher.getId(), teacher.getPerson().getId(), teacher.getDegree(), teacher.getDepartment(), teacher.isPermanent(), teacher.getSalary());
        
         System.out.println("Teacher Added!!"); 
    }

    @Override
    public void editTeacher(Teacher teacher, String id) {
        jdbcTemplate.update("UPDATE uni.teachers t SET degree = ?, department = ?, permanent = ? , salary = ?  WHERE t.id = ? ",
                teacher.getDegree(), teacher.getDepartment(), teacher.isPermanent(), teacher.getSalary(), id);
            System.out.println("Teacher Update");
    }

    @Override
    public void deleteTeacher(String id) {
        jdbcTemplate.update("DELETE from uni.teachers s WHERE s.id = ? ", id);
        System.out.println("Teacher Deleted!!");
    }

    @Override
    public List<Teacher> findTeacher(String id) {
        List<Teacher> teachers = jdbcTemplate.query("Select * from uni.teachers t, uni.persons p " + 
                "  where t.id_person = p.id and t.id = ? ", new TeacherMapper() , id);
            return teachers;
    }

    @Override
    public List<Teacher> findAllTeacher() {
        List <Teacher> teachers = jdbcTemplate.query("Select * from uni.teachers t, uni.persons p " + 
                "where t.id_person = p.id", new TeacherMapper());
        return teachers; 
    }
}
