package ua.com.foxminded.dao.entity;

public class Room {
    
    private String id;
    private String name;
    
    public Room() {
      
    }

    public String getId() {
        return id;
    }
    public Room setId(String id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public Room setName(String name) {
        this.name = name;
        return this;
    }
}
