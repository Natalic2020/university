package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.Group;
import ua.com.foxminded.model.dto.Group;

@Repository
@Qualifier("groupDao")
public class GroupDaoImpl implements GroupDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addGroup(Group group) {
        jdbcTemplate.update("INSERT INTO uni.groups (id, name, specialty) values (?, ?, ?)",
                group.getId(), group.getName(), group.getSpecialty());
        System.out.println("Group Added!!");
    }

    @Override
    public void editGroup(Group group, UUID id) {
        jdbcTemplate.update("UPDATE uni.groups g SET g.name = ? , g.specialty = ?  WHERE g.id = ? ",
                group.getName(), group.getSpecialty(), id);
            System.out.println("Group Updated!!");
    }

    @Override
    public void deleteGroup(UUID id) {
        jdbcTemplate.update("DELETE from uni.groups g WHERE g.id = ? ", id);
        System.out.println("Group Deleted!!");
    }

    @Override
    public Group findGroup(UUID id) {
        Group group = (Group) jdbcTemplate.queryForObject("SELECT * FROM uni.groups g where g.id = ? ",
                new Object[] { id }, new BeanPropertyRowMapper(Group.class));
            return group;
    }

    @Override
    public List<Group> findAllGroup() {
        List <Group> groups = jdbcTemplate.query("SELECT * FROM uni.groups", new BeanPropertyRowMapper(Group.class));
        return groups;
    }

}
