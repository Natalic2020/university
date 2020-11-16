package ua.com.foxminded.dao.interfaces;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.dao.entity.Group;

@Repository
@Qualifier("groupDao")
public interface GroupDao extends CrudRepository<Group, String>{
}
