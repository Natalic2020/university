package ua.com.foxminded.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import ua.com.foxminded.model.enums.Degree;
import ua.com.foxminded.model.enums.Department;

public class TeacherDto extends PersonDto{
    
    private Degree degree;
    private Department departament;
    private boolean isPermanent;
    private BigDecimal salary;
   
    public TeacherDto() {
     
    }

    public TeacherDto(TeacherDto teacher) {
        this.degree = teacher.degree;
        this.departament = teacher.departament;
        this.isPermanent = teacher.isPermanent;
        this.salary = BigDecimal.valueOf(teacher.getSalary().longValue());
    }

    public UUID getId() {
        return id;
    }

    public TeacherDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public TeacherDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public TeacherDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactInfoDto getContactInfo() {
        return contactInfo;
    }

    public TeacherDto setContactInfo(ContactInfoDto contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }
    
    public Degree getDegree() {
        return degree;
    }

    public TeacherDto setDegree(Degree degree) {
        this.degree = degree;
        return this;
    }

    public Department getDepartament() {
        return departament;
    }

    public TeacherDto setDepartament(Department departament) {
        this.departament = departament;
        return this;
    }

    public boolean isPermanent() {
        return isPermanent;
    }

    public TeacherDto setPermanent(boolean isPermanent) {
        this.isPermanent = isPermanent;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public TeacherDto setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((degree == null) ? 0 : degree.hashCode());
        result = prime * result + ((departament == null) ? 0 : departament.hashCode());
        result = prime * result + (isPermanent ? 1231 : 1237);
        result = prime * result + ((salary == null) ? 0 : salary.hashCode());
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
        TeacherDto other = (TeacherDto) obj;
        if (degree != other.degree)
            return false;
        if (departament != other.departament)
            return false;
        if (isPermanent != other.isPermanent)
            return false;
        if (salary == null) {
            if (other.salary != null)
                return false;
        } else if (!salary.equals(other.salary))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Teacher [degree=" + degree + ", departament=" + departament + ", isPermanent=" + isPermanent
                + ", salary=" + salary + "]";
    }
}
