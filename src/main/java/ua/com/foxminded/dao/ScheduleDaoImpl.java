package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.WeekScheduleDto;

@Repository
@Qualifier("weekScheduleDao")
public class ScheduleDaoImpl implements ScheduleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addWeekSchedule(WeekScheduleDto weekSchedule) {
        // TODO Auto-generated method stub

    }

    @Override
    public void editWeekSchedule(WeekScheduleDto weekSchedule, UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteWeekSchedule(UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public WeekScheduleDto findWeekSchedule(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<WeekScheduleDto> findAllWeekSchedule() {
        // TODO Auto-generated method stub
        return null;
    }

}
