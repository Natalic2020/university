package ua.com.foxminded.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ua.com.foxminded.converter.GroupConverter;
import ua.com.foxminded.converter.TeacherConverter;
import ua.com.foxminded.dao.interfaces.GroupDao;
import ua.com.foxminded.dao.interfaces.TeacherDao;
import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.service.interfaces.GroupService;

@Service("groupService")
@Component
public class GroupServiceImpl implements GroupService{

    @Autowired
    @Qualifier("groupDao")
    GroupDao groupDao;
    
    @Autowired
    GroupConverter groupConverter;
    
    @Override
    public void addGroup(GroupDto group) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void editGroup(GroupDto group) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteGroup(UUID uuid) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public GroupDto findGroup(UUID uuid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GroupDto> findAllGroups() {
        List<GroupDto> groups = new ArrayList<>();
        groupDao.findAll().forEach(group -> {
            groups.add(groupConverter.convertEntityToDto(group));
        });
        return groups;   
    }
}

