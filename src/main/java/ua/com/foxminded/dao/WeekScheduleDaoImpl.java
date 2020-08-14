package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.WeekSchedule;

@Repository
@Qualifier("weekScheduleDao")
public class WeekScheduleDaoImpl implements WeekScheduleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addWeekSchedule(WeekSchedule weekSchedule) {
        // TODO Auto-generated method stub

    }

    @Override
    public void editWeekSchedule(WeekSchedule weekSchedule, UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteWeekSchedule(UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public WeekSchedule findWeekSchedule(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<WeekSchedule> findAllWeekSchedule() {
        // TODO Auto-generated method stub
        return null;
    }

}
