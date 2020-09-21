package ua.com.foxminded.dao.entity;

import java.math.BigDecimal;

public class Teacher {

    private String id;
    private Person Person;
    private String degree;
    private String department;
    private boolean isPermanent;
    private BigDecimal salary;
   
    public Teacher() {
     
    }

    public Person getPerson() {
        return Person;
    }

    public Teacher setPerson(Person person) {
        Person = person;
        return this;
    }

    public String getId() {
        return id;
    }

    public Teacher setId(String id) {
        this.id = id;
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
    public String toString() {
        return "Teacher [id=" + id + ", Person=" + Person + ", degree=" + degree + ", department=" + department
                + ", isPermanent=" + isPermanent + ", salary=" + salary + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Person == null) ? 0 : Person.hashCode());
        result = prime * result + ((degree == null) ? 0 : degree.hashCode());
        result = prime * result + ((department == null) ? 0 : department.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (isPermanent ? 1231 : 1237);
        result = prime * result + ((salary == null) ? 0 : salary.hashCode());
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
        Teacher other = (Teacher) obj;
        if (Person == null) {
            if (other.Person != null)
                return false;
        } else if (!Person.equals(other.Person))
            return false;
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
