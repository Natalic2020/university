package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.TimeSlot;

public interface TimeSlotDao {

    public void addTimeSlot(TimeSlot timeSlot);
    public void editTimeSlot(TimeSlot timeSlot, UUID id);
    public void deleteTimeSlot(UUID id);
    public TimeSlot findTimeSlot(UUID id);
    public List<TimeSlot> findAllTimeSlot();
}
