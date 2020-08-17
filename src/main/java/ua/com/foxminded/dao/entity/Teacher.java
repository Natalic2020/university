package ua.com.foxminded.dao.entity;

import java.math.BigDecimal;

import ua.com.foxminded.model.enums.Degree;
import ua.com.foxminded.model.enums.Department;

public class Teacher {
    
    private String id;
    private String idPerson;
    private String degree;
    private String department;
    private boolean isPermanent;
    private BigDecimal salary;
   
    public Teacher() {
     
    }

    public Teacher(Teacher teacher) {
        this.degree = teacher.degree;
        this.department = teacher.department;
        this.isPermanent = teacher.isPermanent;
        this.salary = BigDecimal.valueOf(teacher.getSalary().longValue());
    }

    public String getId() {
        return id;
    }

    public Teacher setId(String id) {
        this.id = id;
        return this;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public Teacher setIdPerson(String idPerson) {
        this.idPerson = idPerson;
        return this;
    }

    public String getDegree() {
        return degree;
    }

    public Teacher setDegree(String degree) {
        this.degree = degree;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public Teacher setDepartment(String departament) {
        this.department = departament;
        return this;
    }

    public boolean isPermanent() {
        return isPermanent;
    }

    public Teacher setPermanent(boolean isPermanent) {
        this.isPermanent = isPermanent;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Teacher setSalary(BigDecimal salary) {
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
        Teacher other = (Teacher) obj;
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
        return "Teacher [id=" + id + ", idPerson=" + idPerson + ", degree=" + degree + ", departament=" + department
                + ", isPermanent=" + isPermanent + ", salary=" + salary + "]";
    }
}
