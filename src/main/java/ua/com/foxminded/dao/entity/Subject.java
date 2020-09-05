package ua.com.foxminded.dao.entity;

public class Subject {
    
    private String id;
    private String name;
    
    public Subject() {
       
    }

    public String getId() {
        return id;
    }

    public Subject setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Subject setName(String name) {
        this.name = name;
        return this;
    }
}
