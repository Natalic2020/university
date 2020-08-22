package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.WeekScheduleDto;

public interface ScheduleService {

    public void addSchedule(WeekScheduleDto weekScheduleDto);
    public void editSchedule(ScheduleItemDto scheduleItemDto, UUID id);
    public void deleteSchedule(UUID id);
    public List<ScheduleItem> findScheduleStudent(UUID id, String startDate, String finishDate);
    public List<ScheduleItem> findScheduleTeacher(UUID id, String startDate, String finishDate);
}
