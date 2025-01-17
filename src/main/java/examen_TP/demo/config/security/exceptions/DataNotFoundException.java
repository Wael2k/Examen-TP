package examen_TP.demo.config.security.exceptions;

import examen_TP.demo.config.security.exceptions.utils.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

public class DataNotFoundException extends BaseException {

    public DataNotFoundException(String code) {
        super(HttpStatus.NOT_FOUND, code);
    }
    public DataNotFoundException(String code, String message) {
        super(HttpStatus.NOT_FOUND, code,message );
    }
    public DataNotFoundException( String message, Errors errors) {
        super(HttpStatus.NOT_FOUND, message, errors);
    }
}