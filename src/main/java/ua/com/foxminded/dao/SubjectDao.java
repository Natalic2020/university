package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.Subject;

public interface SubjectDao {

    public void addSubject(Subject subject);
    public void editSubject(Subject subject, UUID id);
    public void deleteSubject(UUID id);
    public Subject findSubject(UUID id);
    public List<Subject> findAllSubject();
    
}
