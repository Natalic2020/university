package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.StudentDto;

public class ScheduleItemServiceImpl implements ScheduleItemService {

    @Override
    public void addScheduleItem(ScheduleItemDto scheduleItemDto) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void editScheduleItemt(ScheduleItemDto scheduleItemDto, UUID id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteScheduleItem(UUID id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<ScheduleItem> findScheduleStudent(UUID id, String startDate, String finishDate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ScheduleItem> findScheduleTeacher(UUID id, String startDate, String finishDate) {
        // TODO Auto-generated method stub
        return null;
    }
}
