package ua.com.foxminded.dao.interfaces;

import java.time.LocalDate;
import java.util.List;

import ua.com.foxminded.dao.entity.Schedule;

public interface ScheduleDao {
    public void  addSchedule(Schedule schedule);
    public Schedule findScheduleTeacher(String lastName, LocalDate date);
    public Schedule findScheduleStudent(String lastName, LocalDate date);
}
