package ua.com.foxminded.dao.entity;

import java.time.LocalTime;

public class TimeSlot {
    
    private String id;
    private int serialNumber;
    private LocalTime startTime;
    private LocalTime finishTime;
    
    public TimeSlot() {
    
    }

    public String getId() {
        return id;
    }

    public TimeSlot setId(String id) {
        this.id = id;
        return this;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public TimeSlot setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public TimeSlot setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public TimeSlot setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
        return this;
    }
}
