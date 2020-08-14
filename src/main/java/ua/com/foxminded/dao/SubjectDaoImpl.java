package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.Subject;

@Repository
@Qualifier("subjectDao")
public class SubjectDaoImpl implements SubjectDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addSubject(Subject subject) {
        // TODO Auto-generated method stub

    }

    @Override
    public void editSubject(Subject subject, UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteSubject(UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Subject findSubject(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Subject> findAllSubject() {
        // TODO Auto-generated method stub
        return null;
    }

}
