package ua.com.foxminded.dao.entity;

import java.util.List;

public class Group {
    
    private String id;
    private String name;
    private String specialty;
    private List<Student> students;
    
    public Group() {
      
    }

    public String getId() {
        return id;
    }

    public Group setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Group setSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Group setStudents(List<Student> students) {
        this.students = students;
        return this;
    }
}
