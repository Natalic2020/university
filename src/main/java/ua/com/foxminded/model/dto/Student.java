package ua.com.foxminded.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import ua.com.foxminded.enums.StudyStatus;

public class Student extends Person{
    
    private StudyStatus studyStatus;
    private LocalDate startOfStudy;
    private Locale citizenship;
    private BigDecimal grant;
    
    public Student() {
       
    }

    public Student(Student student) {
        super();
        this.studyStatus = student.studyStatus;
        this.startOfStudy = student.startOfStudy;
        this.citizenship = student.citizenship;
        this.grant = student.grant;
    }

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }

    public Student setStudyStatus(StudyStatus studyStatus) {
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

    public Locale getCitizenship() {
        return citizenship;
    }

    public Student setCitizenship(Locale citizenship) {
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
        int result = super.hashCode();
        result = prime * result + ((citizenship == null) ? 0 : citizenship.hashCode());
        result = prime * result + ((grant == null) ? 0 : grant.hashCode());
        result = prime * result + ((startOfStudy == null) ? 0 : startOfStudy.hashCode());
        result = prime * result + ((studyStatus == null) ? 0 : studyStatus.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
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
        if (startOfStudy == null) {
            if (other.startOfStudy != null)
                return false;
        } else if (!startOfStudy.equals(other.startOfStudy))
            return false;
        if (studyStatus != other.studyStatus)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Student [studyStatus=" + studyStatus + ", startOfStudy=" + startOfStudy + ", citizenship=" + citizenship
                + ", grant=" + grant + "]";
    }
}
