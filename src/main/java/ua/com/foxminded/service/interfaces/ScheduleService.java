package ua.com.foxminded.service.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.WeekScheduleDto;

public interface ScheduleService {
    public List<ScheduleItemDto> findWeekScheduleStudent(UUID id);
    public Map<String, List<ScheduleItemDto>> findMonthScheduleStudent(UUID id, LocalDate date);
    public List<ScheduleItemDto> findWeekScheduleTeacher(UUID id);
    public List<String> findMonthScheduleTeacher(UUID id, LocalDate date);
}
