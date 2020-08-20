package ua.com.foxminded.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Teacher;

public class TeacherMapper  implements RowMapper<Teacher>  {

    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(rs.getString("id"));
        teacher.setPerson(new Person().setId(rs.getString("id_person"))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name")));
        teacher.setDegree(rs.getString("degree"));
        teacher.setDepartment(rs.getString("department"));
        teacher.setPermanent(rs.getBoolean("permanent"));
        teacher.setSalary(BigDecimal.valueOf(rs.getLong("salary")));
        
        return teacher;
    }  
}