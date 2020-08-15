package ua.com.foxminded.dao.entity;

import java.util.UUID;

import ua.com.foxminded.model.enums.DayOfWeek;

public class ScheduleItem {
    
    private String id;
    private String idTeacher;
    private String idGroup;
    private String idSubject;
    private String idDayOWeek;
    private String idTimeSlot;
    private String idRoom;
    
    public ScheduleItem() {
        
    }

    public ScheduleItem(ScheduleItem scheduleItem) {
        this.idTeacher = scheduleItem.idTeacher  ;
        this.idGroup = scheduleItem.idGroup ;
        this.idSubject = scheduleItem.idSubject;
        this.idDayOWeek = scheduleItem.idDayOWeek;
        this.idTimeSlot = scheduleItem.idTimeSlot;
        this.idRoom = scheduleItem.idRoom;
    }

    public String getId() {
        return id;
    }

    public ScheduleItem setId(String id) {
        this.id = id;
        return this;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public ScheduleItem setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
        return this;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public ScheduleItem setIdGroup(String idGroup) {
        this.idGroup = idGroup;
        return this;
    }

    public String getIdSubject() {
        return idSubject;
    }

    public ScheduleItem setIdSubject(String idSubject) {
        this.idSubject = idSubject;
        return this;
    }

    public String getIdDayOWeek() {
        return idDayOWeek;
    }

    public ScheduleItem setIdDayOWeek(String idDayOWeek) {
        this.idDayOWeek = idDayOWeek;
        return this;
    }

    public String getIdTimeSlot() {
        return idTimeSlot;
    }

    public ScheduleItem setIdTimeSlot(String idTimeSlot) {
        this.idTimeSlot = idTimeSlot;
        return this;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public ScheduleItem setIdRoom(String idRoom) {
        this.idRoom = idRoom;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idDayOWeek == null) ? 0 : idDayOWeek.hashCode());
        result = prime * result + ((idGroup == null) ? 0 : idGroup.hashCode());
        result = prime * result + ((idRoom == null) ? 0 : idRoom.hashCode());
        result = prime * result + ((idSubject == null) ? 0 : idSubject.hashCode());
        result = prime * result + ((idTeacher == null) ? 0 : idTeacher.hashCode());
        result = prime * result + ((idTimeSlot == null) ? 0 : idTimeSlot.hashCode());
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
        if (idDayOWeek == null) {
            if (other.idDayOWeek != null)
                return false;
        } else if (!idDayOWeek.equals(other.idDayOWeek))
            return false;
        if (idGroup == null) {
            if (other.idGroup != null)
                return false;
        } else if (!idGroup.equals(other.idGroup))
            return false;
        if (idRoom == null) {
            if (other.idRoom != null)
                return false;
        } else if (!idRoom.equals(other.idRoom))
            return false;
        if (idSubject == null) {
            if (other.idSubject != null)
                return false;
        } else if (!idSubject.equals(other.idSubject))
            return false;
        if (idTeacher == null) {
            if (other.idTeacher != null)
                return false;
        } else if (!idTeacher.equals(other.idTeacher))
            return false;
        if (idTimeSlot == null) {
            if (other.idTimeSlot != null)
                return false;
        } else if (!idTimeSlot.equals(other.idTimeSlot))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ScheduleItem [id=" + id + ", idTeacher=" + idTeacher + ", idGroup=" + idGroup + ", idSubject="
                + idSubject + ", idDayOWeek=" + idDayOWeek + ", idTimeSlot=" + idTimeSlot + ", idRoom=" + idRoom + "]";
    }     
}
