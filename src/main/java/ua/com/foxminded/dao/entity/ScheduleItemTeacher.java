package ua.com.foxminded.dao.entity;

public class ScheduleItemTeacher {
    
    private String id;
    private ScheduleItem scheduleItem;
    private Teacher teacher;
    
    public ScheduleItemTeacher() {
       
    }

    public ScheduleItemTeacher(ScheduleItemTeacher scheduleItemTeacher) {
        this.id = scheduleItemTeacher.id;
        this.scheduleItem = scheduleItemTeacher.scheduleItem;
        this.teacher = scheduleItemTeacher.teacher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ScheduleItem getScheduleItem() {
        return scheduleItem;
    }

    public void setScheduleItem(ScheduleItem scheduleItem) {
        this.scheduleItem = scheduleItem;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((scheduleItem == null) ? 0 : scheduleItem.hashCode());
        result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
        if (scheduleItem == null) {
            if (other.scheduleItem != null)
                return false;
        } else if (!scheduleItem.equals(other.scheduleItem))
            return false;
        if (teacher == null) {
            if (other.teacher != null)
                return false;
        } else if (!teacher.equals(other.teacher))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ScheduleItemTeacher [id=" + id + ", scheduleItem=" + scheduleItem + ", teacher=" + teacher + "]";
    }
}
