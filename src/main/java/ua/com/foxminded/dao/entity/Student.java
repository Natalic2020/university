package ua.com.foxminded.dao.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Student {
    
    private String id;
    private Person person;
    private Group group;
    private String studyStatus;
    private LocalDate startOfStudy;
    private String citizenship;
    private BigDecimal grant;
    
    public Student() {
       
    }

    public String getId() {
        return id;
    }

    public Student setId(String id) {
        this.id = id;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public Student setPerson(Person person) {
        this.person = person;
        return this;
    }

    public Group getGroup() {
        return group;
    }

    public Student setGroup(Group group) {
        this.group = group;
        return this;
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    public Student setStudyStatus(String studyStatus) {
        this.studyStatus = studyStatus;
        return this;
    }

    public LocalDate getStartOfStudy() {
        return startOfStudy;
    }

    public Student setStartOfStudy(LocalDate startOfStudy) {
        this.startOfStudy = startOfStudy;
        return this;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public Student setCitizenship(String citizenship) {
        this.citizenship = citizenship;
        return this;
    }

    public BigDecimal getGrant() {
        return grant;
    }

    public Student setGrant(BigDecimal grant) {
        this.grant = grant;
        return this;
    }
}
