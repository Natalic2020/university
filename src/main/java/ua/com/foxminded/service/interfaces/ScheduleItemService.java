package ua.com.foxminded.service.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.ScheduleItemDto;

public interface ScheduleItemService {
    public void addScheduleItem(ScheduleItemDto scheduleItemDto);
    public void editScheduleItem(ScheduleItemDto scheduleItemDto);
    public void deleteScheduleItem(UUID id);
    public List<ScheduleItemDto> findWeekScheduleStudent(String lastName, LocalDate date);
    public List<ScheduleItemDto> findWeekScheduleTeacher(String lastName, LocalDate date);
}
