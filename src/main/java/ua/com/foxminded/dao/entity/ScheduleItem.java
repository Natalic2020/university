package ua.com.foxminded.dao.entity;

import java.util.UUID;

import ua.com.foxminded.model.enums.DayOfWeek;

public class ScheduleItem {
    
    private String id;
    private Group group;
    private Subject subject;
    private String dayOfWeek;
    private TimeSlot timeSlot;
    private Room room;
    
    public ScheduleItem() {
        
    }

    public ScheduleItem(ScheduleItem scheduleItem) {
        this.group = group;
        this.subject = subject;
        this.dayOfWeek = dayOfWeek;
        this.timeSlot = timeSlot;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public ScheduleItem setId(String id) {
        this.id = id;
        return this;
    }

    public Group getGroup() {
        return group;
    }

    public ScheduleItem setGroup(Group group) {
        this.group = group;
        return this;
    }

    public Subject getSubject() {
        return subject;
    }

    public ScheduleItem setSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public ScheduleItem setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public ScheduleItem setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public ScheduleItem setRoom(Room room) {
        this.room = room;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dayOfWeek == null) ? 0 : dayOfWeek.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + ((room == null) ? 0 : room.hashCode());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        result = prime * result + ((timeSlot == null) ? 0 : timeSlot.hashCode());
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
        ScheduleItem other = (ScheduleItem) obj;
        if (dayOfWeek != other.dayOfWeek)
            return false;
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        if (room == null) {
            if (other.room != null)
                return false;
        } else if (!room.equals(other.room))
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        if (timeSlot == null) {
            if (other.timeSlot != null)
                return false;
        } else if (!timeSlot.equals(other.timeSlot))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ScheduleItem [id=" + id + ", group=" + group + ", subject=" + subject + ", dayOfWeek=" + dayOfWeek
                + ", timeSlot=" + timeSlot + ", room=" + room + "]";
    }
}
