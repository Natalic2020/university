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
}
