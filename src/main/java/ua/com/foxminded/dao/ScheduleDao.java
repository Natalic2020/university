package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.dao.entity.Schedule;
import ua.com.foxminded.dao.entity.ScheduleItem;

public interface ScheduleDao {

    public void  addSchedule(List<Schedule> schedule);
    public List<Schedule> findScheduleTeacher(String id, String startDate, String finishDate);
    void fillTable();
}
