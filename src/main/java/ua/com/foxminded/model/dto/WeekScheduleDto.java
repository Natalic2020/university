package ua.com.foxminded.model.dto;

import java.util.List;
import java.util.stream.Collectors;

public class WeekScheduleDto {
    
    private List<ScheduleItemDto> schedules;

    public WeekScheduleDto() {
       
    }

    public WeekScheduleDto(WeekScheduleDto weekSchedule) {
        this.schedules = weekSchedule.getSchedules().stream().collect(Collectors.toList());
       
    }

    public List<ScheduleItemDto> getSchedules() {
        return schedules;
    }

    public WeekScheduleDto setSchedules(List<ScheduleItemDto> schedules) {
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
        WeekScheduleDto other = (WeekScheduleDto) obj;
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
