package ua.com.foxminded.exception;

import java.util.UUID;

public class NoSuchTeacherException extends RuntimeException{

    public NoSuchTeacherException(String id) {
        super(String.format("Teacher with uuid=%s not found", id));
    }   
    
}
