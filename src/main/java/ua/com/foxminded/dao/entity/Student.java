package ua.com.foxminded.dao.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import ua.com.foxminded.model.dto.PersonDto;

public class Student extends Person{
    
    private String idStudent;
//    private Person person;
    private Group group;
    private String studyStatus;
    private LocalDate startOfStudy;
    private String citizenship;
    private BigDecimal grant;
    
    public Student() {
       
    }

    public String getIdStudent() {
        return idStudent;
    }

    public Student setIdStudent(String idStudent) {
        this.idStudent = idStudent;
        return this;
    }

//    public Person getPerson() {
//        return person;
//    }
//
//    public Student setPerson(Person person) {
//        this.person = person;
//        return this;
//    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((citizenship == null) ? 0 : citizenship.hashCode());
        result = prime * result + ((grant == null) ? 0 : grant.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + ((idStudent == null) ? 0 : idStudent.hashCode());
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
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        if (idStudent == null) {
            if (other.idStudent != null)
                return false;
        } else if (!idStudent.equals(other.idStudent))
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
    
}
