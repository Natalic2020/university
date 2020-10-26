package ua.com.foxminded.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.converter.ScheduleItemConverter;
import ua.com.foxminded.dao.interfaces.ScheduleItemDao;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.service.interfaces.ScheduleService;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    ScheduleItemDao scheduleItemDao;

    @Autowired
    ScheduleItemConverter scheduleItemConverter;
    
    public ScheduleServiceImpl(ScheduleItemDao scheduleItemDao) {
        this.scheduleItemDao = scheduleItemDao;
        this.scheduleItemConverter = new ScheduleItemConverter(); 
    }

    @Override
    public List<ScheduleItemDto> findWeekScheduleStudent(UUID id) {
        return  scheduleItemDao.findWeekScheduleStudent(id.toString()).stream()
                .map(scheduleItem -> scheduleItemConverter.convertEntityToDto(scheduleItem))
                .collect(Collectors.toList()); 
    }

    @Override
    public Map<String, List<ScheduleItemDto>> findMonthScheduleStudent(UUID id, LocalDate date) {
        List<ScheduleItemDto> ScheduleItems = findWeekScheduleStudent(id);
        LocalDate dateStart = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate dateFinish = dateStart.plusMonths(1);
        
        Map<String, List<ScheduleItemDto>> scheduleMonthStudent = new LinkedHashMap<>();
        for (LocalDate dateN = dateStart; dateN.isBefore(dateFinish); dateN = dateN.plusDays(1)) {
            String dayOfWeek = dateN.getDayOfWeek().toString();
            List<ScheduleItemDto> scheduleDayOfWeek = ScheduleItems.stream()
                    .filter(e -> e.getDayOfWeek().toString().equals(dayOfWeek))
                    .collect(Collectors.toList());
            scheduleMonthStudent.put(dateN.toString(), scheduleDayOfWeek);   
        }
        return scheduleMonthStudent;
    }

    @Override
    public List<ScheduleItemDto> findWeekScheduleTeacher(UUID id) {
        return scheduleItemDao.findWeekScheduleTeacher(id.toString()).stream()
                .map(scheduleItem -> scheduleItemConverter.convertEntityToDto(scheduleItem))
                .collect(Collectors.toList()); 
    }

    @Override
    public Map<String, List<ScheduleItemDto>> findMonthScheduleTeacher(UUID id, LocalDate date) {
        List<ScheduleItemDto> ScheduleItems = findWeekScheduleTeacher(id);
        LocalDate dateStart = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate dateFinish = dateStart.plusMonths(1);
        
        Map<String, List<ScheduleItemDto>> scheduleMonthTeacher = new LinkedHashMap<>();
        for (LocalDate dateN = dateStart; dateN.isBefore(dateFinish); dateN = dateN.plusDays(1)) {
            String dayOfWeek = dateN.getDayOfWeek().toString();
            List<ScheduleItemDto> scheduleDayOfWeek = ScheduleItems.stream()
                    .filter(e -> e.getDayOfWeek().toString().equals(dayOfWeek))
                    .collect(Collectors.toList());
            if (scheduleDayOfWeek.size() > 0) {
            scheduleMonthTeacher.put(dateN.toString() , scheduleDayOfWeek);
            }
        }
        return scheduleMonthTeacher;
    }   
}
