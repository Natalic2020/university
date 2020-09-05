package ua.com.foxminded.dao.entity;

public class Schedule {
    
    private ScheduleItemTeacher scheduleItemTeacher;
    private Period period;

    public Schedule() {
       
    }

    public ScheduleItemTeacher getScheduleItemTeacher() {
        return scheduleItemTeacher;
    }

    public Schedule setScheduleItemTeacher(ScheduleItemTeacher scheduleItemTeacher) {
        this.scheduleItemTeacher = scheduleItemTeacher;
        return this;
    }

    public Period getPeriod() {
        return period;
    }

    public Schedule setPeriod(Period period) {
        this.period = period;
        return this;
    }
}
