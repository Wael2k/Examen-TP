package examen_TP.demo.config.security.exceptions.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {


    private static MessageSource messageSource;
    public ErrorMessage(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    public static String prepareErrorMessage(String message){
        return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
    }
    public static String prepareErrorMessage(String message,String firstObject){
        return messageSource.getMessage(message, new Object[]{firstObject}, LocaleContextHolder.getLocale());
    }
}