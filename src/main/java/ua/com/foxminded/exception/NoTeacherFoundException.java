package ua.com.foxminded.exception;

public class NoTeacherFoundException extends RuntimeException{

    public NoTeacherFoundException() {
        super(String.format("Teachers  not found"));
    }
}
