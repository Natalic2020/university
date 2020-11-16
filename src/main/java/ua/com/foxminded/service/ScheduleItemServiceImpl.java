package ua.com.foxminded.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.com.foxminded.converter.ScheduleItemConverter;
import ua.com.foxminded.dao.interfaces.ScheduleItemDao;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.service.interfaces.ScheduleItemService;

@Service("scheduleItemService")
public class ScheduleItemServiceImpl implements ScheduleItemService {

    @Autowired
    @Qualifier("scheduleItemDao")
    ScheduleItemDao scheduleItemDao;

    @Autowired
    ScheduleItemConverter scheduleItemConverter;

    @Override
    public void addScheduleItem(ScheduleItemDto scheduleItemDto) {
        scheduleItemDao.save(scheduleItemConverter.convertDtoToEntity(scheduleItemDto));
    }

    @Override
    public void editScheduleItem(ScheduleItemDto scheduleItemDto) {
        scheduleItemDao.save(scheduleItemConverter.convertDtoToEntity(scheduleItemDto));
    }

    @Override
    public void deleteScheduleItem(UUID id) {
        scheduleItemDao.deleteById(id.toString());
    }
}
