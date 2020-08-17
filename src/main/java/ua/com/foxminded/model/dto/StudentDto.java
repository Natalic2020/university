package ua.com.foxminded.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import ua.com.foxminded.model.enums.StudyStatus;

public class StudentDto extends PersonDto {

    private StudyStatus studyStatus;
    private LocalDate startOfStudy;
    private String citizenship;
    private BigDecimal grant;

    public StudentDto() {

    }

    public StudentDto(StudentDto student) {
        this.studyStatus = student.studyStatus;
//        this.startOfStudy =  LocalDate.of(student.getStartOfStudy().getYear(), student.getStartOfStudy().getMonth(),
//                student.getStartOfStudy().getDayOfMonth()) ;
        this.citizenship = student.citizenship;
//        this.grant = BigDecimal.valueOf(student.getGrant().longValue());
    }

    public UUID getId() {
        return id;
    }

    public StudentDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public StudentDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactInfoDto getContactInfo() {
        return contactInfo;
    }

    public StudentDto setContactInfo(ContactInfoDto contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }

    public StudentDto setStudyStatus(StudyStatus studyStatus) {
        this.studyStatus = studyStatus;
        return this;
    }

    public LocalDate getStartOfStudy() {
        return startOfStudy;
    }

    public StudentDto setStartOfStudy(LocalDate startOfStudy) {
        this.startOfStudy = startOfStudy;
        return this;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public StudentDto setCitizenship(String citizenship) {
        this.citizenship = citizenship;
        return this;
    }

    public BigDecimal getGrant() {
        return grant;
    }

    public StudentDto setGrant(BigDecimal grant) {
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
        StudentDto other = (StudentDto) obj;
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
        return "Student  [studyStatus=" + studyStatus + ", startOfStudy=" + startOfStudy + ", citizenship="
                + citizenship
                + ", grant=" + grant + "]" + super.toString();
    }
}