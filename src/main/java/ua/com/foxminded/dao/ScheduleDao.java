package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.dao.entity.Schedule;
import ua.com.foxminded.dao.entity.ScheduleItem;

public interface ScheduleDao {

    public void  addSchedule(Schedule schedule);
    public void editItemSchedule(ScheduleItem scheduleItem, String id);
    public void deleteItemSchedule(String id);
    public List<ScheduleItem> findScheduleStudent(String id, String startDate, String finishDate);
    public List<ScheduleItem> findScheduleTeacher(String id, String startDate, String finishDate);
    
}
