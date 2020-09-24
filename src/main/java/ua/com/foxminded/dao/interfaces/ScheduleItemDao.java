package ua.com.foxminded.dao.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.model.dto.ScheduleItemDto;

public interface ScheduleItemDao {
    public void  addScheduleItem(ScheduleItem  scheduleItem);
    public void  editScheduleItem(ScheduleItem scheduleItem);
    public void  deleteScheduleItem(String string);
    public List<ScheduleItem> findScheduleTeacher(String lastName);
    public List<ScheduleItem> findScheduleStudent(String lastName);
    public List<ScheduleItem> findWeekScheduleTeacher(UUID id);
    public List<ScheduleItem> findWeekScheduleStudent(UUID id);
}
