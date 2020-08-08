package ua.com.foxminded.model.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ua.com.foxminded.model.enums.Specialty;

public class Group {
    
    private UUID id;
    private String name;
    private Specialty specialty;
    private List<Student> students;
    
    public Group() {
      
    }

    public Group(Group group) {
        this.name = group.name;
        this.specialty = group.specialty;
        this.students = group.students.stream().collect(Collectors.toList());
    }

    public UUID getId() {
        return id;
    }

    public Group setId(UUID id) {
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

    public Specialty getSpecialty() {
        return specialty;
    }

    public Group setSpecialty(Specialty specialty) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((specialty == null) ? 0 : specialty.hashCode());
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
        Group other = (Group) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (specialty != other.specialty)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Group [id=" + id + ", name=" + name + ", specialty=" + specialty + ", students=" + students + "]";
    }
}
