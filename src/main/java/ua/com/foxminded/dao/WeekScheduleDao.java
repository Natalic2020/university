package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.WeekSchedule;

public interface WeekScheduleDao {

    public void addWeekSchedule(WeekSchedule weekSchedule);
    public void editWeekSchedule(WeekSchedule weekSchedule, UUID id);
    public void deleteWeekSchedule(UUID id);
    public WeekSchedule findWeekSchedule(UUID id);
    public List<WeekSchedule> findAllWeekSchedule();
    
}
