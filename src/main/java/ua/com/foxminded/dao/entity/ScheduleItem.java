package ua.com.foxminded.dao.entity;

public class ScheduleItem {
    
    private String id;
    private Group group;
    private Subject subject;
    private String dayOfWeek;
    private TimeSlot timeSlot;
    private Room room;
    
    public ScheduleItem() {
        
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
}
