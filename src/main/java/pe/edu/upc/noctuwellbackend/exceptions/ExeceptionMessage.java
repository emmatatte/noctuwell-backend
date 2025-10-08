package pe.edu.upc.noctuwellbackend.exceptions;

import java.time.LocalDateTime;

public class ExeceptionMessage {
    private int status;
    private String exception;
    private String message;
    private String requestDescription;
    private LocalDateTime timestamp;

    public ExeceptionMessage(int status, String exception, String message, String requestDescription, LocalDateTime timestamp) {
        this.status = status;
        this.exception = exception;
        this.message = message;
        this.requestDescription = requestDescription;
        this.timestamp = timestamp;
    }

    public ExeceptionMessage() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ExeceptionMessage{" +
                "status=" + status +
                ", exception='" + exception + '\'' +
                ", message='" + message + '\'' +
                ", requestDescription='" + requestDescription + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
