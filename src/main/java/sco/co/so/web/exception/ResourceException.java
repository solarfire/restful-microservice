package sco.co.so.web.exception;

import org.springframework.http.HttpStatus;

public class ResourceException extends RuntimeException {

    private HttpStatus httpStatus;

    public ResourceException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
