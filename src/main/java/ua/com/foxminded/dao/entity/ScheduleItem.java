package ua.com.foxminded.dao.entity;

import java.util.UUID;

import ua.com.foxminded.model.enums.DayOfWeek;

public class ScheduleItem {
    
    private String student;
    private String teacher;
    private String group;
    private String subject;
    private String dayOWeek;
    private String timeSlot;
    private String room;
    private String date;
   
    
    public ScheduleItem() {
        
    }

    public ScheduleItem(ScheduleItem scheduleItem) {
        this.student = student;
        this.teacher = teacher;
        this.group = group;
        this.subject = subject;
        this.dayOWeek = dayOWeek;
        this.timeSlot = timeSlot;
        this.room = room;
        this.date = date;
    }

    public String getStudent() {
        return student;
    }

    public ScheduleItem setStudent(String student) {
        this.student = student;
        return this;
    }

    public String getTeacher() {
        return teacher;
    }

    public ScheduleItem setTeacher(String teacher) {
        this.teacher = teacher;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public ScheduleItem setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public ScheduleItem setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDayOWeek() {
        return dayOWeek;
    }

    public ScheduleItem setDayOWeek(String dayOWeek) {
        this.dayOWeek = dayOWeek;
        return this;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public ScheduleItem setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
        return this;
    }

    public String getRoom() {
        return room;
    }

    public ScheduleItem setRoom(String room) {
        this.room = room;
        return this;
    }

    public String getDate() {
        return date;
    }

    public ScheduleItem setDate(String date) {
        this.date = date;
        return this;
    }
}
