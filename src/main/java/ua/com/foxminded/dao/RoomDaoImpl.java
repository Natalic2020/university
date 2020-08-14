package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.Room;

@Repository
@Qualifier("roomDao")
public class RoomDaoImpl implements RoomDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addRoom(Room room) {
        // TODO Auto-generated method stub

    }

    @Override
    public void editRoom(Room room, UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRoom(UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Room findRoom(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Room> findAllRoom() {
        // TODO Auto-generated method stub
        return null;
    }

}
