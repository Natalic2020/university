package ua.com.foxminded.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.converter.ScheduleItemConverter;
import ua.com.foxminded.dao.interfaces.ScheduleItemDao;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.service.interfaces.ScheduleItemService;

@Service("scheduleItemService")
public class ScheduleItemServiceImpl implements ScheduleItemService {

    @Autowired
    ScheduleItemDao scheduleItemDao;

    @Autowired
    ScheduleItemConverter scheduleItemConverter;

    @Override
    public void addScheduleItem(ScheduleItemDto scheduleItemDto) {
        scheduleItemDao
                       .addScheduleItem(scheduleItemConverter
                                                             .convertDtoToEntity(scheduleItemDto));
    }

    @Override
    public void editScheduleItem(ScheduleItemDto scheduleItemDto) {
        scheduleItemDao
                       .editScheduleItem(scheduleItemConverter
                                                              .convertDtoToEntity(scheduleItemDto));

    }

    @Override
    public void deleteScheduleItem(UUID id) {
        scheduleItemDao.deleteScheduleItem(id.toString());
    }

    @Override
    public List<ScheduleItemDto> findWeekScheduleStudent(String lastName, LocalDate date) {
        return scheduleItemDao
                              .findScheduleStudent(lastName)
                              .stream()
                              .map(scheduleItem -> scheduleItemConverter.convertEntityToDto(scheduleItem))
                              .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleItemDto> findWeekScheduleTeacher(String lastName, LocalDate date) {
        return scheduleItemDao
                              .findScheduleTeacher(lastName)
                              .stream()
                              .map(scheduleItem -> scheduleItemConverter.convertEntityToDto(scheduleItem))
                              .collect(Collectors.toList());
    }
}
