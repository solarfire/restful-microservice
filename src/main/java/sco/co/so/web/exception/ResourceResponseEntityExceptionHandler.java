package sco.co.so.web.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Exception Handler for all.
 *
 * 1. Handle all unexpected thrown {@link Throwable}s.
 * 2. Handle all thrown {@link ResourceException}s.
 */
@ControllerAdvice /* Methods to be shared accross all @Controller s */
@RestController   /* This is Will provide a response */
public class ResourceResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle all unexpected Exceptions (i.e. Throwable)
     * @param {@link Throwable}
     * @return {@link ResponseEntity}.
     */
    @ExceptionHandler(Throwable.class)
    public final ResponseEntity<ExceptionResponseEntity> handle(Throwable t) {
        return new ResponseEntity<>(
                new ExceptionResponseEntity(new Date(), t.getMessage(), null), INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle all unexpected  (i.e. Throwable)
     * @param {@link ResponseEntity}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(ResourceException.class)
    public final ResponseEntity<ExceptionResponseEntity> handleResourceExceptions(ResourceException e) {
        return new ResponseEntity<>(
                new ExceptionResponseEntity(new Date(), e.getMessage(), null), e.getHttpStatus());
    }


    /**
     * Triggered when binding result failed. e.g. {@link @Valid}.
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return {@link ResponseEntity}
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach( e -> errors.add(e.getDefaultMessage()));


        return new ResponseEntity<>(
                new ExceptionResponseEntity(new Date(), "Validation failed", errors.toString()), status);
    }
}