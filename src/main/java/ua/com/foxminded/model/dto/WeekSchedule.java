package ua.com.foxminded.model.dto;

import java.util.List;
import java.util.stream.Collectors;

public class WeekSchedule {
    
    private List<ScheduleItem> schedules;

    public WeekSchedule() {
       
    }

    public WeekSchedule(WeekSchedule weekSchedule) {
        this.schedules = weekSchedule.getSchedules().stream().collect(Collectors.toList());
       
    }

    public List<ScheduleItem> getSchedules() {
        return schedules;
    }

    public WeekSchedule setSchedules(List<ScheduleItem> schedules) {
        this.schedules = schedules;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((schedules == null) ? 0 : schedules.hashCode());
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
        WeekSchedule other = (WeekSchedule) obj;
        if (schedules == null) {
            if (other.schedules != null)
                return false;
        } else if (!schedules.equals(other.schedules))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "WeekSchedule [schedules=" + schedules + "]";
    } 
}
