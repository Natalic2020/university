package ua.com.foxminded.dao.entity;

import java.time.LocalDate;

public class Period {

    private String id;
    private LocalDate startDate;
    private LocalDate finishDate;
    
    public Period() {
       
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
}
