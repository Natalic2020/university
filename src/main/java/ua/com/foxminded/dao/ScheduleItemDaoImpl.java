package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.ScheduleItem;

@Repository
@Qualifier("scheduleItemDao")
public class ScheduleItemDaoImpl implements ScheduleItemDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addScheduleItem(ScheduleItem scheduleItem) {
        // TODO Auto-generated method stub

    }

    @Override
    public void editScheduleItem(ScheduleItem scheduleItem, UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteScheduleItem(UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public ScheduleItem findScheduleItem(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ScheduleItem> findAllScheduleItem() {
        // TODO Auto-generated method stub
        return null;
    }

}
