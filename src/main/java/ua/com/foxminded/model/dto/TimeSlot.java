package ua.com.foxminded.model.dto;

import java.time.LocalTime;

public class TimeSlot {
    
    private int serialNumber;
    private LocalTime startTime;
    private LocalTime finishTime;
    
    public TimeSlot() {
    
    }

    public TimeSlot(TimeSlot timeSlot) {
        this.serialNumber = timeSlot.serialNumber;
        this.startTime = timeSlot.startTime;
        this.finishTime = timeSlot.finishTime;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((finishTime == null) ? 0 : finishTime.hashCode());
        result = prime * result + serialNumber;
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
        TimeSlot other = (TimeSlot) obj;
        if (finishTime == null) {
            if (other.finishTime != null)
                return false;
        } else if (!finishTime.equals(other.finishTime))
            return false;
        if (serialNumber != other.serialNumber)
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TimeSlot [serialNumber=" + serialNumber + ", startTime=" + startTime + ", finishTime=" + finishTime
                + "]";
    }
}
