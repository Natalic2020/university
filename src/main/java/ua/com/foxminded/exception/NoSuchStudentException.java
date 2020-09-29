package ua.com.foxminded.exception;

import java.util.UUID;

public class NoSuchStudentException extends RuntimeException{

    public NoSuchStudentException(String id) {
        super(String.format("Student with uuid=%s not found", id));
    }   
}
