package ua.com.foxminded.dao.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.UUID;

import ua.com.foxminded.model.enums.StudyStatus;

public class Student {
    
    private String id;
    private Person Person;
    private String idGroup;
    private String studyStatus;
    private LocalDate startOfStudy;
    private String citizenship;
    private BigDecimal grant;
    
    public Student() {
       
    }
    
    public Student(Student student) {
        this.studyStatus = student.studyStatus;
        this.startOfStudy = LocalDate.of(student.getStartOfStudy().getYear(), student.getStartOfStudy().getMonth(), student.getStartOfStudy().getDayOfMonth());
        this.citizenship = student.citizenship;
        this.grant = BigDecimal.valueOf(student.getGrant().longValue());
    }
    
    public Person getPerson() {
        return Person;
    }

    public Student setPerson(Person person) {
        Person = person;
        return this;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public Student setIdGroup(String idGroup) {
        this.idGroup = idGroup;
        return this;
    }

    public String getId() {
        return id;
    }

    public Student setId(String id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Person == null) ? 0 : Person.hashCode());
        result = prime * result + ((citizenship == null) ? 0 : citizenship.hashCode());
        result = prime * result + ((grant == null) ? 0 : grant.hashCode());
        result = prime * result + ((idGroup == null) ? 0 : idGroup.hashCode());
        result = prime * result + ((startOfStudy == null) ? 0 : startOfStudy.hashCode());
        result = prime * result + ((studyStatus == null) ? 0 : studyStatus.hashCode());
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
        Student other = (Student) obj;
        if (Person == null) {
            if (other.Person != null)
                return false;
        } else if (!Person.equals(other.Person))
            return false;
        if (citizenship == null) {
            if (other.citizenship != null)
                return false;
        } else if (!citizenship.equals(other.citizenship))
            return false;
        if (grant == null) {
            if (other.grant != null)
                return false;
        } else if (!grant.equals(other.grant))
            return false;
        if (idGroup == null) {
            if (other.idGroup != null)
                return false;
        } else if (!idGroup.equals(other.idGroup))
            return false;
        if (startOfStudy == null) {
            if (other.startOfStudy != null)
                return false;
        } else if (!startOfStudy.equals(other.startOfStudy))
            return false;
        if (studyStatus == null) {
            if (other.studyStatus != null)
                return false;
        } else if (!studyStatus.equals(other.studyStatus))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", Person=" + Person + ", idGroup=" + idGroup + ", studyStatus=" + studyStatus
                + ", startOfStudy=" + startOfStudy + ", citizenship=" + citizenship + ", grant=" + grant + "]";
    }
}
