package ua.com.foxminded.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorDescriptor {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String message;

    public ErrorDescriptor() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorDescriptor(HttpStatus status, String message  ) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.status = status;     
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
