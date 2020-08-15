package ua.com.foxminded.dao.entity;

import java.time.LocalDate;

public class Period {

    private String id;
    private LocalDate startDate;
    private LocalDate finishDate;
    
    public Period() {
       
    }

    public Period(Period period) {
        this.id = period.id;
        this.startDate = period.startDate;
        this.finishDate = period.finishDate;
    }

    public String getId() {
        return id;
    }

    public Period setId(String id) {
        this.id = id;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Period setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public Period setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((finishDate == null) ? 0 : finishDate.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
        Period other = (Period) obj;
        if (finishDate == null) {
            if (other.finishDate != null)
                return false;
        } else if (!finishDate.equals(other.finishDate))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Period [id=" + id + ", startDate=" + startDate + ", finishDate=" + finishDate + "]";
    }   
}
