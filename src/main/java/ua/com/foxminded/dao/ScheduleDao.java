package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.WeekScheduleDto;

public interface ScheduleDao {

    public void addWeekSchedule(WeekScheduleDto weekSchedule);
    public void editWeekSchedule(WeekScheduleDto weekSchedule, UUID id);
    public void deleteWeekSchedule(UUID id);
    public WeekScheduleDto findWeekSchedule(UUID id);
    public List<WeekScheduleDto> findAllWeekSchedule();
    
}
