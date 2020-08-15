package ua.com.foxminded.dao.entity;

public class ScheduleItemTeacher {
    
    private String id;
    private String idScheduleItem;
    private String idTeacher;
    
    public ScheduleItemTeacher() {
       
    }

    public ScheduleItemTeacher(ScheduleItemTeacher scheduleItemTeacher) {
        this.id = scheduleItemTeacher.id;
        this.idScheduleItem = scheduleItemTeacher.idScheduleItem;
        this.idTeacher = scheduleItemTeacher.idTeacher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdScheduleItem() {
        return idScheduleItem;
    }

    public void setIdScheduleItem(String idScheduleItem) {
        this.idScheduleItem = idScheduleItem;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idScheduleItem == null) ? 0 : idScheduleItem.hashCode());
        result = prime * result + ((idTeacher == null) ? 0 : idTeacher.hashCode());
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
        ScheduleItemTeacher other = (ScheduleItemTeacher) obj;
        if (idScheduleItem == null) {
            if (other.idScheduleItem != null)
                return false;
        } else if (!idScheduleItem.equals(other.idScheduleItem))
            return false;
        if (idTeacher == null) {
            if (other.idTeacher != null)
                return false;
        } else if (!idTeacher.equals(other.idTeacher))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ScheduleItemTeacher [id=" + id + ", idScheduleItem=" + idScheduleItem + ", idTeacher=" + idTeacher
                + "]";
    }
}
