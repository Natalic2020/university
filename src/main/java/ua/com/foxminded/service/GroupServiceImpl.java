package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.dao.GroupDao;
import ua.com.foxminded.model.dto.Group;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupDao groupDao;
    
    @Override
    public void addGroup(Group group) {
        groupDao.addGroup(group);
    }

    @Override
    public void editGroup(Group group, UUID id) {
        groupDao.editGroup(group, id);
    }

    @Override
    public void deleteGroup(UUID id) {
        groupDao.deleteGroup(id);
    }

    @Override
    public Group findGroup(UUID id) {
        return groupDao.findGroup(id);
    }

    @Override
    public List<Group> findAllGroup() {
        return groupDao.findAllGroup();
    }
}
