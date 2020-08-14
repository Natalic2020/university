package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.TimeSlot;

@Repository
@Qualifier("timeSlotDao")
public class TimeSlotDaoImpl implements TimeSlotDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addTimeSlot(TimeSlot timeSlot) {
        // TODO Auto-generated method stub

    }

    @Override
    public void editTimeSlot(TimeSlot timeSlot, UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTimeSlot(UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public TimeSlot findTimeSlot(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TimeSlot> findAllTimeSlot() {
        // TODO Auto-generated method stub
        return null;
    }

}
