package ua.com.foxminded.dao.entity;

import java.util.List;
import java.util.stream.Collectors;

public class Schedule {
    
    private  ScheduleItemTeacher scheduleItemTeacher;
    private Period period;

    public Schedule() {
       
    }

    public Schedule(Schedule schedule) {
        this.scheduleItemTeacher = schedule.scheduleItemTeacher;
        this.period = schedule.period;
    }

    public ScheduleItemTeacher getScheduleItemTeacher() {
        return scheduleItemTeacher;
    }

    public void setScheduleItemTeacher(ScheduleItemTeacher scheduleItemTeacher) {
        this.scheduleItemTeacher = scheduleItemTeacher;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((period == null) ? 0 : period.hashCode());
        result = prime * result + ((scheduleItemTeacher == null) ? 0 : scheduleItemTeacher.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Schedule other = (Schedule) obj;
        if (period == null) {
            if (other.period != null)
                return false;
        } else if (!period.equals(other.period))
            return false;
        if (scheduleItemTeacher == null) {
            if (other.scheduleItemTeacher != null)
                return false;
        } else if (!scheduleItemTeacher.equals(other.scheduleItemTeacher))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Schedule [scheduleItemTeacher=" + scheduleItemTeacher + ", period=" + period + "]";
    }   
}
