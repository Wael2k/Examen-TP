package examen_TP.demo.config.security.exceptions.utils;

import lombok.Data;

import java.util.List;

@Data
public class ExceptionResponseMessage {

    private String path;
    private String error;
    private String message;
    private List<String> errors;


    public ExceptionResponseMessage(String path, String error, String message){
        this.path = path;
        this.error = error;
        this.message = message;
    }
    public ExceptionResponseMessage(String path, String error, String message,List<String> errors){
        this.path = path;
        this.error = error;
        this.message = message;
        this.errors = errors;
    }


}