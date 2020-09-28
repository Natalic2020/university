package ua.com.foxminded.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import ua.com.foxminded.model.enums.Degree;
import ua.com.foxminded.model.enums.Department;

public class TeacherDto extends PersonDto{
    
    private UUID idTeacher;
    private Degree degree;
    private Department department;
    private boolean isPermanent;
    private BigDecimal salary;
   
    public TeacherDto() {
     
    }

    public TeacherDto(TeacherDto teacher) {
        this.degree = teacher.degree;
        this.department = teacher.department;
        this.isPermanent = teacher.isPermanent;
        this.salary = BigDecimal.valueOf(teacher.getSalary().longValue());
    }

    public UUID getIdTeacher() {
        return idTeacher;
    }

    public TeacherDto setIdTeacher(UUID idTeacher) {
        this.idTeacher = idTeacher;
        return this;
    }

    public Degree getDegree() {
        return degree;
    }

    public TeacherDto setDegree(Degree degree) {
        this.degree = degree;
        return this;
    }

    public Department getDepartment() {
        return department;
    }

    public TeacherDto setDepartment(Department department) {
        this.department = department;
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
        result = prime * result + ((department == null) ? 0 : department.hashCode());
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
        if (department != other.department)
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
        return "TeacherDto [idTeacher=" + idTeacher + ", degree=" + degree 
                + ", department=" + department
                + ", isPermanent=" + isPermanent + ", salary=" + salary 
                + ", idPerson=" + super.getIdPerson() + ", firstName="
                + super.getFirstName() + ", lastName=" + super.getLastName() 
                + ", contactInfo=" + super.getContactInfo() + "]";
    }  
}
