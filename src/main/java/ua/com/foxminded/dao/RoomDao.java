package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.Room;

public interface RoomDao {

    public void addRoom(Room room);
    public void editRoom(Room room, UUID id);
    public void deleteRoom(UUID id);
    public Room findRoom(UUID id);
    public List<Room> findAllRoom();
    
}
