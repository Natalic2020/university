package ua.com.foxminded.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.converter.ScheduleConverter;
import ua.com.foxminded.converter.StudentConverter;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.interfaces.ScheduleDao;
import ua.com.foxminded.dao.interfaces.StudentDao;
import ua.com.foxminded.model.dto.PeriodDto;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.WeekScheduleDto;
import ua.com.foxminded.service.interfaces.ScheduleService;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleDao scheduleDao;

    @Autowired
    ScheduleConverter scheduleConverterr;

    @Override
    public void addSchedule(WeekScheduleDto weekScheduleDto) {
        scheduleDao.addSchedule(ScheduleConverter.convertDtoToEntity(weekScheduleDto));
    }

    @Override
    public void editSchedule(ScheduleItemDto scheduleItemDto, UUID id) {
    }

    @Override
    public void deleteSchedule(UUID id) {
    }

    @Override
    public WeekScheduleDto findWeekScheduleStudent(String lastName, LocalDate date) {
        LocalDate dateStart = date.with(DayOfWeek.MONDAY);
        LocalDate dateFinish = dateStart.plusWeeks(1);
        return findScheduleStudents(lastName,  dateStart, dateFinish);
    }

    @Override
    public WeekScheduleDto findMonthScheduleStudent(String lastName, LocalDate date) {
        LocalDate dateStart = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate dateFinish = dateStart.plusMonths(1);
        return findScheduleStudents(lastName,  dateStart, dateFinish);
    }
    
    private WeekScheduleDto findScheduleStudents(String lastName,LocalDate dateStart,
            LocalDate dateFinish) {
        List<WeekScheduleDto> schedule = new ArrayList<>();
//        for (LocalDate dateN = dateStart; dateN.isBefore(dateFinish); dateN = dateN.plusDays(1)) {
        WeekScheduleDto weekScheduleDto = ScheduleConverter.convertEntityToDto(scheduleDao.findScheduleStudent(lastName, dateStart));
        
//            if (scheduleDto.size() > 0) {
//                schedule.add(new WeekScheduleDto().setSchedules(scheduleDto)
//                                                  .setPeriod(new PeriodDto().setStartDate(dateN)));
//            }
//        }
        return weekScheduleDto;
    }   

    @Override
    public WeekScheduleDto findWeekScheduleTeacher(String lastName, LocalDate date) {
        LocalDate dateStart = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate dateFinish = dateStart.plusWeeks(1);
        return findScheduleTeacher(lastName,  dateStart, dateFinish);
    }

    @Override
    public WeekScheduleDto findMonthScheduleTeacher(String lastName, LocalDate date) {
        LocalDate dateStart = date.with(DayOfWeek.MONDAY);
        LocalDate dateFinish = dateStart.plusMonths(1);
        return findScheduleTeacher(lastName,  dateStart, dateFinish);
    }

    
    private WeekScheduleDto findScheduleTeacher(String lastName,LocalDate dateStart,
            LocalDate dateFinish) {
        List<WeekScheduleDto> schedule = new ArrayList<>();
        for (LocalDate dateN = dateStart; dateN.isBefore(dateFinish); dateN = dateN.plusDays(1)) {
            List<ScheduleItemDto> scheduleDto = scheduleDao.findScheduleTeacher(lastName, dateN)
                                                           .stream()
                                                           .map(ScheduleConverter::convertEntityToDto)
                                                           .collect(Collectors.toList());
            if (scheduleDto.size() > 0) {
                schedule.add(new WeekScheduleDto().setSchedules(scheduleDto)
                                                  .setPeriod(new PeriodDto().setStartDate(dateN)));
            }
        }
        return schedule;
    }   
}
