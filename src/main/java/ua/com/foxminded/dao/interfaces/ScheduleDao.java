package ua.com.foxminded.dao.interfaces;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.dao.entity.Schedule;

public interface ScheduleDao {

    public void  addSchedule(List<Schedule> schedule);
    public List<Schedule> findScheduleTeacher(String lastName, LocalDate date);
    public List<Schedule> findScheduleStudent(String lastName, LocalDate date);
}
