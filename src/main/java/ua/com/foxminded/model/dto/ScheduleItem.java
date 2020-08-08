package ua.com.foxminded.model.dto;

import java.util.UUID;

import ua.com.foxminded.model.enums.DayOfWeek;

public class ScheduleItem {
    
    private UUID id;
    private Teacher teacher;
    private Group group;
    private Subject subject;
    private DayOfWeek dayOWeek;
    private TimeSlot timeSlot;
    private Room room;
    
    public ScheduleItem() {
        
    }

    public ScheduleItem(ScheduleItem scheduleItem) {
        this.teacher = new Teacher(scheduleItem.getTeacher())  ;
        this.group = new Group(scheduleItem.getGroup()) ;
        this.subject = new Subject(scheduleItem.getSubject());
        this.dayOWeek = scheduleItem.dayOWeek;
        this.timeSlot = new TimeSlot(scheduleItem.getTimeSlot());
        this.room = new Room(scheduleItem.getRoom());
    }

    public UUID getId() {
        return id;
    }

    public ScheduleItem setId(UUID id) {
        this.id = id;
        return this;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ScheduleItem setTeacher(Teacher teacher) {
        this.teacher = teacher;
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

    public DayOfWeek getDayOWeek() {
        return dayOWeek;
    }

    public ScheduleItem setDayOWeek(DayOfWeek dayOWeek) {
        this.dayOWeek = dayOWeek;
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
        result = prime * result + ((dayOWeek == null) ? 0 : dayOWeek.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + ((room == null) ? 0 : room.hashCode());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
        if (dayOWeek != other.dayOWeek)
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
        if (teacher == null) {
            if (other.teacher != null)
                return false;
        } else if (!teacher.equals(other.teacher))
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
        return "ScheduleItem [id=" + id + ", teacher=" + teacher + ", group=" + group + ", subject=" + subject
                + ", dayOWeek=" + dayOWeek + ", timeSlot=" + timeSlot + ", room=" + room + "]";
    }   
}
