package ua.com.foxminded.dao.entity;

import java.math.BigDecimal;

public class Teacher extends Person{

    private String idTeacher;
//    private Person Person;
    private String degree;
    private String department;
    private boolean isPermanent;
    private BigDecimal salary;
   
    public Teacher() {
     
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public Teacher setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
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
        result = prime * result + ((idTeacher == null) ? 0 : idTeacher.hashCode());
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
        if (degree == null) {
            if (other.degree != null)
                return false;
        } else if (!degree.equals(other.degree))
            return false;
        if (department == null) {
            if (other.department != null)
                return false;
        } else if (!department.equals(other.department))
            return false;
        if (idTeacher == null) {
            if (other.idTeacher != null)
                return false;
        } else if (!idTeacher.equals(other.idTeacher))
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
    
}
