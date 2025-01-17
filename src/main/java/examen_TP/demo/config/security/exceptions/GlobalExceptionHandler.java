package examen_TP.demo.config.security.exceptions;

import examen_TP.demo.config.security.exceptions.utils.ExceptionResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ExceptionResponseMessage> handleDataNotFoundException(DataNotFoundException ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseMessage(req.getRequestURI(), ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ExceptionResponseMessage> handleBadRequestException(BadRequestException ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseMessage(req.getRequestURI(), ex.getCode(), ex.getMessage(), (List<String>) ex.getData()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseMessage> handleException(Exception ex,HttpServletRequest req) {
        ExceptionResponseMessage errorResponse = new ExceptionResponseMessage(req.getRequestURI(), "500", "Internal Server Error", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
