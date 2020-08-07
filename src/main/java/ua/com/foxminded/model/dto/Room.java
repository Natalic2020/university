package ua.com.foxminded.model.dto;

import java.util.UUID;

public class Room {
    
    private UUID id;
    private String name;
    
    public Room() {
      
    }
    
    public Room(Room room) {
        this.id = room.id;
        this.name = room.name;
    }

    public UUID getId() {
        return id;
    }
    public Room setId(UUID id) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Room other = (Room) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Room [id=" + id + ", name=" + name + "]";
    }      
}
