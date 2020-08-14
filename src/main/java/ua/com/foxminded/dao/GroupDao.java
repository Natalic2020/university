package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.Group;

public interface GroupDao {

    public void addGroup(Group group);
    public void editGroup(Group group, UUID id);
    public void deleteGroup(UUID id);
    public Group findGroup(UUID id);
    public List<Group> findAllGroup();
    
}
