package ua.com.foxminded.dao.entity;

import java.util.List;
import java.util.stream.Collectors;

public class Schedule {
    
    private String idScheduleItemTeacher;
    private String idPeriod;

    public Schedule() {
       
    }

    public Schedule(Schedule schedule) {
        this.idScheduleItemTeacher = schedule.idScheduleItemTeacher;
        this.idPeriod = schedule.idPeriod;
    }

    public String getIdScheduleItemTeacher() {
        return idScheduleItemTeacher;
    }

    public Schedule setIdScheduleItemTeacher(String idScheduleItemTeacher) {
        this.idScheduleItemTeacher = idScheduleItemTeacher;
        return this;
    }

    public String getIdPeriod() {
        return idPeriod;
    }

    public Schedule setIdPeriod(String idPeriod) {
        this.idPeriod = idPeriod;
        return this;
    }
}
