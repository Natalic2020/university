package ua.com.foxminded.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.converter.ScheduleConverter;
import ua.com.foxminded.converter.StudentConverter;
import ua.com.foxminded.dao.ScheduleDao;
import ua.com.foxminded.dao.StudentDao;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.WeekScheduleDto;

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
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteSchedule(UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ScheduleItemDto> findScheduleStudent(String lastName, LocalDate date) {
        return scheduleDao.findScheduleStudent(lastName, date).stream().map(ScheduleConverter::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleItemDto> findScheduleTeacher(String lastName, LocalDate date) {
        return scheduleDao.findScheduleTeacher(lastName, date).stream().map(ScheduleConverter::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void fillTable() {
        scheduleDao.fillTable();   
    }
}
