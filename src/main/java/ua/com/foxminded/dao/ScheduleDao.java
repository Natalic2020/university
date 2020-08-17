package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.dao.entity.ScheduleItem;

public interface ScheduleDao {

    public void addWeekSchedule(ScheduleItem scheduleItem);
    public void editWeekSchedule(ScheduleItem scheduleItem, String id);
    public void deleteWeekSchedule(String id);
    public List<ScheduleItem> findScheduleStudent(String id, String startDate, String finishDate);
    public List<ScheduleItem> findScheduleTeacher(String id, String startDate, String finishDate);
}
