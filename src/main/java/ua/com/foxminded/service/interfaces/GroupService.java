package ua.com.foxminded.service.interfaces;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.GroupDto;

public interface GroupService {
    public void addGroup(GroupDto group);
    public void editGroup(GroupDto group);
    public void deleteGroup(UUID uuid);
    public GroupDto findGroup(UUID uuid);
    public List<GroupDto> findAllGroups(); 
}
