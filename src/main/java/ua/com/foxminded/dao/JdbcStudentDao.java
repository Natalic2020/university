package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.com.foxminded.model.dto.Student;


public class JdbcStudentDao implements StudentDao{

    @Autowired
    private DataSource dataSource;
    
//    @Autowired
//    private Student student;
    
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }
    
//    @Autowired
//    public JdbcStudentDao(Student student) {
//        super();
//        this.student = student;
//    }



    @Override
    public void insert(Student student) {
        String sql = "INSERT INTO school.students " +
                "(student_id, first_name, last_name) VALUES (?, ?, ?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, 812);
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        } 
    }

    @Override
    public List<Student> receiveAllStudents() {
        String sql = "Select * From school.students";
        Connection conn = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                students.add(new Student(UUID.randomUUID(), resultSet.getString("first_name"), resultSet.getString("last_name")));
            }
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
            return students;
        }
    }
}
