package ua.com.foxminded.exception;

import java.util.UUID;

public class NoStudentFoundException extends RuntimeException{

    public NoStudentFoundException() {
        super(String.format("Students  not found"));
    }
    
}
