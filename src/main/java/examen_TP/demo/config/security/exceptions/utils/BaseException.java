package examen_TP.demo.config.security.exceptions.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
@Getter
public class BaseException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    private final transient Object data;
    private final Errors errors;


    public BaseException(HttpStatus httpStatus, String code) {
        super(code);
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = null;
        this.data = null;
        this.errors=null;


    }

    public BaseException(HttpStatus httpStatus, String code, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.data = null;
        this.errors=null;
    }

    public BaseException(HttpStatus httpStatus, String code, String message, Object data) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.data = data;
        this.errors=null;
    }
    public BaseException(HttpStatus httpStatus, String message, Errors errors) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = null;
        this.message = message;
        this.data = null;
        this.errors = errors;
    }
}