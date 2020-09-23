package ua.com.foxminded.service.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.WeekScheduleDto;

public interface ScheduleService {
    public void addSchedule(WeekScheduleDto weekScheduleDto);
    public void editSchedule(ScheduleItemDto scheduleItemDto, UUID id);
    public void deleteSchedule(UUID id);
    public WeekScheduleDto findWeekScheduleStudent(String lastName, LocalDate date);
    public WeekScheduleDto findMonthScheduleStudent(String lastName, LocalDate date);
    public WeekScheduleDto findWeekScheduleTeacher(String lastName, LocalDate date);
    public WeekScheduleDto findMonthScheduleTeacher(String lastName, LocalDate date);
}
