package ua.com.foxminded.dao.entity;

public class ScheduleItemTeacher {
    
    private String id;
    private ScheduleItem scheduleItem;
    private Teacher teacher;
    
    public ScheduleItemTeacher() {
       
    }

    public String getId() {
        return id;
    }

    public ScheduleItemTeacher setId(String id) {
        this.id = id;
        return this;
    }

    public ScheduleItem getScheduleItem() {
        return scheduleItem;
    }

    public ScheduleItemTeacher setScheduleItem(ScheduleItem scheduleItem) {
        this.scheduleItem = scheduleItem;
        return this;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ScheduleItemTeacher setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }
}
