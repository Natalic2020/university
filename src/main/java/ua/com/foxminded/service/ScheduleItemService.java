package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.StudentDto;

public interface ScheduleItemService {

    public void addScheduleItem(ScheduleItemDto scheduleItemDto);
    public void editScheduleItemt(ScheduleItemDto scheduleItemDto, UUID id);
    public void deleteScheduleItem(UUID id);
    public List<ScheduleItem> findScheduleStudent(UUID id, String startDate, String finishDate);
    public List<ScheduleItem> findScheduleTeacher(UUID id, String startDate, String finishDate);
}
