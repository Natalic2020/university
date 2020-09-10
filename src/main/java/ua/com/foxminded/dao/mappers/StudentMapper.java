package ua.com.foxminded.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ua.com.foxminded.dao.entity.Group;
import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Student;

@Component
public class StudentMapper implements RowMapper<Student>{

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getString("id"));
        student.setPerson(new Person()
                .setId(rs.getString("id_person"))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name")));
        student.setCitizenship(rs.getString("citizenship"));
        student.setGrant(rs.getBigDecimal("grants"));
        student.setStartOfStudy(rs.getDate("start_of_study").toLocalDate());
        student.setStudyStatus(rs.getString("study_status"));
        return student;
    }  
}
