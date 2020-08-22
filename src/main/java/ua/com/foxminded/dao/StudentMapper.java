package ua.com.foxminded.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.com.foxminded.dao.entity.Group;
import ua.com.foxminded.dao.entity.Person;
import ua.com.foxminded.dao.entity.Student;

public class StudentMapper implements RowMapper<Student>{

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getString("id"));
        student.setPerson(new Person().setId(rs.getString("id_person"))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name")));
//        student.setGroup(new Group().setId(rs.getString("id_group"))
//                .setName(rs.getString("group_name"))
//                .setSpecialty(rs.getString("specialty")));
        student.setCitizenship(rs.getString("citizenship"));
        student.setGrant(rs.getBigDecimal("grants"));
        student.setStartOfStudy(rs.getDate("start_of_study").toLocalDate());
        student.setStudyStatus(rs.getString("study_status"));
        return student;
    }  
}
