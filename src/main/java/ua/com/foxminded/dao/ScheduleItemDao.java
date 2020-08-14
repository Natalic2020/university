package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.ScheduleItem;

public interface ScheduleItemDao {
    
    public void addScheduleItem(ScheduleItem scheduleItem);
    public void editScheduleItem(ScheduleItem scheduleItem, UUID id);
    public void deleteScheduleItem(UUID id);
    public ScheduleItem findScheduleItem(UUID id);
    public List<ScheduleItem> findAllScheduleItem();
    
}
