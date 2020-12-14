package ua.com.foxminded.service.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ua.com.foxminded.model.dto.ScheduleItemDto;

public interface ScheduleItemService {
    public void addScheduleItem(ScheduleItemDto scheduleItemDto);
    public void editScheduleItem(ScheduleItemDto scheduleItemDto);
    public void deleteScheduleItem(UUID id);
    public List<ScheduleItemDto> findWeekScheduleStudent(UUID id);
    public Map<String, List<ScheduleItemDto>> findMonthScheduleStudent(UUID id, LocalDate date);
    public List<ScheduleItemDto> findWeekScheduleTeacher(UUID id);
    public Map<String, List<ScheduleItemDto>> findMonthScheduleTeacher(UUID id, LocalDate date);
    public List<ScheduleItemDto> findDayScheduleStudent(UUID fromString, LocalDate date);
    public List<ScheduleItemDto> findDayScheduleTeacher(UUID fromString, LocalDate date);
}
